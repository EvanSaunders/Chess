package org.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * tests moveList
 * 
 * @author evans
 * @version 11/13/2022
 */
public class MoveListTest {

	private MoveList mL;
	private Board b;

	/**
	 * sets up mL and b
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		mL = new MoveList();
		b = new Board(8, 'h');
	}

	/**
	 * tests moveList
	 */
	@Test
	public void testMoveList() {
		assertEquals(0, mL.getNumMoves());
	}

	/**
	 * tests addMove
	 */
	@Test
	public void testAddMove() {
		assertEquals(0, mL.getNumMoves());
		mL.addMove(b.getSquare(1, 'a'));
		assertEquals(1, mL.getNumMoves());
	}

	/**
	 * tests getNumMoves
	 */
	@Test
	public void testGetNumMoves() {
		assertEquals(0, mL.getNumMoves());
		mL.addMove(b.getSquare(1, 'a'));
		assertEquals(1, mL.getNumMoves());
	}

	/**
	 * test getMove
	 */
	@Test
	public void testGetMove() {
		assertEquals(0, mL.getNumMoves());
		mL.addMove(b.getSquare(1, 'a'));
		assertEquals(b.getSquare(1, 'a'), mL.getMove(0));
		assertNull(mL.getMove(1));

		assertNull(mL.getMove(-1));
	}

	/**
	 * tests findMove
	 */
	@Test
	public void testFindMove() {
		assertNull(mL.findMove(1, 'a'));
		mL.addMove(b.getSquare(1, 'a'));
		assertNotNull(mL.findMove(1, 'a'));
	}

	/**
	 * tests reset
	 */
	@Test
	public void testReset() {
		mL.addMove(b.getSquare(1, 'a'));
		mL.reset();
		assertEquals(0, mL.getNumMoves());
	}

}
