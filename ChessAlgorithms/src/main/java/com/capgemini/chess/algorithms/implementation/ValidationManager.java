package com.capgemini.chess.algorithms.implementation;

import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.CoordinateNotOnBoard;
import com.capgemini.chess.algorithms.implementation.exceptions.CoordinateOccupiedByMyPieceException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.PieceNotThereException;
import com.capgemini.chess.algorithms.implementation.exceptions.WrongPieceColorException;

public class ValidationManager {
	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color actualPlayerColor;
	
	public ValidationManager(Coordinate from, Coordinate to, Board board, Color actualPlayerColor) {
		super();
		this.from = from;
		this.to = to;
		this.board = board;
		this.actualPlayerColor = actualPlayerColor;
	}
	
	public Move validate() throws InvalidMoveException {		
		ValidatorFactory validatorFactory = new ValidatorFactory(this.from, this.to,
				this.board, this.actualPlayerColor);
		List<Validator> validators = validatorFactory.getValidators();
		Move move = null;
		for (Validator validator : validators) {
			move = validator.validate();
		}
		return move;
	}
}
