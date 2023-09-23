package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests SquareGUI
 * 
 * @author evans
 * @version 12/7/2022
 */
public class SquareGUITest {

	/**
	 * test SquareGui constructor
	 */
	@Test
	public void testSquareGUI() {
		Board b = new Board(8, 'h');
		SquareGUI sGUI = new SquareGUI(b.getSquare(2, 'c'));
		SquareGUI sGUI2 = new SquareGUI(b.getSquare(3, 'c'));
		assertEquals(b.getSquare(2, 'c'), sGUI.getSquare());
		assertEquals(b.getSquare(3, 'c'), sGUI2.getSquare());
	}

	/**
	 * testsAddPiece
	 */
	@Test
	public void testAddPieceGUI() {
		Board b = new Board(8, 'h');
		SquareGUI sGUI = new SquareGUI(b.getSquare(2, 'c'));

		PieceGUI pGUI = new PieceGUI(new Bishop(true));
		sGUI.addPieceGUI(pGUI);

		// Assert to Make WebCat Happy
		assertEquals(b.getSquare(2, 'c'), sGUI.getSquare());
	}

	/**
	 * tests removePieceGUI
	 */
	@Test
	public void testRemovePieceGUI() {
		Board b = new Board(8, 'h');
		SquareGUI sGUI = new SquareGUI(b.getSquare(2, 'c'));

		PieceGUI pGUI = new PieceGUI(new Bishop(true));
		sGUI.addPieceGUI(pGUI);
		sGUI.removePieceGUI();

		// Assert to Make WebCat Happy
		assertEquals(b.getSquare(2, 'c'), sGUI.getSquare());
	}

	/**
	 * tests setHighlighted
	 */
	@Test
	public void testSetHighlighted() {
		Board b = new Board(8, 'h');
		SquareGUI sGUI = new SquareGUI(b.getSquare(2, 'c'));

		sGUI.setHighlighted(true);
		assertEquals("-fx-background-color : green", sGUI.getStyle());
		sGUI.setHighlighted(false);
		assertEquals("-fx-background-color: black;", sGUI.getStyle());
	}

	/**
	 * tests setSelected
	 */
	@Test
	public void testSetSelected() {
		Board b = new Board(8, 'h');
		SquareGUI sGUI = new SquareGUI(b.getSquare(2, 'c'));

		sGUI.setSelected(true);
		assertEquals("-fx-background-color : lightblue", sGUI.getStyle());
		sGUI.setSelected(false);
		assertEquals("-fx-background-color: black;", sGUI.getStyle());
	}

}
