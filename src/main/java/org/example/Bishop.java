package org.example;

/**
 * Creates a Bishop that inherits ChessPiece
 * 
 * @author evans
 * @version 11/13/2022
 */
public class Bishop extends ChessPiece {
	/**
	 * Constructor
	 * 
	 * @param side white or black
	 */
	public Bishop(boolean side) {
		super(side);
	}

	/**
	 * returns type
	 * 
	 * @return type
	 */
	public PieceType getType() {
		return PieceType.BISHOP;
	}

	/**
	 * returns a string and location if valid
	 * 
	 * @return String of type and location
	 */
	public String toString() {
		if (location != null) {
			if (side) {
				return "WB@" + location.getFile() + location.getRank();
			} else {
				return "BB@" + location.getFile() + location.getRank();
			}
		}
		if (side) {
			return "WB";
		}
		return "BB";
	}

	/**
	 * returns a MoveList of possible moves
	 * 
	 * @return MoveList of possible moves
	 */
	public MoveList getPossibleMoves() {
		if (location == null) {
			return null;
		}
		MoveList moveOptions = new MoveList();
		int rankTranslate = location.getRank();
		char fileTranslate = location.getFile();

		// diagonal right down
		int y = 1;
		char x = 1;

		while (canMoveTo(rankTranslate - y, (char) (fileTranslate + x))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate - y, (char) (fileTranslate + x)));
			if (location.getBoard().getSquare(rankTranslate - y, (char) (fileTranslate + x)).getPiece() != null) {
				break;
			}
			y++;
			x++;
		}

		// diagonal right up
		y = 1;
		x = 1;
		while (canMoveTo(rankTranslate + y, (char) (fileTranslate + x))) {
			System.out.println(y);
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate + y, (char) (fileTranslate + x)));
			if (location.getBoard().getSquare(rankTranslate + y, (char) (fileTranslate + x)).getPiece() != null) {
				break;
			}
			y++;
			x++;

		}

		// diagonal left down
		y = 1;
		x = 1;
		while (canMoveTo(rankTranslate - y, (char) (fileTranslate - x))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate - y, (char) (fileTranslate - x)));
			if (location.getBoard().getSquare(rankTranslate - y, (char) (fileTranslate - x)).getPiece() != null) {
				break;
			}
			y++;
			x++;

		}

		// diagonal left up
		y = 1;
		x = 1;
		while (canMoveTo(rankTranslate + y, (char) (fileTranslate - x))) {
			moveOptions.addMove(location.getBoard().getSquare(rankTranslate + y, (char) (fileTranslate - x)));

			if (location.getBoard().getSquare(rankTranslate + y, (char) (fileTranslate - x)).getPiece() != null) {
				break;
			}
			y++;
			x++;
		}

		return moveOptions;
	}

}
