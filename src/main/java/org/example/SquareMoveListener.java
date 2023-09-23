package org.example;

/**
 * interface for SquareMoveListener classes
 * 
 * @author evans
 * @version 11/30/2022
 */
public interface SquareMoveListener {
	/**
	 * what to do when moved off
	 * 
	 * @param piece temp piece
	 * @param s     temp square
	 */
	void onMovedOff(ChessPiece piece, Square s);

	/**
	 * what to do when moved on
	 * 
	 * @param piece temp piece
	 * @param s     temp square
	 */
	void onMovedOn(ChessPiece piece, Square s);
}
