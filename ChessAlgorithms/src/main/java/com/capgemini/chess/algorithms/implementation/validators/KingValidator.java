package com.capgemini.chess.algorithms.implementation.validators;

import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class KingValidator implements Validator {
	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color actualPlayerColor;

	private static final int WHITE_FIRST_ROW = 0;
	private static final int BLACK_FIRST_ROW = 7;
	private static final int WHITE_KING_START_X = 4;
	private static final int WHITE_KING_START_Y = 0;
	private static final int BLACK_KING_START_X = 4;
	private static final int BLACK_KING_START_Y = 7;
	private static final int KING_START_Y = 4;
	private static final int BOARD_MAX_SIZE = 7;
	private static final int BOARD_MIN_SIZE = 0;
	private static final int KING_START_X = 4;

	public KingValidator(Coordinate from, Coordinate to, Board board, Color actualPlayerColor) {
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
		if (deltaX < 2 && deltaY < 2) {
			Piece pieceAtTo = board.getPieceAt(to);
			if (pieceAtTo != null) {
				return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
			} else {
				return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
			}
		} else if (deltaX == 2 && deltaY == 0) {
			return validateCastling();
		} else {
			throw new InvalidMoveException();
		}
	}

	private Move validateCastling() throws InvalidMoveException {
		if (this.actualPlayerColor.equals(Color.WHITE)) {
			return validateWhiteKingCastling();
		} else {
			return validateBlackKingCastling();
		}
	}

	private Move validateWhiteKingCastling() throws InvalidMoveException {
		if (this.from.getY() == WHITE_FIRST_ROW && this.from.getX() == WHITE_KING_START_X
				&& this.from.getY() == WHITE_KING_START_Y && isSpacesEmptyForCastling()) {
			checkWhiteHistory();
			// todo check validation for passing space
			return new Move(this.from, this.to, MoveType.CASTLING, this.board.getPieceAt(this.from));
		}
		throw new InvalidMoveException();
	}

	private Move validateBlackKingCastling() throws InvalidMoveException {
		if (this.from.getY() == BLACK_FIRST_ROW && this.from.getX() == BLACK_KING_START_X
				&& this.from.getY() == BLACK_KING_START_Y && isSpacesEmptyForCastling()) {
			checkBlackHistory();
			// todo check validation for passing space
			return new Move(this.from, this.to, MoveType.CASTLING, this.board.getPieceAt(this.from));
		}
		throw new InvalidMoveException();
	}

	private boolean isSpacesEmptyForCastling() {
		int deltaX = this.to.getX() - this.from.getX();
		int y = this.from.getY();
		if (deltaX > 0) {
			for (int x = KING_START_X + 1; x < BOARD_MAX_SIZE; x++) {
				if (this.board.getPieceAt(new Coordinate(x, y)) != null) {
					return false;
				}
			}
		} else {
			for (int x = KING_START_X + 1; x > BOARD_MIN_SIZE; x++) {
				if (this.board.getPieceAt(new Coordinate(x, y)) != null) {
					return false;
				}
			}
		}
		return true;
	}

	private void checkWhiteHistory() throws InvalidMoveException {
		int deltaX = this.to.getX() - this.from.getX();
		List<Move> moveHistory = this.board.getMoveHistory();
		if (deltaX > 0) {
			for (Move move : moveHistory) {
				int x = move.getFrom().getX();
				int y = move.getFrom().getY();
				if (y == BOARD_MIN_SIZE && (x == BOARD_MAX_SIZE || x == WHITE_KING_START_X)) {
					throw new InvalidMoveException();
				}
			}
		} else {
			for (Move move : moveHistory) {
				int x = move.getFrom().getX();
				int y = move.getFrom().getY();
				if (y == BOARD_MIN_SIZE && (x == BOARD_MIN_SIZE || x == WHITE_KING_START_X)) {
					throw new InvalidMoveException();
				}
			}
		}
	}

	private void checkBlackHistory() throws InvalidMoveException {
		int deltaX = this.to.getX() - this.from.getX();
		List<Move> moveHistory = this.board.getMoveHistory();
		if (deltaX > 0) {
			for (Move move : moveHistory) {
				int x = move.getFrom().getX();
				int y = move.getFrom().getY();
				if (y == BOARD_MAX_SIZE && (x == BOARD_MAX_SIZE || x == WHITE_KING_START_X)) {
					throw new InvalidMoveException();
				}
			}
		} else {
			for (Move move : moveHistory) {
				int x = move.getFrom().getX();
				int y = move.getFrom().getY();
				if (y == BOARD_MAX_SIZE && (x == BOARD_MIN_SIZE || x == WHITE_KING_START_X)) {
					throw new InvalidMoveException();
				}
			}
		}
	}
}
