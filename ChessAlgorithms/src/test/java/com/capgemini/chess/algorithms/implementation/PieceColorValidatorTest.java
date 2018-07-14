package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.WrongPieceColorException;
import com.capgemini.chess.algorithms.implementation.validators.PieceColorValidator;

public class PieceColorValidatorTest {
	@Test(expected = WrongPieceColorException.class)
	public void shouldThrowException() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(0, 6);
		Coordinate to = new Coordinate(0, 5);
		
		//when
		PieceColorValidator validator = new PieceColorValidator(from, boardManager.getBoard(), Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test
	public void shouldNotThrowException() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(0, 1);
		
		//when
		PieceColorValidator validator = new PieceColorValidator(from, boardManager.getBoard(), Color.WHITE);

		// then
		validator.validate();
	}
}
