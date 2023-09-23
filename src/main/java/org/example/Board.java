package org.example;

/**
 * Creates a board object and allows for some functions
 * 
 * @author evans
 * @version 10/18/2022
 */
public class Board {
	private Square[] arr;
	private int maxRank;
	private char maxFile;

	/**
	 * Constructor for Board object
	 * 
	 * @param maxRankTemp temporary maxRank
	 * @param maxFileTemp temporary maxFile
	 * @throws IllegalArgumentException if maxRank or maxFile are out of bounds
	 */
	public Board(int maxRankTemp, char maxFileTemp) throws IllegalArgumentException {
		if (maxRankTemp > 26 || maxRankTemp < 1 || maxFileTemp < 'a' || maxFileTemp > 'z') {
			throw new IllegalArgumentException();
		}

		maxRank = maxRankTemp;
		maxFile = maxFileTemp;

		arr = new Square[maxRank * (int) (maxFile - 'a' + 1)];

		int count = 0;

		for (int i = 1; i <= maxRank; i++) {
			for (char x = 'a'; x <= maxFile; x++) {
				Square temp = new Square(this, i, x);
				arr[count] = temp;
				count++;
			}
		}
	}

	/**
	 * returns maxRank
	 * 
	 * @return maxRank
	 */
	public int getMaxRank() {
		return maxRank;
	}

	/**
	 * returns maxFile
	 * 
	 * @return maxFile
	 */
	public char getMaxFile() {
		return maxFile;
	}

	/**
	 * returns minRank
	 * 
	 * @return minRank
	 */
	public int getMinRank() {
		return 1;
	}

	/**
	 * returns minFile
	 * 
	 * @return minFile
	 */
	public char getMinFile() {
		return 'a';
	}

	/**
	 * Returns a square at a specific place on the board
	 * 
	 * @param rankTemp what rank to be retrieved from
	 * @param fileTemp what file to be retrieved from
	 * @return square
	 */
	public Square getSquare(int rankTemp, char fileTemp) {
		if (rankTemp > 0 && rankTemp <= maxRank && fileTemp >= 'a' && fileTemp <= maxFile) {
			int a = rankTemp - 1;
			int b = fileTemp - 'a';
			int c = maxFile - 'a' + 1;
			
			return arr[(a * c) + b];

		}
		return null;
	}

	/**
	 * Clears the board of all pieces
	 */
	public void clearBoard() {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getPiece() != null) {
				arr[i].getPiece().moveTo(null);
				arr[i].setPiece(null); 
			}
		}
	}
}
