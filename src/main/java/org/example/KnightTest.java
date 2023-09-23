package org.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * tests Knight class
 * 
 * @author evans
 * @version 11/13/2022
 */
public class KnightTest {

	private Board board;
	private Knight k;

	/**
	 * sets up k and board
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		k = new Knight(true);
		board = new Board(8, 'h');
	}

	/**
	 * tests getType
	 */
	@Test
	public void testGetType() {
		assertEquals(PieceType.KNIGHT, k.getType());
	}

	/**
	 * tests getPossibleMoves
	 */
	@Test
	public void testGetPossibleMoves() {
		ChessPiece a5 = new ChessPiece(false);
		a5.moveTo(board.getSquare(5, 'a'));
		ChessPiece b6 = new ChessPiece(false);
		b6.moveTo(board.getSquare(6, 'b'));
		Knight d4 = new Knight(true);
		d4.moveTo(board.getSquare(4, 'd'));
		ChessPiece d8 = new ChessPiece(true);
		d8.moveTo(board.getSquare(8, 'd'));
		ChessPiece e6 = new ChessPiece(false);
		e6.moveTo(board.getSquare(6, 'e'));
		ChessPiece g6 = new ChessPiece(false);
		g6.moveTo(board.getSquare(6, 'g'));
		ChessPiece e2 = new ChessPiece(true);
		e2.moveTo(board.getSquare(2, 'e'));
		ChessPiece a1 = new ChessPiece(false);
		a1.moveTo(board.getSquare(1, 'a'));

		MoveList list = new MoveList();
		list = d4.getPossibleMoves();

		assertNull(list.findMove(1, 'a'));

		assertEquals(board.getSquare(6, 'c'), list.findMove(6, 'c'));

		assertEquals(board.getSquare(6, 'e'), list.findMove(6, 'e'));

		assertEquals(board.getSquare(5, 'f'), list.findMove(5, 'f'));

		assertEquals(board.getSquare(3, 'f'), list.findMove(3, 'f'));

		assertNull(list.findMove(2, 'e'));

		assertEquals(board.getSquare(2, 'c'), list.findMove(2, 'c'));

		assertEquals(board.getSquare(5, 'b'), list.findMove(5, 'b'));

		MoveList list2 = new MoveList();
		e2.moveTo(null);
		list2 = d4.getPossibleMoves();

		assertEquals(board.getSquare(6, 'e'), list2.findMove(6, 'e'));

		Knight nullPiece = new Knight(false);
		nullPiece.moveTo(null);
		assertNull(nullPiece.getPossibleMoves());
	}

	/**
	 * tests toString
	 */
	@Test
	public void testToString() {
		assertEquals("WN", k.toString());
		k.moveTo(board.getSquare(7, 'e'));
		assertEquals("WN@e7", k.toString());

		k = new Knight(false);
		assertEquals("BN", k.toString());
		k.moveTo(board.getSquare(4, 'e'));
		assertEquals("BN@e4", k.toString());
	}

	/**
	 * tests Knight constructor
	 */
	@Test
	public void testKnight() {
		assertEquals(PieceType.KNIGHT, k.getType());
	}

}
