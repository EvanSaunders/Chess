package org.example;

/**
 * creates ChessPieces
 * 
 * @author evans
 * @version 11/13/2022
 */
public class PieceFactory {
	/**
	 * does nothing
	 */
	private PieceFactory() {
		// Do Nothing
	}

	/**
	 * creates chess pieces
	 * 
	 * @param type what type of piece
	 * @param side what side
	 * @return ChessPiece
	 */
	public static ChessPiece createPiece(PieceType type, boolean side) {
		if (type == PieceType.KNIGHT) {
			return new Knight(side);
		} else {
			return new Bishop(side);
		}

	}
}
