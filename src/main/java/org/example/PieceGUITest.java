package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests PieceGUI
 * 
 * @author evans
 * @version 12/7/2022
 */
public class PieceGUITest {

	/**
	 * tests if the correct piece image was retrieved
	 */
	@Test
	public void testPieceGUI() {
		Bishop bishW = new Bishop(true);
		PieceGUI pGUI = new PieceGUI(bishW);
		assertEquals(bishW, pGUI.getPiece());

		Bishop bishB = new Bishop(false);
		pGUI = new PieceGUI(bishB);
		assertEquals(bishB, pGUI.getPiece());

		Knight kniB = new Knight(false);
		pGUI = new PieceGUI(kniB);
		assertEquals(kniB, pGUI.getPiece());

		Knight kniW = new Knight(true);
		pGUI = new PieceGUI(kniW);
		assertEquals(kniW, pGUI.getPiece());
	}

}
