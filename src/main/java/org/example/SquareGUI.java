package org.example;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * creates a square that displays rank and file
 * 
 * @author evans
 * @version 12/7/2022
 */
public class SquareGUI extends Pane {
	private Square s;
	private PieceGUI pGUI;
	private String bGC;

	/**
	 * creates a squareGUI with rank and file
	 * 
	 * @param sTemp temporary square
	 */
	public SquareGUI(Square sTemp) {
		s = sTemp;
		pGUI = null;
		Text rankDisplay = new Text("" + s.getRank());
		Text fileDisplay = new Text("" + s.getFile());

		if (s.getRank() % 2 == 0 && (s.getFile() - 'a' + 1) % 2 == 0
				|| s.getRank() % 2 != 0 && (s.getFile() - 'a' + 1) % 2 != 0) {
			bGC = "-fx-background-color: white;";
			rankDisplay.setFill(Color.BLACK);
			fileDisplay.setFill(Color.BLACK);
			setStyle(bGC);
		} else {
			bGC = "-fx-background-color: black;";
			rankDisplay.setFill(Color.WHITE);
			fileDisplay.setFill(Color.WHITE);
			setStyle(bGC);
		}

		fileDisplay.setY(10);
		rankDisplay.setY(58);
		rankDisplay.setX(50);

		getChildren().addAll(rankDisplay, fileDisplay);
		setMinWidth(25);
		setMinHeight(25);
		setPrefWidth(60);
		setPrefHeight(60);

		setOnMouseEntered(this::mouseEntered);
		setOnMouseExited(this::mouseExited);
	}

	/**
	 * adds a PieceGui to the square
	 * 
	 * @param p PieceGui to add
	 */
	public void addPieceGUI(PieceGUI p) {
		getChildren().add(p);
		p.setFitHeight(50);
		p.setFitWidth(50);
	}

	/**
	 * pieceGUI to remove
	 */
	public void removePieceGUI() {
		getChildren().remove(getChildren().size() - 1);
		pGUI = null;
	}

	/**
	 * sets square to green if selected
	 * 
	 * @param bool to be colored or not
	 */

	public void setHighlighted(boolean bool) {
		if (bool) {
			setStyle("-fx-background-color : green");
		} else {
			setStyle(bGC);
		}
	}

	/**
	 * sets square to light blue if selected
	 * 
	 * @param bool to be colored or not
	 */
	public void setSelected(boolean bool) {
		if (bool) {
			setStyle("-fx-background-color : lightblue");
		} else {
			setStyle(bGC);
		}

	}

	/**
	 * sets square to grey if mouse is hovering
	 * 
	 * @param m mouse event
	 */
	private void mouseEntered(MouseEvent m) {
		if (!getStyle().equals("-fx-background-color : green")
				&& !getStyle().equals("-fx-background-color : lightblue")) {
			setStyle("-fx-background-color : grey");
		}
	}

	/**
	 * sets square back to normal after mouse is done hovering
	 * 
	 * @param m
	 */
	private void mouseExited(MouseEvent m) {
		if (!getStyle().equals("-fx-background-color : green")
				&& !getStyle().equals("-fx-background-color : lightblue")) {
			setStyle(bGC);
		}
	}

	/**
	 * returns square
	 * 
	 * @return square
	 */
	public Square getSquare() {
		return s;
	}
}
