package org.example;

/**
 * Creates a chessPiece object
 * 
 * @author evans
 * @version 10/18/2022
 */
public class ChessPiece {
	/**
	 * what side
	 */
	protected boolean side;
	/**
	 * what square the piece is on
	 */
	protected Square location;

	/**
	 * ChessPiece constructor
	 * 
	 * @param b temporary side
	 */
	public ChessPiece(boolean b) {
		side = b;
		location = null;
	}

	/**
	 * returns true if white
	 * 
	 * @return true if white
	 */
	public boolean isWhite() {
		return side;
	}

	/**
	 * returns true if black
	 * 
	 * @return true if black
	 */
	public boolean isBlack() {
		return !side;
	}

	/**
	 * returns location
	 * 
	 * @return location
	 */
	public Square getSquare() {
		return location;
	}

	/**
	 * moves a piece to a new square
	 * 
	 * @param s temporary square
	 */
	public void moveTo(Square s) {

		if (location != null) {
			location.setPiece(null);
			
		}

		location = s;

		if (s != null) {
			if (s.getPiece() != null) {
				s.getPiece().moveTo(null);
			}
			s.setPiece(this);
		}
	}

	/**
	 * returns type
	 * 
	 * @return PieceType
	 */
	public PieceType getType() {
		return null;
	}

	/**
	 * returns possible moves
	 * 
	 * @return MoveList
	 */
	public MoveList getPossibleMoves() {
		return null;
	}

	/**
	 * tests toString
	 * 
	 * @return empty string
	 */
	public String toString() {
		return "";
	}

	/**
	 * test canMoveTo
	 * 
	 * @param rank temporary rank
	 * @param file temporary file
	 * @return boolean
	 */
	protected boolean canMoveTo(int rank, char file) {
		if (location.getPiece() != null && location.getBoard() != null
				&& location.getBoard().getSquare(rank, file) != null) {

			if (location.getBoard().getSquare(rank, file).getPiece() == null) {
				return true;
			}
			if (location.getBoard().getSquare(rank, file).getPiece().isWhite() && !side) {
				return true;
			}
			if (location.getBoard().getSquare(rank, file).getPiece().isBlack() && side) {
				return true;
			}
		}
		return false;
	}

	/**
	 * adds move to moveList
	 * 
	 * @param list temporary list
	 * @param rank temporary rank
	 * @param file temporary file
	 * @return boolean
	 */
	protected boolean addMove(MoveList list, int rank, char file) {
		if (canMoveTo(rank, file)) {

			list.addMove(location.getBoard().getSquare(rank, file));
			if (location.getBoard().getSquare(rank, file).getPiece() == null) {
				return true;
			}
		}
		return false;
	}
}
