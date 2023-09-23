package org.example;

import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Sets up a gui to control the board
 * 
 * @author evans
 * @version 12/12/2022
 */
public class ChessBoard extends Application implements SquareSelectionListener {
	private Board board = new Board(8, 'h');
	private BoardController bC = new BoardController(board);
	private BoardGUI bGUI = new BoardGUI(bC);

	private Stage stage;

	private ComboBox<PieceType> pieceTypeList;
	private RadioButton sideWhite;
	private RadioButton sideBlack;
	private ComboBox<Integer> rankList;
	private ComboBox<Character> fileList;
	// private Button addPiece, removePiece, clearPieces, save, load, quit;
	private ArrayList<Button> bList = new ArrayList<Button>();

	/**
	 * sets up the board
	 * 
	 * @param mainStage mainStage
	 */
	public void start(Stage mainStage) throws Exception {
		stage = mainStage;

		pieceTypeList = new ComboBox<PieceType>();
		pieceTypeList.setId("piece");
		pieceTypeList.setEditable(false);
		pieceTypeList.getItems().add(PieceType.BISHOP);
		pieceTypeList.getItems().add(PieceType.KNIGHT);
		pieceTypeList.getSelectionModel().select(PieceType.BISHOP);

		ToggleGroup tGroup = new ToggleGroup();
		sideWhite = new RadioButton("White");
		sideWhite.setToggleGroup(tGroup);
		sideWhite.setId("sidewhite");
		sideWhite.setSelected(true);

		sideBlack = new RadioButton("Black");
		sideBlack.setToggleGroup(tGroup);
		sideBlack.setId("sideblack");
		sideBlack.setSelected(false);

		rankList = new ComboBox<Integer>();
		rankList.setId("rank");
		for (int i = 1; i <= board.getMaxRank(); i++) {
			rankList.getItems().add(i);
		}
		rankList.getSelectionModel().select(0);

		fileList = new ComboBox<Character>();
		fileList.setId("file");
		for (char x = 'a'; x < board.getMaxFile() + 1; x++) {
			fileList.getItems().add((char) x);
		}
		fileList.getSelectionModel().select(0);

		Button addPiece = new Button("Add Piece");
		bList.add(addPiece);
		addPiece.setId("add");
		addPiece.setOnAction(this::onButtonClick);

		Button removePiece = new Button("Remove Piece");
		bList.add(removePiece);
		removePiece.setId("remove");
		removePiece.setDisable(true);
		removePiece.setOnAction(this::onButtonClick);

		Button clearPieces = new Button("Clear Pieces");
		bList.add(clearPieces);
		clearPieces.setId("clear");
		clearPieces.setOnAction(this::onButtonClick);

		Button save = new Button("Save");
		bList.add(save);
		save.setId("save");
		save.setOnAction(this::onButtonClick);

		Button load = new Button("load");
		bList.add(load);
		load.setId("load");
		load.setOnAction(this::onButtonClick);

		Button quit = new Button("quit");
		bList.add(quit);
		quit.setId("quit");
		quit.setOnAction(this::onButtonClick);

		mainStage.setTitle("Chess Game");

		BorderPane content = new BorderPane();
		FlowPane fpane = new FlowPane(5, 10);
		fpane.setMaxWidth(25);

		content.setCenter(bGUI);
		fpane.getChildren().addAll(pieceTypeList, new Label("Side:    "), sideWhite, sideBlack, new Label("    Rank"),
				rankList, new Label("       File"), fileList, addPiece, removePiece, clearPieces, save, load, quit);
		content.setRight(fpane);

		Scene mainScene = new Scene(content, 600, 500);

		mainStage.setMinWidth(587);
		mainStage.setMinHeight(520);
		mainStage.setMaxWidth(700);
		mainStage.setMaxHeight(600);

		mainStage.setScene(mainScene);

		mainStage.show();

		// FUNCTIONALITY
		bC.addListener(this);

	}

	/**
	 * returns board
	 * 
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * returns controller
	 * 
	 * @return bC
	 */
	public BoardController getController() {
		return bC;
	}

	@Override
	public void onSelectionChange(Square s, boolean b) {

		bList.get(1).setDisable(!b);

	}

	@Override
	public void onPossibleMoveChange(Square s, boolean b) {
		// does nothing
	}

	private void onButtonClick(ActionEvent e) {
		Object source = e.getSource();
		if (source instanceof Button) {
			if (source == bList.get(0)) {
				Button b = (Button) source;
				PieceType pT = pieceTypeList.getSelectionModel().getSelectedItem();
				int rank = rankList.getSelectionModel().getSelectedItem();
				char file = fileList.getSelectionModel().getSelectedItem();
				boolean side;
				if (sideWhite.isSelected()) {
					side = true;
				} else {
					side = false;
				}

				if (board.getSquare(rank, file).getPiece() == null) {
					if (pT == PieceType.BISHOP) {
						Bishop bish = new Bishop(side);
						bish.moveTo(board.getSquare(rank, file));
						bC.clearSelected();
					} else {
						Knight kngiht = new Knight(side);
						kngiht.moveTo(board.getSquare(rank, file));
						bC.clearSelected();
					}
				}
			}
			if (source == bList.get(1)) {
				bC.removeSelected();
			}
			if (source == bList.get(2)) {
				bC.resetBoard();

			}
			if (source == bList.get(3)) {
				BoardIO bIO = new BoardIO();
				FileChooser fChoose = new FileChooser();
				fChoose.getExtensionFilters().add(new ExtensionFilter("Text File", "*.txt"));
				File selectedFile = fChoose.showOpenDialog(stage);
				bIO.saveBoardState(board, selectedFile.getPath());
			}
			if (source == bList.get(4)) {
				BoardIO bIO = new BoardIO();
				FileChooser fChoose = new FileChooser();
				fChoose.getExtensionFilters().add(new ExtensionFilter("Text File", "*.txt"));
				File selectedFile = fChoose.showOpenDialog(stage);
				bIO.loadBoardState(board, selectedFile.getPath());
			}
			if (source == bList.get(5)) {
				stage.close();
			}
		}
	}

	/**
	 * launches the gui
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
