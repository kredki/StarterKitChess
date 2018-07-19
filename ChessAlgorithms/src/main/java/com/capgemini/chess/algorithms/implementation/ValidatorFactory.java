package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.PieceNotThereException;
import com.capgemini.chess.algorithms.implementation.validators.BishopValidator;
import com.capgemini.chess.algorithms.implementation.validators.CoordinatesValidator;
import com.capgemini.chess.algorithms.implementation.validators.KingOnToValidator;
import com.capgemini.chess.algorithms.implementation.validators.KingValidator;
import com.capgemini.chess.algorithms.implementation.validators.KnightValidator;
import com.capgemini.chess.algorithms.implementation.validators.OccupiedByMyPieceValidator;
import com.capgemini.chess.algorithms.implementation.validators.PawnValidator;
import com.capgemini.chess.algorithms.implementation.validators.PieceColorValidator;
import com.capgemini.chess.algorithms.implementation.validators.PieceIsThereValidator;
import com.capgemini.chess.algorithms.implementation.validators.QueenValidator;
import com.capgemini.chess.algorithms.implementation.validators.RookValidator;

public class ValidatorFactory {
	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color actualPlayerColor;
	private final static int BOARD_MAX_SIZE = 7;
	private final static int BOARD_MIN_SIZE = 0;

	public ValidatorFactory(Coordinate from, Coordinate to, Board board, Color actualPlayerColor) {
		super();
		this.from = from;
		this.to = to;
		this.board = board;
		this.actualPlayerColor = actualPlayerColor;
	}

	public List<Validator> getValidators() throws InvalidMoveException {
		Validator validator = new CoordinatesValidator(this.from, this.to);
		validator.validate();

		List<Validator> validators = new ArrayList<>();
		validators.add(new PieceIsThereValidator(this.from, this.board));
		validators.add(new PieceColorValidator(this.from, this.board, this.actualPlayerColor));
		validators.add(new OccupiedByMyPieceValidator(this.to, this.board, this.actualPlayerColor));
		validators.add(new KingOnToValidator(to, board));
		validators.add(getPieceValidator());
		return validators;
	}

	private Validator getPieceValidator() throws PieceNotThereException {
		Validator validator = null;
		PieceType pieceType;
		try {
			pieceType = board.getPieceAt(this.from).getType();
		} catch (Exception e) {
			throw new PieceNotThereException();
		}
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

	public List<Validator> getAfterMoveValidators(Move move) {
		List<Validator> validators = new ArrayList<>();
		Board afterMoveBoard = copyBoard();
		if (move != null) {
			Coordinate copyFrom = move.getFrom();
			Coordinate copyTo = move.getTo();
			afterMoveBoard.setPieceAt(null, copyFrom);
			afterMoveBoard.setPieceAt(move.getMovedPiece(), copyTo);
			if (move.getType().equals(MoveType.EN_PASSANT)) {
				moveOpponentPawn(move, afterMoveBoard, copyTo);
			}

			if (move.getType().equals(MoveType.CASTLING)) {
				moveCastlingRook(afterMoveBoard, move);
			}
		}
		Color opponentsColor = getOpponentsColor();
		Coordinate myKingCoordinate = afterMoveBoard.getKingCoordinates(this.actualPlayerColor);

		for (int x = BOARD_MIN_SIZE; x <= BOARD_MAX_SIZE; x++) {
			for (int y = BOARD_MIN_SIZE; y <= BOARD_MAX_SIZE; y++) {
				Coordinate coordinate = new Coordinate(x, y);
				Piece piece = afterMoveBoard.getPieceAt(coordinate);
				if (piece != null && !piece.getColor().equals(this.actualPlayerColor)
						&& !piece.getType().equals(PieceType.KING)) {
					Validator validator = getPieceValidator(coordinate, myKingCoordinate, afterMoveBoard,
							piece.getType(), opponentsColor);
					validators.add(validator);
				}
			}
		}
		return validators;
	}

	private void moveOpponentPawn(Move move, Board destBoard, Coordinate to) {
		int x = to.getX();
		int y;
		if (move.getMovedPiece().getColor().equals(Color.WHITE)) {
			y = to.getY() - 1;
		} else {
			y = to.getY() + 1;
		}
		Coordinate opponentsPawnCoordinate = new Coordinate(x, y);
		destBoard.setPieceAt(null, opponentsPawnCoordinate);
	}

	private void moveCastlingRook(Board destBoard, Move castlingMove) {
		int deltaX = castlingMove.getTo().getX() - castlingMove.getFrom().getX();
		Coordinate moveRookFrom;
		Coordinate moveRookTo;
		int y = castlingMove.getTo().getY();
		if (deltaX > 0) {
			moveRookFrom = new Coordinate(BOARD_MAX_SIZE, y);
			moveRookTo = new Coordinate(5, y);
		} else {
			moveRookFrom = new Coordinate(BOARD_MIN_SIZE, y);
			moveRookTo = new Coordinate(3, y);
		}
		movePiece(destBoard, moveRookFrom, moveRookTo);
	}

	private void movePiece(Board board, Coordinate from, Coordinate to) {
		Piece movedPiece = board.getPieceAt(from);
		board.setPieceAt(null, from);
		board.setPieceAt(movedPiece, to);
	}

	private Board copyBoard() {
		Board copiedBoard = new Board();
		for (int x = BOARD_MIN_SIZE; x <= BOARD_MAX_SIZE; x++) {
			for (int y = BOARD_MIN_SIZE; y <= BOARD_MAX_SIZE; y++) {
				Coordinate coordinate = new Coordinate(x, y);
				Piece pieceToCopy = this.board.getPieceAt(coordinate);
				copiedBoard.setPieceAt(pieceToCopy, coordinate);
			}
		}
		return copiedBoard;
	}

	private Color getOpponentsColor() {
		if (this.actualPlayerColor.equals(Color.WHITE)) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}

	private Validator getPieceValidator(Coordinate from, Coordinate to, Board board, PieceType pieceType, Color color) {
		Validator validator = null;
		switch (pieceType) {
		case KING:
			validator = new KingValidator(from, to, board, color);
			break;
		case QUEEN:
			validator = new QueenValidator(from, to, board, color);
			break;
		case BISHOP:
			validator = new BishopValidator(from, to, board, color);
			break;
		case KNIGHT:
			validator = new KnightValidator(from, to, board, color);
			break;
		case ROOK:
			validator = new RookValidator(from, to, board, color);
			break;
		case PAWN:
			validator = new PawnValidator(from, to, board, color);
			break;

		default:
			break;
		}
		return validator;
	}
}
