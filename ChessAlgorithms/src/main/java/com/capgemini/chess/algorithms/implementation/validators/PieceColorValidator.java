package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.WrongPieceColorException;

public class PieceColorValidator implements Validator {
	private Coordinate from;
	private Board board;
	private Color actualPlayerColor;

	public PieceColorValidator(Coordinate from, Board board, Color actualPlayerColor) {
		super();
		this.from = from;
		this.board = board;
		this.actualPlayerColor = actualPlayerColor;
	}

	@Override
	public Move validate() throws InvalidMoveException {
		validatePieceColor();
		return null;
	}

	private boolean validatePieceColor() throws WrongPieceColorException {
		if(board.getPieceAt(this.from).getColor() == this.actualPlayerColor) {
			return true;
		}
		throw new WrongPieceColorException();
	}
}
