package org.example;

/**
 * enum of PieceType
 * 
 * @author evans
 * @version 11/13/2022
 */
public enum PieceType {
	/**
	 * Bishop type
	 */
	BISHOP,
	/**
	 * Knight type
	 */
	KNIGHT;

	/**
	 * tests toString
	 * 
	 * @return string
	 */
	public String toString() {
		if (this == BISHOP) {
			return "Bishop";
		} else {
			return "Knight";
		}

	}
}
