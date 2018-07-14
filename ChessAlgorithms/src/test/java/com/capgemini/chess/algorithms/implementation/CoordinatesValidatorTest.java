package com.capgemini.chess.algorithms.implementation;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.implementation.exceptions.CoordinateNotOnBoard;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.validators.CoordinatesValidator;

public class CoordinatesValidatorTest {
	
	@Test(expected = CoordinateNotOnBoard.class)
	public void shouldThrowExceprion() throws InvalidMoveException {
		// given
		Coordinate from = new Coordinate(1, 0);
		Coordinate to = new Coordinate(0, -1);
		
		//when
		CoordinatesValidator validator = new CoordinatesValidator(from, to);

		// then
		validator.validate();
	}
	
	@Test
	public void shouldNotThrowExceprion() throws InvalidMoveException {
		// given
		Coordinate from = new Coordinate(1, 0);
		Coordinate to = new Coordinate(0, 1);
		
		//when
		CoordinatesValidator validator = new CoordinatesValidator(from, to);

		// then
		validator.validate();
	}
}
