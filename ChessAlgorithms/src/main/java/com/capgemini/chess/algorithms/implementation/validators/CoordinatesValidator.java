package com.capgemini.chess.algorithms.implementation.validators;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.implementation.Validator;
import com.capgemini.chess.algorithms.implementation.exceptions.CoordinateNotOnBoard;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class CoordinatesValidator implements Validator {
	private Coordinate from;
	private Coordinate to;

	public CoordinatesValidator(Coordinate from, Coordinate to) {
		super();
		this.from = from;
		this.to = to;
	}

	@Override
	public Move validate() throws InvalidMoveException {
		isCoordinatesOnBoard();
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
}
