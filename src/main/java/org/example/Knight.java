package org.example;

/**
 * Creates a Knight that inherits ChessPiece
 * 
 * @author evans
 * @version 11/13/2022
 */
public class Knight extends ChessPiece {
	/**
	 * Knight constructor
	 * 
	 * @param sideTemp side white or black
	 */
	public Knight(boolean sideTemp) {
		super(sideTemp);
	}

	/**
	 * returns type
	 * 
	 * @return PieceType
	 */
	public PieceType getType() {
		return PieceType.KNIGHT;
	}

	/**
	 * returns String
	 * 
	 * @return String
	 */
	public String toString() {
		if (location != null) {
			if (side) {
				return "WN@" + location.getFile() + location.getRank();
			}
			return "BN@" + location.getFile() + location.getRank();
		}
		if (side) {
			return "WN";
		}
		return "BN";
	}

	/**
	 * returns a moveList of possible Moves
	 * 
	 * @return MoveList
	 */
	public MoveList getPossibleMoves() {
		if (location == null) {
			return null;
		}
		MoveList moveOptions = new MoveList();
		int rankTranslate = location.getRank();
		char fileTranslate = location.getFile();

		// UP 2, Left 1
		if (canMoveTo(rankTranslate + 2, (char) (fileTranslate - 1))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate + 2, (char) (fileTranslate - 1)));
		}

		// UP 2, right 1
		if (canMoveTo(rankTranslate + 2, (char) (fileTranslate + 1))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate + 2, (char) (fileTranslate + 1)));
		}

		// UP 1, right 2
		if (canMoveTo(rankTranslate + 1, (char) (fileTranslate + 2))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate + 1, (char) (fileTranslate + 2)));
		}

		// down 1, right 2
		if (canMoveTo(rankTranslate - 1, (char) (fileTranslate + 2))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate - 1, (char) (fileTranslate + 2)));
		}

		// down 2, right 1
		if (canMoveTo(rankTranslate - 2, (char) (fileTranslate + 1))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate - 2, (char) (fileTranslate + 1)));
		}

		// down 2, left 1
		if (canMoveTo(rankTranslate - 2, (char) (fileTranslate - 1))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate - 2, (char) (fileTranslate - 1)));
		}

		// down 1, left 2
		if (canMoveTo(rankTranslate - 1, (char) (fileTranslate - 2))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate - 1, (char) (fileTranslate - 2)));
		}

		// up 1, left 2
		if (canMoveTo(rankTranslate + 1, (char) (fileTranslate - 2))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate + 1, (char) (fileTranslate - 2)));
		}

		return moveOptions;
	}
}
