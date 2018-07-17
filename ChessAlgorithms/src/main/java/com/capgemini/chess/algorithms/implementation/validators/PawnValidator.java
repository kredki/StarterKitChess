package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PawnValidator implements Validator {
	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color actualPlayerColor;
	private static final int DELTA_X_WHITE_ATTACK = 0;
	private static final int DELTA_Y_WHITE_ATTACK = 1;
	private static final int DELTA_X_WHITE_CAPTURE = 1;
	private static final int DELTA_Y_WHITE_CAPTURE = 1;
	private static final int DELTA_X_WHITE_FIRST_MOVE = 0;
	private static final int DELTA_Y_WHITE_FIRST_MOVE = 2;
	
	private static final int DELTA_X_BLACK_ATTACK = 0;
	private static final int DELTA_Y_BLACK_ATTACK = -1;
	private static final int DELTA_X_BLACK_CAPTURE = 1;
	private static final int DELTA_Y_BLACK_CAPTURE = -1;
	private static final int DELTA_X_BLACK_FIRST_MOVE = 0;
	private static final int DELTA_Y_BLACK_FIRST_MOVE = -2;

	public PawnValidator(Coordinate from, Coordinate to, Board board, Color actualPlayerColor) {
		super();
		this.from = from;
		this.to = to;
		this.board = board;
		this.actualPlayerColor = actualPlayerColor;
	}

	@Override
	public Move validate() throws InvalidMoveException {
		if (actualPlayerColor.equals(Color.WHITE)) {
			return validateWhite();
		} else {
			return validateBlack();
		}
	}

	private Move validateWhite() throws InvalidMoveException {
		int deltaX = this.to.getX() - this.from.getX();
		int deltaY = this.to.getY() - this.from.getY();
		int absDeltaX = Math.abs(this.to.getX() - this.from.getX());
		
		if (isWhiteAttack(deltaX, deltaY)) {
			return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
		} else if (isWhiteCapture(absDeltaX, deltaY)) {
			return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
		} else if (isWhiteFirstMove(deltaX, deltaY)) {
			return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
		} else {
			throw new InvalidMoveException();
		}
	}
	
	private boolean isWhiteAttack(int deltaX, int deltaY) {
		return (deltaX == DELTA_X_WHITE_ATTACK && deltaY == DELTA_Y_WHITE_ATTACK
				&& board.getPieceAt(this.to) == null);
	}
	
	private boolean isWhiteCapture(int absDeltaX, int deltaY) {
		return (absDeltaX == DELTA_X_WHITE_CAPTURE && deltaY == DELTA_Y_WHITE_CAPTURE
				&& board.getPieceAt(this.to) != null);
	}
	
	private boolean isWhiteFirstMove(int deltaX, int deltaY) {
		return (deltaY == DELTA_Y_WHITE_FIRST_MOVE && deltaX == DELTA_X_WHITE_FIRST_MOVE
				&& board.getPieceAt(this.to) == null);
	}

	private Move validateBlack() throws InvalidMoveException {
		int deltaX = this.to.getX() - this.from.getX();
		int deltaY = this.to.getY() - this.from.getY();
		int absDeltaX = Math.abs(this.to.getX() - this.from.getX());
		
		if (isBlackAttack(deltaX, deltaY)) {
			return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
		} else if (isBlackCapture(absDeltaX, deltaY)) {
			return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
		} else if (isBlackFirstMove(deltaX, deltaY)) {
			return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
		} else {
			throw new InvalidMoveException();
		}
	}
	
	private boolean isBlackAttack(int deltaX, int deltaY) {
		return (deltaX == DELTA_X_BLACK_ATTACK && deltaY == DELTA_Y_BLACK_ATTACK
				&& board.getPieceAt(this.to) == null);
	}
	
	private boolean isBlackCapture(int absDeltaX, int deltaY) {
		return (absDeltaX == DELTA_X_BLACK_CAPTURE && deltaY == DELTA_Y_BLACK_CAPTURE
				&& board.getPieceAt(this.to) != null);
	}
	
	private boolean isBlackFirstMove(int deltaX, int deltaY) {
		return (deltaY == DELTA_Y_BLACK_FIRST_MOVE && deltaX == DELTA_X_BLACK_FIRST_MOVE
				&& board.getPieceAt(this.to) == null);
	}
}
