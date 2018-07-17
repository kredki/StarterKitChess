package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingOnToException;

public class KingOnToValidator implements Validator {
	private Coordinate to;
	private Board board;

	public KingOnToValidator(Coordinate to, Board board) {
		super();
		this.to = to;
		this.board = board;
	}

	@Override
	public Move validate() throws InvalidMoveException {
		Piece piece = board.getPieceAt(to);
		if(piece == null) {
			return null;
		}
		
		if (piece.getType().equals(PieceType.KING)) {
			throw new KingOnToException();
		} else {
			return null;
		}
	}
}
