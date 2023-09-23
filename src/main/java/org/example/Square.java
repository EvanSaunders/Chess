package org.example;

/**
 * Creates square objects
 * 
 * @author evans
 * @version 10/18/2022
 * 
 */
public class Square {
	private int rank;
	private char file;
	private Board whatBoard;
	private ChessPiece piece;
	private SquareMoveListener sML;

	/**
	 * chessPiece constructor
	 * 
	 * @param whatBoardTemp temporary board
	 * @param rankTemp      temporary rank
	 * @param fileTemp      temporary file
	 */
	public Square(Board whatBoardTemp, int rankTemp, char fileTemp) {
		rank = rankTemp;
		file = fileTemp;
		whatBoard = whatBoardTemp;
		piece = null;
		sML = null;
	}

	/**
	 * chessPiece constructor
	 * 
	 * @param rankTemp temporary rank
	 * @param fileTemp temporary file
	 */
	public Square(int rankTemp, char fileTemp) {
		rank = rankTemp;
		file = fileTemp;
		whatBoard = null;
		piece = null;
	}

	/**
	 * returns rank
	 * 
	 * @return rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * returns rank
	 * 
	 * @return rank
	 */
	public char getFile() {
		return file;
	}

	/**
	 * returns rank
	 * 
	 * @return rank
	 */
	public Board getBoard() {
		return whatBoard;
	}

	/**
	 * returns piece
	 * 
	 * @return piece
	 */
	public ChessPiece getPiece() {
		return piece;
	}

	/**
	 * sets the Piece
	 * 
	 * @param temp temporary piece
	 */
	public void setPiece(ChessPiece temp) {
		if (sML != null && piece != null) {
			sML.onMovedOff(piece, this);
		}

		piece = temp;

		if (sML != null && piece != null) {
			sML.onMovedOn(temp, this);
		}
	}

	/**
	 * returns a string of file and rank
	 * 
	 * @return string
	 */
	public String toString() {
		return "" + file + rank;
	}

	/**
	 * returns listener
	 * 
	 * @return sML
	 */
	public SquareMoveListener getListener() {
		return sML;
	}

	/**
	 * sets listener
	 * 
	 * @param sMLTemp Temporary listener
	 */
	public void setListener(SquareMoveListener sMLTemp) {
		sML = sMLTemp;
	}
}
