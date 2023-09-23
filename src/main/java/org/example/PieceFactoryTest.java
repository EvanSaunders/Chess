package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests PieceFactory
 * 
 * @author evans
 * @version 11/ 13/2022
 */
public class PieceFactoryTest {

	/**
	 * tests createPiece
	 */
	@Test
	public void testCreatePiece() {

		ChessPiece b = PieceFactory.createPiece(PieceType.BISHOP, true);
		assertEquals("Bishop", b.getType().toString());

		ChessPiece k = PieceFactory.createPiece(PieceType.KNIGHT, true);
		assertEquals("Knight", k.getType().toString());
	}

}
