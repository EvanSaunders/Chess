package org.example;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Test;

/**
 * Tests the BoardIO class
 * 
 * @author evans
 * @version 10/18/2022
 */
public class BoardIOTest {

	/**
	 * Tests LoadBoardState
	 */
	@Test
	public void testLoadBoardStateBoardScanner() {
		// loads the data for the input
		// BISHOP and KNIGHT are okay since checkPiece
		// is not checking the type
		Scanner input = new Scanner("BISHOP black 1 d\n" + "KNIGHT white 2 g\n" + "BISHOP white 4\n"
				+ "KNIGHT black 5 a\n" + "KNIGHT 6 b\n" + "BISHOP black 6 d\n");

		BoardIO io = new BoardIO();
		Board board = new Board(8, 'h');
		// should read in 4 values
		boolean result = io.loadBoardState(board, input);
		// tests

		// check if loaded
		assertTrue(result);

		// check the number of pieces on the board
		assertEquals(4, countPieces(board));
		result = io.loadBoardState(null, input);
		assertFalse(result);

		// check each piece
		checkPiece(board, "BISHOP", false, 1, 'd');
		checkPiece(board, "KNIGHT", true, 2, 'g');
		checkPiece(board, "KNIGHT", false, 5, 'a');
		checkPiece(board, "BISHOP", false, 6, 'd');

		input.close();

		boolean result2 = io.loadBoardState(null, input);
		assertFalse(result2);

		input = null;
		boolean result3 = io.loadBoardState(board, input);
		assertFalse(result3);

	}

	/**
	 * Tests LoadBoardState
	 */
	@Test
	public void testLoadBoardStateBoardString() {

		BoardIO io = new BoardIO();
		Board board = new Board(8, 'h');
		// should read in 4 values
		
		io.saveBoardState(board, "testOut2.txt");

		boolean result = io.loadBoardState(board, "testOut2.txt");
		// tests

		// check if loaded
		assertTrue(result);
		boolean result2 = io.loadBoardState(board, "testOut3.txt");
		assertFalse(result2);

		// check the number of pieces on the board
		assertEquals(0, countPieces(board));
		result = io.loadBoardState(null, "testOut1.txt");
		assertFalse(result);

		// check each piece
	}

	/**
	 * Tests SaveBoardState
	 */
	@Test
	public void testSaveBoardStateBoardPrintWriter() {
		Board board = new Board(8, 'h');
		// create pieces

		// pieces (i.e. Bishop or Knight)
		Bishop p1 = new Bishop(false);
		Knight p2 = new Knight(true);
		Bishop p3 = new Bishop(false);
		Knight p4 = new Knight(false);
		// place them on the board
		p1.moveTo(board.getSquare(1, 'd'));
		p2.moveTo(board.getSquare(2, 'g'));
		p3.moveTo(board.getSquare(5, 'a'));
		p4.moveTo(board.getSquare(6, 'd'));

		// create SpriteIO with some data
		BoardIO io = new BoardIO();
		// write it to a file
		File file = new File("testOut1.txt");
		try {
			io.saveBoardState(board, new PrintWriter(file));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			io.saveBoardState(null, new PrintWriter(file));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		try {
			boolean result = io.saveBoardState(null, new PrintWriter(file));
			assertFalse(result);

			PrintWriter wr = null;
			boolean result2 = io.saveBoardState(board, wr);
			assertFalse(result2);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}
	

	/**
	 * Tests SaveBoardState
	 */
	@Test
	public void testSaveBoardStateBoardString() {
		Board board = new Board(8, 'h');
		// create pieces

		// pieces (i.e. Bishop or Knight)
		Bishop p1 = new Bishop(false);
		Knight p2 = new Knight(true);
		Bishop p3 = new Bishop(false);
		Knight p4 = new Knight(false);
		// place them on the board
		p1.moveTo(board.getSquare(1, 'd'));
		p2.moveTo(board.getSquare(2, 'g'));
		p3.moveTo(board.getSquare(5, 'a'));
		p4.moveTo(board.getSquare(6, 'd'));

		// create SpriteIO with some data
		BoardIO io = new BoardIO();
		// write it to a file
		String nullTester = null;
		assertFalse(io.saveBoardState(null, "testOut1.txt"));
		assertFalse(io.saveBoardState(board, nullTester));
		io.saveBoardState(board, "testOut1.txt");
		// now check file
		File file = new File("testOut1.txt");
		try {
			Scanner in = new Scanner(file);
			String line;
			// read in a line and remove some
			// whitespace are the beginning and end (just in case)
			// may need to be careful about the spacing between
			// values on each line put the expected values in
			// an array instead? Then I could use a loop
			line = in.nextLine().trim();

			// PIECE will need to be a specific
			// type (i.e. BISHOP or KNIGHT)

			// on the order the board is searched
			assertEquals("BISHOP black 1 d", line);
			line = in.nextLine().trim();
			assertEquals("KNIGHT white 2 g", line);
			line = in.nextLine().trim();
			assertEquals("BISHOP black 5 a", line);
			line = in.nextLine().trim();
			assertEquals("KNIGHT black 6 d", line);

			in.close();
		} catch (FileNotFoundException e) {
			fail("Couldn't open file");
		}
		// remove the file. Comment it out if you want
		// to examine the file by hand
		file.delete();
	}

	/**
	 * Counts the pieces on the board
	 * 
	 * @param board temporary board
	 * @return number of pieces on board
	 */
	private int countPieces(Board board) {
		int count = 0;
		// check all of the squares

		for (int r = 1; r <= board.getMaxRank(); r++) {
			for (char f = 'a'; f <= board.getMaxFile(); f++) {
				// if there is a piece there, count it

				if (board.getSquare(r, f).getPiece() != null) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Checks a piece
	 * 
	 * @param b     temporary board
	 * @param type  temporary type
	 * @param wSide temporary side
	 * @param rank  temporary rank
	 * @param file  temporary file
	 */
	private void checkPiece(Board b, String type, boolean wSide, int rank, char file) {
		Square sq = b.getSquare(rank, file);
		ChessPiece p = sq.getPiece();
		assertNotNull("No chess piece was on the square " + file + rank + ".", p);

		// assertEquals(type, p.getType().name());
		assertEquals(wSide, p.isWhite());
	}

}
