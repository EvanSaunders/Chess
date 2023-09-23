package org.example;

/**
 * interface for SquareSelectionListener classes
 * 
 * @author evans
 * @version 11/30/2022
 */
public interface SquareSelectionListener {

	/**
	 * what to do when selection changes
	 * 
	 * @param s temp square
	 * @param b temp boolean
	 */
	void onSelectionChange(Square s, boolean b);

	/**
	 * what to do when possible moves change
	 * 
	 * @param s temp square
	 * @param b temp boolean
	 */
	void onPossibleMoveChange(Square s, boolean b);
}
