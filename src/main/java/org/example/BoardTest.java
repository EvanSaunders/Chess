package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the board class
 * 
 * @author evans
 * @version 10/18/2022
 */
public class BoardTest {
	/**
	 * Tests board
	 */
	@Test
	public void testBoard() {
		Board b = new Board(3, 'c');
		assertEquals(3, b.getMaxRank());
		assertEquals('c', b.getMaxFile());

		try {
			Board ba = new Board(33, 'c');
			ba.getMaxFile();
			fail("Did not throw exception");
		} catch (IllegalArgumentException e) {
			// Do Nothing
		} catch (Exception e) {
			fail("Threw wrong exception");
		}
		try {
			Board ba = new Board(-6, 'c');
			ba.getMaxFile();
			fail("Did not throw exception");
		} catch (IllegalArgumentException e) {
			// Do Nothing
		} catch (Exception e) {
			fail("Threw wrong exception");
		}
		try {
			Board ba = new Board(3, '?');
			ba.getMaxFile();
			fail("Did not throw exception");
		} catch (IllegalArgumentException e) {
			// Do Nothing
		} catch (Exception e) {
			fail("Threw wrong exception");
		}
		try {
			Board ba = new Board(3, '~');
			ba.getMaxFile();
			fail("Did not throw exception");
		} catch (IllegalArgumentException e) {
			// Do Nothing
		} catch (Exception e) {
			fail("Threw wrong exception");
		}
		try {
			Board ba = new Board(3, 'A');
			ba.getMaxFile();
			fail("Did not throw exception");
		} catch (IllegalArgumentException e) {
			// Do Nothing
		} catch (Exception e) {
			fail("Threw wrong exception");
		}
	}

	/**
	 * Tests getMaxRank
	 */
	@Test
	public void testGetMaxRank() {
		Board b = new Board(3, 'c');
		assertEquals(3, b.getMaxRank());
	}

	/**
	 * Tests getMaxFile
	 */
	@Test
	public void testGetMaxFile() {
		Board b = new Board(3, 'c');
		assertEquals('c', b.getMaxFile());
	}

	/**
	 * Tests getMinRank
	 */
	@Test
	public void testGetMinRank() {
		Board b = new Board(3, 'c');
		assertEquals(1, b.getMinRank());
	}

	/**
	 * Tests getMinFile
	 */
	@Test
	public void testGetMinFile() {
		Board b = new Board(3, 'c');
		assertEquals('a', b.getMinFile());
	}

	/**
	 * Tests getSquare
	 */
	@Test
	public void testGetSquare() {
		Board b = new Board(3, 'c');
		assertEquals("b2", b.getSquare(2, 'b').toString());
		assertEquals(null, b.getSquare(99, 'b'));
		assertEquals(null, b.getSquare(2, '~'));
		assertEquals(null, b.getSquare(-2, '?'));
		assertEquals(null, b.getSquare(99, 'b'));
		assertEquals(null, b.getSquare(2, '?'));

	}

	/**
	 * Tests clearBoard
	 */
	@Test
	public void testClearBoard() {
		Board b = new Board(3, 'c');
		ChessPiece piece = new ChessPiece(true);
		b.getSquare(2, 'b').setPiece(piece);
		b.clearBoard();
		assertEquals(null, b.getSquare(2, 'b').getPiece());
	}

}
