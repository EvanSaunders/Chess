package org.example;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * displays a piece
 * 
 * @author evans
 * @version 12/7/2022
 */
public class PieceGUI extends ImageView {
	private ChessPiece piece;
	private Image img;

	/**
	 * displays corresponding piece
	 * 
	 * @param pieceTemp Temporary ChessPiece
	 */
	public PieceGUI(ChessPiece pieceTemp) {
		JFXPanel jfxPanel = new JFXPanel();

		piece = pieceTemp;
		if (piece.isWhite()) {
			if (piece.getType() == PieceType.BISHOP) {
				img = new Image("bishopWhite.png");
			} else {
				img = new Image("knightWhite.png");
			}
		} else {
			if (piece.getType() == PieceType.BISHOP) {
				img = new Image("bishopBlack.png");
			} else {
				img = new Image("knightBlack.png");
			}
		}

		setImage(img);
		setPreserveRatio(true);
	}

	/**
	 * returns chessPiece
	 * 
	 * @return piece
	 */
	public ChessPiece getPiece() {
		return piece;
	}

	// getClass().getResourceAsStream(
}
