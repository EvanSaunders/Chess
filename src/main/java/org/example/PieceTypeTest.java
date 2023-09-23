package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests pieceType
 * 
 * @author evans
 * @version 11/13/2022
 */
public class PieceTypeTest {

	/**
	 * test toString
	 */
	@Test
	public void testToString() {
		assertEquals("Bishop", PieceType.BISHOP.toString());
		assertEquals("Knight", PieceType.KNIGHT.toString());
	}

}
