package org.example;

import java.util.ArrayList;

/**
 * Controls a board
 * 
 * @author evans
 * @version 11/13/2022
 */
public class BoardController {
	private Board b;
	private Square s;
	private MoveList mL;
	private ArrayList<SquareSelectionListener> list;

	/**
	 * constructor
	 * 
	 * @param bTemp temporary Board
	 */
	public BoardController(Board bTemp) {
		b = bTemp;
		s = null;
		mL = null;
		list = new ArrayList<SquareSelectionListener>();
	}

	/**
	 * returns board
	 * 
	 * @return board
	 */
	public Board getBoard() {
		return b;
	}

	/**
	 * returns s
	 * 
	 * @return s
	 */
	public Square getSelected() {
		return s;
	}

	/**
	 * returns mL
	 * 
	 * @return mL
	 */
	public MoveList getPossibleMoves() {
		return mL;
	}

	/**
	 * adds a Piece to ml
	 * 
	 * @param pT   temporary pieceType
	 * @param side temporary side
	 * @param rank temporary rank
	 * @param file temporary file
	 */
	public void addPiece(PieceType pT, boolean side, int rank, char file) {
		ChessPiece piece = PieceFactory.createPiece(pT, side);
		piece.moveTo(b.getSquare(rank, file));
	}

	/**
	 * sets variables to null
	 */
	public void clearSelected() {
		if (s != null) {

			loopOSC(s, false);
			s = null;

			loopOPMC(mL, false);
			mL = null;
		}
	}

	private void loopOSC(Square sTemp, boolean bool) {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).onSelectionChange(sTemp, bool);
		}
	}

	private void loopOPMC(MoveList mLTemp, boolean bool) {
		for (int i = 0; i < list.size(); i++) {
			for (int x = 0; x < mLTemp.getNumMoves(); x++) {
				list.get(i).onPossibleMoveChange(mLTemp.getMove(x), bool);
			}
		}
	}

	/**
	 * selects a certain square
	 * 
	 * @param rank temporary rank
	 * @param file temporary file
	 */
	public void select(int rank, char file) {

		if (b.getSquare(rank, file) != null && b.getSquare(rank, file).getPiece() != null) {
			if (b.getSquare(rank, file) != s) {
				clearSelected();
				s = b.getSquare(rank, file);
				loopOSC(s, true);

				mL = s.getPiece().getPossibleMoves();
				loopOPMC(mL, true);

			} else {
				clearSelected();
			}

		} else {
			clearSelected();
		}

		// else if (b.getSquare(rank, file).getPiece() == null) {
		// clearSelected();
		// }
	}

	/**
	 * moves the selection
	 * 
	 * @param rank temporary rank
	 * @param file temporary file
	 * @return true or false
	 */
	public boolean moveSelected(int rank, char file) {
		if (s != null && mL.findMove(rank, file) != null) {
			s.getPiece().moveTo(mL.findMove(rank, file));
			clearSelected();
			return true;
		}
		return false;
	}

	/**
	 * removes the selection
	 */
	public void removeSelected() {
		if (s != null) {
			s.getPiece().moveTo(null);
			clearSelected();
		}
	}

	/**
	 * resets the board
	 */
	public void resetBoard() {
		clearSelected();
		b.clearBoard();
	}

	/**
	 * adds a listener to the arrayList
	 * 
	 * @param sSL squareSelectionListener
	 */
	public void addListener(SquareSelectionListener sSL) {
		list.add(sSL);
	}

	/**
	 * removes a listener from the arrayList
	 * 
	 * @param sSL squareSelectionListener
	 */
	public void removeListener(SquareSelectionListener sSL) {
		list.remove(sSL);
	}
}
