package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.validators.*;

public class ValidatorFactory {
	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color actualPlayerColor;

	public ValidatorFactory(Coordinate from, Coordinate to, Board board, Color actualPlayerColor) {
		super();
		this.from = from;
		this.to = to;
		this.board = board;
		this.actualPlayerColor = actualPlayerColor;
	}

	public List<Validator> getValidators() {
		List<Validator> validators = new ArrayList<>();
		validators.add(new CoordinatesValidator(this.from, this.to));
		validators.add(new PieceIsThereValidator(this.from, this.board));
		validators.add(new PieceColorValidator(this.from, this.board, this.actualPlayerColor));
		validators.add(new OccupiedByMyPieceValidator(this.to, this.board, this.actualPlayerColor));
		validators.add(getPieceValidator());
		return validators;
	}

	private Validator getPieceValidator() {
		Validator validator = null;
		PieceType pieceType = board.getPieceAt(this.from).getType();
		switch (pieceType) {
		case KING:
			validator = new KingValidator(this.from, this.to, this.board, this.actualPlayerColor);
			break;
		case QUEEN:
			validator = new QueenValidator(this.from, this.to, this.board, this.actualPlayerColor);
			break;
		case BISHOP:
			validator = new BishopValidator(this.from, this.to, this.board, this.actualPlayerColor);
			break;
		case KNIGHT:
			validator = new KnightValidator(this.from, this.to, this.board, this.actualPlayerColor);
			break;
		case ROOK:
			validator = new RookValidator(this.from, this.to, this.board, this.actualPlayerColor);
			break;
		case PAWN:
			validator = new PawnValidator(this.from, this.to, this.board, this.actualPlayerColor);
			break;

		default:
			break;
		}
		return validator;
	}
}
