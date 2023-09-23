package org.example;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * sets up boardGUI
 * 
 * @author evans
 * @version 12/6/2022
 */
public class BoardGUI extends GridPane implements SquareSelectionListener, SquareMoveListener {
	private BoardController bC;
	private ArrayList<SquareGUI> sList;
	private ArrayList<PieceGUI> pList;
	private Board b;
	private SquareSelectionListener sSL;

	/**
	 * Sets up a board full of squareGUIs
	 * 
	 * @param bCTemp temp boardController
	 */
	public BoardGUI(BoardController bCTemp) {
		bC = bCTemp;
		b = bC.getBoard();
		sList = new ArrayList<SquareGUI>();
		pList = new ArrayList<PieceGUI>();
		setPrefWidth(500);
		setPrefHeight(500);

		bC.addListener(this);
		for (char x = 'a'; x < b.getMaxFile() + 1; x++) {
			for (int i = 0; i < b.getMaxRank(); i++) {

				Square tempS = b.getSquare(i + 1, x);
				System.out.println(tempS);
				SquareGUI sG = new SquareGUI(tempS);
				sG.setId("sq_" + ((char) (x)) + (i + 1));
				System.out.println(sG.getId());
				sList.add(sG);
				this.add(sG, x - 'a', b.getMaxRank() - i);
				tempS.setListener(this);
				sG.setOnMouseClicked(this::mouseClicked);
			}
		}

	}

	/**
	 * gets Board
	 * 
	 * @return b
	 */
	public Board getBoard() {
		return b;
	}

	/**
	 * finds a the SquareGUI the square is in
	 * 
	 * @param sTemp temporary square
	 * @return SquareGUI
	 */
	private SquareGUI sGUILookup(Square sTemp) {
		for (int i = 0; i < sList.size(); i++) {
			if (sList.get(i).getSquare() == sTemp) {
				return sList.get(i);
			}
		}
		return null;
	}

	/**
	 * finds a the PieceGUI the piece is in
	 * 
	 * @param pTemp temporary piece
	 * @return PieceGUI
	 */
	private PieceGUI pGUILookup(ChessPiece pTemp) {
		for (int i = 0; i < pList.size(); i++) {
			if (pList.get(i).getPiece() == pTemp) {
				return pList.get(i);
			}
		}
		return null;
	}

	/**
	 * what to do when selection changes
	 * 
	 * @param s    square
	 * @param bool boolean
	 */
	@Override
	public void onSelectionChange(Square s, boolean bool) {
		if (sGUILookup(s) != null) {
			sGUILookup(s).setSelected(bool);
		}
	}

	/**
	 * what to do when there is a possible move change
	 * 
	 * @param s    square
	 * @param bool boolean
	 */
	@Override
	public void onPossibleMoveChange(Square s, boolean bool) {
		if (sGUILookup(s) != null) {
			sGUILookup(s).setHighlighted(bool);
		}
	}

	/**
	 * what to do when a piece is moved off
	 * 
	 * @param s     square
	 * @param piece ChessPiece
	 */
	@Override
	public void onMovedOff(ChessPiece piece, Square s) {
		if (sGUILookup(s) != null) {
			sGUILookup(s).removePieceGUI();
		}
	}

	/**
	 * what to do when a piece is moved on
	 * 
	 * @param piece ChessPiece
	 * @param s     Square
	 */
	@Override
	public void onMovedOn(ChessPiece piece, Square s) {

		if (pGUILookup(piece) == null) {
			PieceGUI pGUITemp = new PieceGUI(piece);
			pList.add(pGUITemp);

		}
		sGUILookup(s).addPieceGUI(pGUILookup(piece));
	}

	/**
	 * what to do when mouse is Clicked
	 * 
	 * @param m mouseEvent
	 */
	private void mouseClicked(MouseEvent m) {
		int rank = ((SquareGUI) m.getSource()).getSquare().getRank();
		char file = ((SquareGUI) m.getSource()).getSquare().getFile();
		if (!bC.moveSelected(rank, file)) {
			bC.select(rank, file);
		}
	}
}
