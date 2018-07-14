package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.PieceNotThereException;

public class PieceIsThereValidator implements Validator {
	private Coordinate from;
	private Board board;
	
	public PieceIsThereValidator(Coordinate from, Board board) {
		super();
		this.from = from;
		this.board = board;
	}

	@Override
	public Move validate() throws InvalidMoveException {
		validateIfPieceIsThere();
		return null;
	}

	private boolean validateIfPieceIsThere() throws PieceNotThereException {
		if(board.getPieceAt(this.from) != null) {
			return true;
		}
		throw new PieceNotThereException();
	}
}
