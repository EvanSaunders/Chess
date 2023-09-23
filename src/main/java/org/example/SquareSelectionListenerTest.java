package org.example;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * tests squareSeletionListner
 * 
 * @author evans
 * @version 12/8/2022
 */
public class SquareSelectionListenerTest {

	private void checkCall(ArrayList<ListenerCall> list, int pos, Square sq, boolean selected, String method) {

		assertSame(sq + "did not match", sq, list.get(pos).getSquare());
		assertEquals(sq + "did not match", selected, list.get(pos).isSelected());
		assertEquals(sq + "did not match", method, list.get(pos).getMethodName());
	}

	/**
	 * creates some methods to help test
	 * 
	 * @author evans
	 * @version 12/8/12
	 */
	private class TestListener implements SquareSelectionListener {
		private ArrayList<ListenerCall> calls;

		/**
		 * Sets up listener
		 */
		public TestListener() {
			calls = new ArrayList<ListenerCall>();
		}

		@Override
		public void onSelectionChange(Square s, boolean b) {
			calls.add(new ListenerCall(s, b, "selected"));

		}

		@Override
		public void onPossibleMoveChange(Square s, boolean b) {
			calls.add(new ListenerCall(s, b, "move"));

		}

		public ArrayList getCalls() {
			return calls;
		}

	}

	/**
	 * Inner class to record the listener method calls
	 * 
	 * @author turners
	 * @version 1
	 */
	private class ListenerCall {
		private Square square;
		private boolean selected;
		private String methodName;

		/**
		 * Records a method call on the listener
		 * 
		 * @param p  New ChessPiece
		 * @param s  New Square
		 * @param on true if moved on to the square
		 */
		public ListenerCall(Square s, boolean sel, String name) {

			square = s;
			selected = sel;
			methodName = name;

		}

		/**
		 * Get the piece
		 * 
		 * @return The piece
		 */
		public String getMethodName() {
			return methodName;
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
		 * @return True if moved on to the square
		 */
		public boolean isSelected() {
			return selected;
		}
	}

	/**
	 * tests squareSelectionListener
	 */
	@Test
	public void tester() {

		Board board = new Board(8, 'h');
		BoardController control = new BoardController(board);

		ChessPiece bishop1 = new Bishop(false);
		bishop1.moveTo(board.getSquare(8, 'a'));

		TestListener tListen = new TestListener();
		control.addListener(tListen);
		control.select(8, 'a');

		ArrayList<ListenerCall> list = tListen.getCalls();

		assertEquals(8, list.size());

		checkCall(list, 0, board.getSquare(8, 'a'), true, "selected");
		checkCall(list, 1, board.getSquare(7, 'b'), true, "move");
		checkCall(list, 2, board.getSquare(6, 'c'), true, "move");
		checkCall(list, 3, board.getSquare(5, 'd'), true, "move");
		checkCall(list, 4, board.getSquare(4, 'e'), true, "move");
		checkCall(list, 5, board.getSquare(3, 'f'), true, "move");
		checkCall(list, 6, board.getSquare(2, 'g'), true, "move");
		checkCall(list, 7, board.getSquare(1, 'h'), true, "move");
		// need board and controller

		// add pieces

		// add a listener
		// TODO
		// clear selection
		// selecting different piece
		// moving
		// removing
	}

}
