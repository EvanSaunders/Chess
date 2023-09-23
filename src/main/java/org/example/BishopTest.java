package org.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests Bishop Class
 * 
 * @author evans
 * @version 11/13/2022
 */
public class BishopTest {

	private Board board;
	private Bishop b;

	/**
	 * sets up board and b
	 * 
	 */
	@Before
	public void setUp() {
		b = new Bishop(true);
		board = new Board(8, 'h');
	}

	/**
	 * tests getType
	 */
	@Test
	public void testGetType() {
		assertEquals(PieceType.BISHOP, b.getType());
	}

	/**
	 * tests getPossibleMoves
	 */
	@Test
	public void testGetPossibleMoves() {
		ChessPiece b6 = new ChessPiece(true);
		b6.moveTo(board.getSquare(6, 'b'));
		ChessPiece c4 = new ChessPiece(true);
		c4.moveTo(board.getSquare(4, 'c'));
		Bishop e3 = new Bishop(false);
		e3.moveTo(board.getSquare(3, 'e'));
		ChessPiece e6 = new ChessPiece(true);
		e6.moveTo(board.getSquare(6, 'e'));
		ChessPiece g6 = new ChessPiece(false);
		g6.moveTo(board.getSquare(6, 'g'));
		ChessPiece g5 = new ChessPiece(false);
		g5.moveTo(board.getSquare(5, 'g'));

		MoveList list = new MoveList();
		list = e3.getPossibleMoves();

		// Left up
		assertNull(list.findMove(8, 'h'));
		assertEquals(board.getSquare(4, 'd'), list.findMove(4, 'd'));
		assertEquals(board.getSquare(5, 'c'), list.findMove(5, 'c'));
		System.out.println(list.findMove(6, 'b'));
		assertEquals(board.getSquare(6, 'b'), list.findMove(6, 'b'));
		assertNull(list.findMove(7, 'a'));

		// right up
		assertEquals(board.getSquare(4, 'f'), list.findMove(4, 'f'));
		assertNull(list.findMove(5, 'g'));

		// right down
		assertEquals(board.getSquare(2, 'f'), list.findMove(2, 'f'));
		assertEquals(board.getSquare(1, 'g'), list.findMove(1, 'g'));
		assertNull(list.findMove(0, 'h'));

		// left down
		assertEquals(board.getSquare(2, 'd'), list.findMove(2, 'd'));
		assertEquals(board.getSquare(1, 'c'), list.findMove(1, 'c'));
		assertNull(list.findMove(0, 'b'));

		Bishop nullPiece = new Bishop(false);
		assertNull(nullPiece.getPossibleMoves());

		ChessPiece f4 = new ChessPiece(true);
		f4.moveTo(board.getSquare(4, 'f'));
		ChessPiece f2 = new ChessPiece(true);
		f2.moveTo(board.getSquare(2, 'f'));
		ChessPiece d2 = new ChessPiece(true);
		d2.moveTo(board.getSquare(2, 'd'));

		MoveList list2 = new MoveList();
		list2 = e3.getPossibleMoves();

	}

	/**
	 * tests toString
	 */
	@Test
	public void testToString() {
		assertEquals("WB", b.toString());
		b.moveTo(board.getSquare(7, 'e'));
		assertEquals("WB@e7", b.toString());

		b = new Bishop(false);
		assertEquals("BB", b.toString());
		b.moveTo(board.getSquare(4, 'e'));
		assertEquals("BB@e4", b.toString());
	}

	/**
	 * tests Bishop constructor
	 */
	@Test
	public void testBishop() {
		assertEquals(PieceType.BISHOP, b.getType());
	}

}
