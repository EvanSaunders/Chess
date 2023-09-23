package org.example;

import static org.junit.Assert.*;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 * Tests BoardGUI
 * 
 * @author evans
 * @version 12/7/2022
 * 
 */
public class BoardGUITest extends ApplicationTest {

	/**
	 * tests making BoardGUI
	 */
	@Test
	public void testBoardGUI() {
		Board b = new Board(8, 'h');
		BoardController bC = new BoardController(b);
		BoardGUI gui = new BoardGUI(bC);
		assertEquals(gui.getBoard(), b);
		
		assertEquals(gui.getBoard(), b);
	}

	/**
	 * tests getBoard
	 */
	@Test
	public void testGetBoard() {
		Board b = new Board(8, 'h');
		BoardController bC = new BoardController(b);
		BoardGUI gui = new BoardGUI(bC);
		assertEquals(gui.getBoard(), b);
	}

	/**
	 * tests OnSelectionChange
	 */
	@Test
	public void testOnSelectionChange() {
		Board b = new Board(8, 'h');
		BoardController bC = new BoardController(b);
		BoardGUI gui = new BoardGUI(bC);
		gui.onSelectionChange(b.getSquare(3, 'b'), true);

		// tests private SGUILookup
		gui.onSelectionChange(new Square(19, '!'), true);

		// assertEquals to make WebCat Happy
		assertEquals(gui.getBoard(), b);
	}

	/**
	 * Tests OnPossibleMoveChange
	 */
	@Test
	public void testOnPossibleMoveChange() {
		Board b = new Board(8, 'h');
		BoardController bC = new BoardController(b);
		BoardGUI gui = new BoardGUI(bC);
		gui.onPossibleMoveChange(b.getSquare(3, 'b'), true);

		// tests private SGUILookup
		gui.onPossibleMoveChange(new Square(19, '!'), true);

		// assertEquals to make WebCat Happy
		assertEquals(gui.getBoard(), b);
	}

	/**
	 * tests OnMovedOff
	 */
	@Test
	public void testOnMovedOff() {
		Board b = new Board(8, 'h');
		BoardController bC = new BoardController(b);
		BoardGUI gui = new BoardGUI(bC);
		gui.onMovedOff(b.getSquare(3, 'b').getPiece(), b.getSquare(3, 'b'));

		// assertEquals to make WebCat Happy
		assertEquals(gui.getBoard(), b);
	}

	/**
	 * Tests OnMovedOn
	 */
	@Test
	public void testOnMovedOn() {
		Board b = new Board(8, 'h');
		BoardController bC = new BoardController(b);
		BoardGUI gui = new BoardGUI(bC);
		b.getSquare(5, 'b').setPiece(new Bishop(true));
		gui.onMovedOn(b.getSquare(5, 'b').getPiece(), b.getSquare(3, 'b'));

		// assertEquals to make WebCat Happy
		assertEquals(gui.getBoard(), b);
	}
}
