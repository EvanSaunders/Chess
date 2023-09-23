package org.example;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Tests the square class
 * 
 * @author evans
 * @version 10/18/2022
 */
public class SquareTest {
	/**
	 * tests Square constructor
	 */
	@Test
	public void testSquareBoardIntChar() {
		Square s = new Square(new Board(5, 'e'), 3, 'c');
		assertEquals(3, s.getRank());
		assertEquals('c', s.getFile());
	}

	/**
	 * tests Square constructor
	 */
	@Test
	public void testSquareIntChar() {
		Square s = new Square(3, 'c');
		assertEquals(3, s.getRank());
		assertEquals('c', s.getFile());
	}

	/**
	 * tests getRank
	 */
	@Test
	public void testGetRank() {
		Square s = new Square(3, 'c');
		assertEquals(3, s.getRank());
	}

	/**
	 * tests getFile
	 */
	@Test
	public void testGetFile() {
		Square s = new Square(3, 'c');
		assertEquals('c', s.getFile());
	}

	/**
	 * tests getBoard
	 */
	@Test
	public void testGetBoard() {
		Board b = new Board(5, 'e');
		Square s = new Square(b, 3, 'c');
		assertEquals(b, s.getBoard());
	}

	/**
	 * tests getPiece
	 */
	@Test
	public void testGetPiece() {
		Square s = new Square(3, 'c');
		ChessPiece piece = new ChessPiece(true);
		s.setPiece(piece);
		assertEquals(piece, s.getPiece());
	}

	/**
	 * tests setPiece
	 */
	@Test
	public void testSetPiece() {
		Square s = new Square(3, 'c');
		ChessPiece piece = new ChessPiece(true);
		s.setPiece(piece);
		assertEquals(piece, s.getPiece());
	}

	/**
	 * tests toString
	 */
	@Test
	public void testToString() {
		Square s = new Square(3, 'c');
		assertEquals("c3", s.toString());
	}

	/**
	 * tests getListener
	 */
	@Test
	public void testGetListener() {
		Board b = new Board(8, 'h');
		BoardController bC = new BoardController(b);
		BoardGUI sML = new BoardGUI(bC);
		Square s = b.getSquare(4, 'd');
		s.setListener(sML);
		assertEquals(sML, s.getListener());
	}

	/**
	 * Inner class to record the listener method calls
	 * 
	 * @author turners
	 * @version 1
	 */
	private class ListenerCall {
		private ChessPiece piece;
		private Square square;
		private boolean movedOn;

		/**
		 * Records a method call on the listener
		 * 
		 * @param p  New ChessPiece
		 * @param s  New Square
		 * @param on true if moved on to the square
		 */
		public ListenerCall(ChessPiece p, Square s, boolean on) {
			piece = p;
			square = s;
			movedOn = on;
		}

		/**
		 * Get the piece
		 * 
		 * @return The piece
		 */
		public ChessPiece getPiece() {
			return piece;
		}

		/**
		 * Get the square
		 * 
		 * @return The square
		 */
		public Square getSquare() {
			return square;
		}

		/**
		 * Get the type of move
		 * 
		 * @return True if moved on to the squre
		 */
		public boolean isMoveOn() {
			return movedOn;
		}
	}

	/**
	 * Test listener
	 * 
	 * @author turners
	 * @version 1
	 */
	private class TestListener implements SquareMoveListener {
		private ArrayList<ListenerCall> calls;

		/**
		 * Sets up listener
		 */
		public TestListener() {
			calls = new ArrayList<ListenerCall>();
		}

		/**
		 * tests onMovedOff
		 */
		@Override
		public void onMovedOff(ChessPiece piece, Square location) {
			calls.add(new ListenerCall(piece, location, false));
		}

		/**
		 * tests onMovedOn
		 */
		@Override
		public void onMovedOn(ChessPiece piece, Square location) {
			calls.add(new ListenerCall(piece, location, true));
		}

		public ArrayList<ListenerCall> getCalls() {
			return calls;
		}
	}

	/**
	 * Test basic SquareMoveListener functionality
	 */
	@Test
	public void testSquareMoveListener() {
		Square sq = new Square(5, 'e');
		ChessPiece p = new Knight(true);
		TestListener listen = new TestListener();
		// add the listener
		sq.setListener(listen);
		// should trigger and onMovedOn event
		p.moveTo(sq);

		// test it
		ArrayList<ListenerCall> calls = listen.getCalls();
		assertEquals(1, calls.size()); // should be exactly one
		assertSame(sq, calls.get(0).getSquare()); // same square
		assertSame(p, calls.get(0).getPiece()); // same piece
		assertTrue(calls.get(0).isMoveOn()); // moved on method

		// should trigger and onMovedOff event
		p.moveTo(null);

		// test it
		calls = listen.getCalls();
		assertEquals(2, calls.size()); // should be exactly one
		assertSame(sq, calls.get(1).getSquare()); // same square
		assertSame(p, calls.get(1).getPiece()); // same piece
		assertFalse(calls.get(1).isMoveOn()); // moved on method
	}

}
