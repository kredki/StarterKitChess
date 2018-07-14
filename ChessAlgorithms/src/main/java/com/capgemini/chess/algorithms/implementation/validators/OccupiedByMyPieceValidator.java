package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.CoordinateOccupiedByMyPieceException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class OccupiedByMyPieceValidator implements Validator {
	private Coordinate to;
	private Board board;
	private Color actualPlayerColor;

	public OccupiedByMyPieceValidator(Coordinate to, Board board, Color actualPlayerColor) {
		super();
		this.to = to;
		this.board = board;
		this.actualPlayerColor = actualPlayerColor;
	}

	@Override
	public Move validate() throws InvalidMoveException {
		isToNotOccupiedByMyPiece();
		return null;
	}

	private boolean isToNotOccupiedByMyPiece() throws CoordinateOccupiedByMyPieceException {
		Piece piece = board.getPieceAt(this.to);
		if(piece == null || piece.getColor() != this.actualPlayerColor) {
			return true;
		}
		throw new CoordinateOccupiedByMyPieceException();
	}
}
