package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.SpaceBetweenNotEmpty;

public class RookValidator implements Validator {
	private Coordinate from;
	private Coordinate to;
	private Board board;
	private Color actualPlayerColor;

	public RookValidator(Coordinate from, Coordinate to, Board board, Color actualPlayerColor) {
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
		if ((deltaX == 0 && deltaY > 0) || (deltaX > 0 && deltaY == 0)) {
			validateSpacesBetween();
			Piece pieceAtTo = board.getPieceAt(to);
			if(pieceAtTo != null) {
				return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
			} else {
				return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
			}
		} else {
			throw new InvalidMoveException();
		}
	}

	private void validateSpacesBetween() throws SpaceBetweenNotEmpty {
		int deltaX = this.to.getX() - this.from.getX();
		int deltaY = this.to.getY() - this.from.getY();
		
		if(deltaX > 0 && deltaY == 0) {
			validateXPlusYConst();
		} else if(deltaX < 0 && deltaY == 0) {
			validateXMinusYConst();
		} else if(deltaX == 0 && deltaY > 0){
			validateXConstYPlus();
		} else { //deltaX == 0 && deltaY < 0
			validateXConstYMinus();
		}
	}
	
	private void validateXPlusYConst() throws SpaceBetweenNotEmpty {
		int xFrom = this.from.getX();
		int yFrom = this.from.getY();
		int xTo = this.to.getX();
		
		int x = xFrom + 1;
		int y = yFrom;
		while(x < xTo) {
			Piece pieceBetween = this.board.getPieceAt(new Coordinate(x, y));
			if(pieceBetween != null) {
				throw new SpaceBetweenNotEmpty();
			}
			x++;
		}
	}
	
	private void validateXMinusYConst() throws SpaceBetweenNotEmpty {
		int xFrom = this.from.getX();
		int yFrom = this.from.getY();
		int xTo = this.to.getX();
		
		int x = xFrom - 1;
		int y = yFrom;
		while(x > xTo) {
			Piece pieceBetween = this.board.getPieceAt(new Coordinate(x, y));
			if(pieceBetween != null) {
				throw new SpaceBetweenNotEmpty();
			}
			x--;
		}
	}
	
	private void validateXConstYPlus() throws SpaceBetweenNotEmpty {
		int xFrom = this.from.getX();
		int yFrom = this.from.getY();
		int yTo = this.to.getY();
		
		int x = xFrom;
		int y = yFrom + 1;
		while(y < yTo) {
			Piece pieceBetween = this.board.getPieceAt(new Coordinate(x, y));
			if(pieceBetween != null) {
				throw new SpaceBetweenNotEmpty();
			}
			y++;
		}
	}
	
	private void validateXConstYMinus() throws SpaceBetweenNotEmpty {
		int xFrom = this.from.getX();
		int yFrom = this.from.getY();
		int yTo = this.to.getY();
		
		int x = xFrom;
		int y = yFrom - 1;
		while(y > yTo) {
			Piece pieceBetween = this.board.getPieceAt(new Coordinate(x, y));
			if(pieceBetween != null) {
				throw new SpaceBetweenNotEmpty();
			}
			y--;
		}
	}
}
