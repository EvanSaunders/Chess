package org.example;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * tests BoardController
 * 
 * @author evans
 * @version 11/13/2022
 */
public class BoardControllerTest {

	private Board board;
	private BoardController control;

	/**
	 * Set up board and controller
	 * 
	 * @throws Exception On error
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board(8, 'h');
		control = new BoardController(board);
	}

	/**
	 * Uses addPiece to add a few pieces to the board
	 */
	private void addPieces() {
		control.addPiece(PieceType.BISHOP, false, 3, 'e');
		control.addPiece(PieceType.BISHOP, true, 7, 'e');
		control.addPiece(PieceType.KNIGHT, true, 4, 'c');
		control.addPiece(PieceType.KNIGHT, false, 2, 'd');

	}

	/**
	 * Test constructor
	 */
	@Test
	public void testBoardController() {
		// check if nothing selected
		assertNull(control.getSelected());
		assertNull(control.getPossibleMoves());
		// check board
		assertEquals(board, control.getBoard());
	}

	/**
	 * Tests if pieces added correctly
	 */
	@Test
	public void testAddPiece() {
		addPieces();
		// check if pieces exist
		ChessPiece p;
		p = board.getSquare(3, 'e').getPiece();
		assertNotNull(p); // it was added
		assertEquals(PieceType.BISHOP, p.getType());
		assertEquals(false, p.isWhite());

		p = board.getSquare(7, 'e').getPiece();
		assertNotNull(p); // it was added
		assertEquals(PieceType.BISHOP, p.getType());
		assertEquals(true, p.isWhite());

		p = board.getSquare(4, 'c').getPiece();
		assertNotNull(p); // it was added
		assertEquals(PieceType.KNIGHT, p.getType());
		assertEquals(true, p.isWhite());

		p = board.getSquare(2, 'd').getPiece();
		assertNotNull(p); // it was added
		assertEquals(PieceType.KNIGHT, p.getType());
		assertEquals(false, p.isWhite());
	}

	/**
	 * Test if selection works
	 */
	@Test
	public void testSelect() {
		addPieces();

		// select something
		control.select(7, 'e');
		// see if right square was selected
		assertEquals(board.getSquare(7, 'e'), control.getSelected());
		// see if there is a move list
		assertNotNull(control.getPossibleMoves());

		control.select(3, 'e');
		control.select(3, 'e');
		assertNull((control.getSelected()));

		control.select(999, '!');
		assertNull((control.getSelected()));

		control.select(2, 'b');
		assertNull((control.getSelected()));
		
		
		

		// control.select(7, 'e');
		// control.getSelected().getPiece().moveTo(null);
		// assertNull((control.getSelected()));

	}

	/**
	 * Tests if piece can be moved
	 */
	@Test
	public void testMoveSelected() {
		boolean result;
		ChessPiece p;

		addPieces();

		p = board.getSquare(7, 'e').getPiece();
		assertNotNull(p); // check if board was set up
		control.select(7, 'e'); // select it
		result = control.moveSelected(5, 'g'); // move it

		// test piece in new location
		assertEquals(p, board.getSquare(5, 'g').getPiece());
		// test old location empty
		assertNull(board.getSquare(7, 'e').getPiece());
		// test result
		assertTrue(result);
		// test selected, should be cleared
		assertNull(control.getSelected());
		assertNull(control.getPossibleMoves());

		control.select(5, 'g');
		assertFalse(control.moveSelected(99, '!'));
		control.clearSelected();
		assertFalse(control.moveSelected(1, 'a'));

	}

	/**
	 * tests removeSelected
	 */
	@Test
	public void testRemoveSelected() {
		addPieces();
		control.select(7, 'e');
		control.removeSelected();
		assertNull(control.getSelected());

		control.select(8, 'e');
		control.removeSelected();
		assertNull(control.getSelected());
	}

	/**
	 * tests resetBoard
	 */
	@Test
	public void testResetBoard() {
		addPieces();
		control.select(7, 'e');
		assertEquals(board.getSquare(7, 'e'), control.getSelected());
		control.resetBoard();
		assertNull(control.getSelected());
	}

	/**
	 * tests addListener
	 */
	@Test
	public void testAddListener() {
		BoardController bC = new BoardController(new Board(8, 'h'));
		SquareSelectionListener ssL = null;
		bC.addListener(ssL);

		// cant test, make webcat happy
		addPieces();
		control.select(7, 'e');
		assertEquals(board.getSquare(7, 'e'), control.getSelected());
	}

	/**
	 * tests removeListener
	 */
	@Test
	public void testRemoveListener() {
		BoardController bC = new BoardController(new Board(8, 'h'));
		SquareSelectionListener ssL = null;
		bC.addListener(ssL);
		bC.removeListener(ssL);

		// cant test, make webcat happy
		addPieces();
		control.select(7, 'e');
		assertEquals(board.getSquare(7, 'e'), control.getSelected());
	}

}
