package org.example;

/**
 * makes moveList
 * 
 * @author evans
 * @version 11/13/2022
 */
public class MoveList {
	private Square[] possMove = new Square[4];
	private int count = 0;

	/**
	 * does nothing
	 */
	public MoveList() {
		// does nothing
	}

	/**
	 * adds move to possMove
	 * 
	 * @param squareTemp temporary square
	 */
	public void addMove(Square squareTemp) {
		if (possMove.length >= 4) {
			resizeArray();
		}
		possMove[count] = squareTemp;
		count++;
	}

	/**
	 * resizes possMove
	 */
	private void resizeArray() {
		// copy data over
		Square[] bigger = new Square[possMove.length + 6];

		for (int i = 0; i < possMove.length; i++) {
			bigger[i] = possMove[i];
		}

		// update history
		possMove = bigger;
	}

	/**
	 * returns count
	 * 
	 * @return count
	 */
	public int getNumMoves() {
		return count;
	}

	/**
	 * returns move at a position
	 * 
	 * @param pos position on board
	 * @return Square
	 */
	public Square getMove(int pos) {
		if (pos >= 0 && pos <= count) {
			if (possMove[pos] != null) {
				return possMove[pos];
			}
			return null;
		}
		return null;
	}

	/**
	 * find a move at rank and file
	 * 
	 * @param rank temporary rank
	 * @param file temporary file
	 * @return Square
	 */
	public Square findMove(int rank, char file) {
		for (int i = 0; i < count; i++) {

			if (possMove[i] != null && possMove[i].getRank() == rank && possMove[i].getFile() == file) {
				return possMove[i];

			}
		}
		return null;
	}

	/**
	 * resets board
	 */
	public void reset() {
		count = 0;
	}
}
