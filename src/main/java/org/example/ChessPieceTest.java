package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test the ChessPiece class
 * 
 * @author evans
 * @version 10/18/2022
 */
public class ChessPieceTest {
	/**
	 * Test ChessPiece constructor
	 */
	@Test
	public void testChessPiece() {
		ChessPiece piece = new ChessPiece(true);
		assertTrue(piece.isWhite());
		assertFalse(piece.isBlack());
		assertEquals(null, piece.getSquare());
	}

	/**
	 * tests inWhite
	 */
	@Test
	public void testIsWhite() {
		ChessPiece piece = new ChessPiece(true);
		assertTrue(piece.isWhite());
		piece = new ChessPiece(false);
		assertFalse(piece.isWhite());
	}

	/**
	 * tests isBlack
	 */
	@Test
	public void testIsBlack() {
		ChessPiece piece = new ChessPiece(true);
		assertFalse(piece.isBlack());
		piece = new ChessPiece(false);
		assertTrue(piece.isBlack());
	}

	/**
	 * Tests getSquare
	 */
	@Test
	public void testGetSquare() {
		ChessPiece piece = new ChessPiece(true);
		assertEquals(null, piece.getSquare());
	}

	/**
	 * tests moveTo
	 */
	@Test
	public void testMoveTo() {
		ChessPiece piece = new ChessPiece(true);
		Square tempSquare = new Square(new Board(3, 'c'), 2, 'b');
		piece.moveTo(tempSquare);
		assertEquals(piece, tempSquare.getPiece());
		tempSquare = new Square(5, 'h');
		tempSquare.setPiece(new ChessPiece(false));
		piece.moveTo(tempSquare);
		assertEquals(piece, tempSquare.getPiece());

	}

	/**
	 * tests getters and toString, should not be implemented yet
	 */
	@Test
	public void testGetters() {
		ChessPiece piece = new ChessPiece(true);
		assertEquals(null, piece.getType());

		assertEquals(null, piece.getPossibleMoves());

		assertEquals("", piece.toString());
	}

	/**
	 * tests canMoveTo
	 */
	@Test
	public void testCanMoveTo() {
		Board board = new Board(8, 'h');

		Bishop piece = new Bishop(true);
		piece.moveTo(board.getSquare(3, 'e'));

		ChessPiece g5 = new ChessPiece(false);
		g5.moveTo(board.getSquare(5, 'g'));

		assertTrue(piece.canMoveTo(5, 'g'));
		assertTrue(piece.canMoveTo(4, 'f'));

		ChessPiece g3 = new ChessPiece(true);
		g3.moveTo(board.getSquare(3, 'g'));

		assertFalse(piece.canMoveTo(3, 'g'));

		Bishop piece2 = new Bishop(false);
		piece2.moveTo(board.getSquare(7, 'c'));

		ChessPiece d7 = new ChessPiece(true);
		d7.moveTo(board.getSquare(7, 'd'));

		assertTrue(piece2.canMoveTo(7, 'd'));
		assertTrue(piece2.canMoveTo(4, 'f'));

		ChessPiece g32 = new ChessPiece(true);
		g32.moveTo(board.getSquare(3, 'g'));
	}

	/**
	 * tests addMove
	 */
	@Test
	public void testAddMove() {
		Board board = new Board(8, 'h');

		Bishop piece = new Bishop(true);
		piece.moveTo(board.getSquare(3, 'e'));
		System.out.println(board.getSquare(3, 'e'));

		MoveList mL = new MoveList();
		// Board bb = new Board(8, 'a');
		// Knight k = new Knight(true);
		// System.out.println(bb.getSquare(3, 'c'));
		assertTrue(piece.addMove(mL, 1, 'a'));
		assertFalse(piece.addMove(mL, 99, '!'));
	}

}
