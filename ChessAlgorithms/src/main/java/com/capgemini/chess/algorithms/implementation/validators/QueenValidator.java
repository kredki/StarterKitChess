package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class QueenValidator implements Validator {
	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color actualPlayerColor;

	public QueenValidator(Coordinate from, Coordinate to, Board board, Color actualPlayerColor) {
		super();
		this.from = from;
		this.to = to;
		this.board = board;
		this.actualPlayerColor = actualPlayerColor;
	}

	@Override
	public Move validate() throws InvalidMoveException {
		int deltaX = Math.abs(this.to.getX() - this.from.getX());
		int deltaY = Math.abs(this.to.getY() - this.from.getY());
		if (((deltaX == 0 && deltaY > 0) || (deltaX > 0 && deltaY == 0)) ||
				(deltaX == deltaY)) {
			return null;
		} else {
			throw new InvalidMoveException();
		}
	}

}
