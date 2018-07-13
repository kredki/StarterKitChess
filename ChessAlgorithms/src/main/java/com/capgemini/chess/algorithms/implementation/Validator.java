package com.capgemini.chess.algorithms.implementation;

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

public class Validator {
	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color actualPlayerColor;
	
	public Validator(Coordinate from, Coordinate to, Board board, Color actualPlayerColor) {
		super();
		this.from = from;
		this.to = to;
		this.board = board;
		this.actualPlayerColor = actualPlayerColor;
	}
	
	public Move validate() throws InvalidMoveException {
		isCoordinatesOnBoard();
		validateIfPieceIsThere();
		validatePieceColor();
		isToNotOccupiedByMyPiece();
		return null;
	}
	
	private boolean isCoordinatesOnBoard() throws CoordinateNotOnBoard {
		int xFrom = this.from.getX();
		int yFrom = this.from.getY();
		int xTo = this.to.getX();
		int yTo = this.to.getY();
		
		if(isInRange(xFrom) && isInRange(yFrom) && isInRange(xTo) && isInRange(yTo)) {
			return true;
		} else {
			throw new CoordinateNotOnBoard();
		}
	}
	
	private boolean isInRange(int x) {
		if(x < 8 && x > -1) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isToNotOccupiedByMyPiece() throws CoordinateOccupiedByMyPieceException {
		Piece piece = board.getPieceAt(this.to);
		if(piece == null || piece.getColor() != this.actualPlayerColor) {
			return true;
		}
		throw new CoordinateOccupiedByMyPieceException();
	}
	
	private boolean validateIfPieceIsThere() throws PieceNotThereException {
		if(board.getPieceAt(this.from) != null) {
			return true;
		}
		throw new PieceNotThereException();
	}
	
	private boolean validatePieceColor() throws WrongPieceColorException {
		if(board.getPieceAt(this.from).getColor() == this.actualPlayerColor) {
			return true;
		}
		throw new WrongPieceColorException();
	}
}
