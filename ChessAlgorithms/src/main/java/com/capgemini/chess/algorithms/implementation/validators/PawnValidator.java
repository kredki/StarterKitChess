package com.capgemini.chess.algorithms.implementation.validators;

import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.SpaceBetweenNotEmpty;

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
	private static final int WHITE_FIRST_ROW = 1;
	private static final int WHITE_ENPASSANT_ROW = 4;

	private static final int DELTA_X_BLACK_ATTACK = 0;
	private static final int DELTA_Y_BLACK_ATTACK = -1;
	private static final int DELTA_X_BLACK_CAPTURE = 1;
	private static final int DELTA_Y_BLACK_CAPTURE = -1;
	private static final int DELTA_X_BLACK_FIRST_MOVE = 0;
	private static final int DELTA_Y_BLACK_FIRST_MOVE = -2;
	private static final int BLACK_FIRST_ROW = 6;
	private static final int BLACK_ENPASSANT_ROW = 3;

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

		if (isWhiteEnPassant(absDeltaX, deltaY)) {
			return new Move(from, to, MoveType.EN_PASSANT, board.getPieceAt(from));
		} else if (isWhiteAttack(deltaX, deltaY)) {
			return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
		} else if (isWhiteCapture(absDeltaX, deltaY)) {
			return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
		} else if (isWhiteFirstMove(deltaX, deltaY)) {
			if (isSpaceBetweenEmpty(Color.WHITE)) {
				return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
			} else {
				throw new SpaceBetweenNotEmpty();
			}
		} else {
			throw new InvalidMoveException();
		}
	}

	private boolean isWhiteEnPassant(int absDeltaX, int deltaY) {
		if (this.from.getY() == WHITE_ENPASSANT_ROW && absDeltaX == DELTA_X_WHITE_CAPTURE
				&& deltaY == DELTA_Y_WHITE_CAPTURE && board.getPieceAt(this.to) == null) {
			if (checkWhiteHistory()) {
				return true;
			}
		}
		return false;
	}

	private boolean checkWhiteHistory() {
		List<Move> moveHistory = this.board.getMoveHistory();
		if (!moveHistory.isEmpty()) {
			Move lastMove = moveHistory.get(moveHistory.size() - 1);
			Piece lastMovedPiece = lastMove.getMovedPiece();
			Coordinate lastMoveFrom = lastMove.getFrom();
			Coordinate lastMoveTo = lastMove.getTo();
			int deltaXBetweenPieces = Math.abs(lastMoveTo.getX() - this.from.getX());
			if (lastMovedPiece.getType().equals(PieceType.PAWN) && lastMovedPiece.getColor().equals(Color.BLACK)
					&& lastMoveFrom.getY() == BLACK_FIRST_ROW && deltaXBetweenPieces == 1
					&& lastMoveTo.getY() == WHITE_ENPASSANT_ROW) {
				return true;
			}
		}
		return false;
	}

	private boolean isWhiteAttack(int deltaX, int deltaY) {
		return (deltaX == DELTA_X_WHITE_ATTACK && deltaY == DELTA_Y_WHITE_ATTACK && board.getPieceAt(this.to) == null);
	}

	private boolean isWhiteCapture(int absDeltaX, int deltaY) {
		return (absDeltaX == DELTA_X_WHITE_CAPTURE && deltaY == DELTA_Y_WHITE_CAPTURE
				&& board.getPieceAt(this.to) != null);
	}

	private boolean isWhiteFirstMove(int deltaX, int deltaY) {
		return (this.from.getY() == WHITE_FIRST_ROW && deltaY == DELTA_Y_WHITE_FIRST_MOVE
				&& deltaX == DELTA_X_WHITE_FIRST_MOVE && board.getPieceAt(this.to) == null);
	}

	private boolean isSpaceBetweenEmpty(Color color) {
		if (color.equals(Color.WHITE)) {
			int x = from.getX();
			int y = from.getY();
			if (board.getPieceAt(new Coordinate(x, y + 1)) != null) {
				return false;
			} else {
				return true;
			}
		} else {
			int x = from.getX();
			int y = from.getY();
			if (board.getPieceAt(new Coordinate(x, y - 1)) != null) {
				return false;
			} else {
				return true;
			}
		}
	}

	private Move validateBlack() throws InvalidMoveException {
		int deltaX = this.to.getX() - this.from.getX();
		int deltaY = this.to.getY() - this.from.getY();
		int absDeltaX = Math.abs(this.to.getX() - this.from.getX());

		if (isBlackEnPassant(absDeltaX, deltaY)) {
			return new Move(from, to, MoveType.EN_PASSANT, board.getPieceAt(from));
		} else if (isBlackAttack(deltaX, deltaY)) {
			return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
		} else if (isBlackCapture(absDeltaX, deltaY)) {
			return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
		} else if (isBlackFirstMove(deltaX, deltaY)) {
			if (isSpaceBetweenEmpty(Color.BLACK)) {
				return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
			} else {
				throw new SpaceBetweenNotEmpty();
			}
		} else {
			throw new InvalidMoveException();
		}
	}

	private boolean isBlackEnPassant(int absDeltaX, int deltaY) {
		if (this.from.getY() == BLACK_ENPASSANT_ROW && absDeltaX == DELTA_X_BLACK_CAPTURE
				&& deltaY == DELTA_Y_BLACK_CAPTURE && board.getPieceAt(this.to) == null) {
			if (checkBlackHistory()) {
				return true;
			}
		}
		return false;
	}

	private boolean checkBlackHistory() {
		List<Move> moveHistory = this.board.getMoveHistory();
		if (!moveHistory.isEmpty()) {
			Move lastMove = moveHistory.get(moveHistory.size() - 1);
			Piece lastMovedPiece = lastMove.getMovedPiece();
			Coordinate lastMoveFrom = lastMove.getFrom();
			Coordinate lastMoveTo = lastMove.getTo();
			int deltaXBetweenPieces = Math.abs(lastMoveTo.getX() - this.from.getX());
			if (lastMovedPiece.getType().equals(PieceType.PAWN) && lastMovedPiece.getColor().equals(Color.WHITE)
					&& lastMoveFrom.getY() == WHITE_FIRST_ROW && deltaXBetweenPieces == 1
					&& lastMoveTo.getY() == BLACK_ENPASSANT_ROW) {
				return true;
			}
		}
		return false;
	}

	private boolean isBlackAttack(int deltaX, int deltaY) {
		return (deltaX == DELTA_X_BLACK_ATTACK && deltaY == DELTA_Y_BLACK_ATTACK && board.getPieceAt(this.to) == null);
	}

	private boolean isBlackCapture(int absDeltaX, int deltaY) {
		return (absDeltaX == DELTA_X_BLACK_CAPTURE && deltaY == DELTA_Y_BLACK_CAPTURE
				&& board.getPieceAt(this.to) != null);
	}

	private boolean isBlackFirstMove(int deltaX, int deltaY) {
		return (this.from.getY() == BLACK_FIRST_ROW && deltaY == DELTA_Y_BLACK_FIRST_MOVE
				&& deltaX == DELTA_X_BLACK_FIRST_MOVE && board.getPieceAt(this.to) == null);
	}
}
