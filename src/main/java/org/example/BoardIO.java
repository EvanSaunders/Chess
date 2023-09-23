package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Loads and saves chess boards
 * 
 * @author evans
 * @version 10/18/2022
 */
public class BoardIO {
	/**
	 * Loads a board from a board and a scanner
	 * 
	 * @param boardTemp temporary board
	 * @param s         scanner for file
	 * @return true if successful, false if not
	 */
	public boolean loadBoardState(Board boardTemp, Scanner s) {

		if (boardTemp != null && s != null) {
			boardTemp.clearBoard();
			while (s.hasNext()) {
				String type;
				String side;
				int rank;
				String file;
				String line = s.nextLine();
				Scanner lineScan = new Scanner(line);

				try {
					while (lineScan.hasNext()) {
						type = lineScan.next();
						side = lineScan.next();
						rank = lineScan.nextInt();
						file = lineScan.next();

						if (side.equals("white")) {
							// ChessPiece piecePlacer = new ChessPiece(true);
							ChessPiece piecePlacer = PieceFactory.createPiece(PieceType.valueOf(type), true);
							piecePlacer.moveTo(boardTemp.getSquare(rank, file.charAt(0)));

						} else {
							ChessPiece piecePlacer = PieceFactory.createPiece(PieceType.valueOf(type), false);
							piecePlacer.moveTo(boardTemp.getSquare(rank, file.charAt(0)));
						}
					}
				} catch (NoSuchElementException e) {
					// Do nothing
					System.out.println("Improper file entry");
				}
				lineScan.close();

			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Loads a board from a board and a fileName
	 * 
	 * @param boardTemp temporary board
	 * @param fileName  provided fileName
	 * @return true if successful, false if not
	 */
	public boolean loadBoardState(Board boardTemp, String fileName) {

		Scanner s = null;
		try {
			File file = new File(fileName);
			s = new Scanner(file);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (boardTemp != null) {
			boardTemp.clearBoard();
			while (s.hasNext()) {
				String type;
				String side;
				int rank;
				String file;
				String line = s.nextLine();
				Scanner lineScan = new Scanner(line);

				try {
					while (lineScan.hasNext()) {
						type = lineScan.next();
						side = lineScan.next();
						rank = lineScan.nextInt();
						file = lineScan.next();

						if (side.equals("white")) {
							// ChessPiece piecePlacer = new ChessPiece(true);
							ChessPiece piecePlacer = PieceFactory.createPiece(PieceType.valueOf(type), true);
							piecePlacer.moveTo(boardTemp.getSquare(rank, file.charAt(0)));

						} else {
							ChessPiece piecePlacer = PieceFactory.createPiece(PieceType.valueOf(type), false);
							piecePlacer.moveTo(boardTemp.getSquare(rank, file.charAt(0)));
						}
					}
				} catch (NoSuchElementException e) {
					System.out.println("Improper file entry");
				}
				lineScan.close();
			}
			s.close();
			return true;
		} else {
			s.close();
			return false;
		}

	}

	/**
	 * Saves a board with a board and a writer
	 * 
	 * @param boardTemp temporary board
	 * @param writer    writer provided
	 * @return true if successful, false if not
	 */
	public boolean saveBoardState(Board boardTemp, PrintWriter writer) {

		if (boardTemp == null || writer == null) {
			return false;
		}
		for (int i = 1; i <= boardTemp.getMaxRank(); i++) {
			for (char x = 'a'; x <= boardTemp.getMaxFile(); x++) {
				if (boardTemp.getSquare(i, x).getPiece() != null) {
					writer.print(boardTemp.getSquare(i, x).getPiece().getType().name() + " ");

					if (boardTemp.getSquare(i, x).getPiece().isWhite()) {
						writer.print("white ");

					} else {
						writer.print("black ");
					}
					writer.print(boardTemp.getSquare(i, x).getRank() + " ");

					writer.println(boardTemp.getSquare(i, x).getFile());

				}
			}
		}
		writer.close();
		return true;
	}

	/**
	 * Saves a board with a board and a fileName
	 * 
	 * @param boardTemp temporary board
	 * @param fileName  provided fileName
	 * @return true if successful, false if not
	 */
	public boolean saveBoardState(Board boardTemp, String fileName) {
		if (boardTemp == null || fileName == null) {
			return false;
		}
		File f = new File(fileName);

		try {
			PrintWriter writer = new PrintWriter(f);
			this.saveBoardState(boardTemp, writer);
		} catch (FileNotFoundException e) {
			return false;
		}
		return true;

	}
}
