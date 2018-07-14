package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.implementation.exceptions.CoordinateOccupiedByMyPieceException;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.validators.OccupiedByMyPieceValidator;

public class OccupiedByMyPieceValidatorTest {
	
	@Test(expected = CoordinateOccupiedByMyPieceException.class)
	public void shouldThrowException() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(0, 0);
		Coordinate to = new Coordinate(0, 1);
		
		//when
		OccupiedByMyPieceValidator validator = new OccupiedByMyPieceValidator(to, 
				boardManager.getBoard(), Color.WHITE);
		// then
		validator.validate();
	}
	
	@Test
	public void shouldNotThrowException() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(0, 1);
		Coordinate to = new Coordinate(0, 2);
		
		//when
		OccupiedByMyPieceValidator validator = new OccupiedByMyPieceValidator(to, 
				boardManager.getBoard(), Color.WHITE);
		// then
		validator.validate();
	}
}
