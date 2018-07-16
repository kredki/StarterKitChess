package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class BishopValidator implements Validator {

	public BishopValidator(Coordinate from, Coordinate to, Board board, Color actualPlayerColor) {
		super();
		this.from = from;
		this.to = to;
		this.board = board;
		this.actualPlayerColor = actualPlayerColor;
	}

	@Override
	public Move validate() throws InvalidMoveException {
		// TODO Auto-generated method stub
		return null;
	}

}
