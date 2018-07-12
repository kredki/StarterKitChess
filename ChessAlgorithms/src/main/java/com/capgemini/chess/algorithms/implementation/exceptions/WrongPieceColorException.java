package com.capgemini.chess.algorithms.implementation.exceptions;

public class WrongPieceColorException extends InvalidMoveException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8467976250275869068L;

	public WrongPieceColorException() {
		super("Invalid move!");
	}
	
	public WrongPieceColorException(String message) {
		super("Invalid move! " + message);
	}
}
