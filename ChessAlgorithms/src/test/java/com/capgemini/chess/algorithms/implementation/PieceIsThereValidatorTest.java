package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.PieceNotThereException;
import com.capgemini.chess.algorithms.implementation.validators.PieceIsThereValidator;

public class PieceIsThereValidatorTest {
	@Test(expected = PieceNotThereException.class)
	public void shouldThrowExceprion() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(0, 2);
		
		//when
		PieceIsThereValidator validator = new PieceIsThereValidator(from, boardManager.getBoard());

		// then
		validator.validate();
	}
	
	@Test
	public void shouldNotThrowExceprion() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(0, 1);
		
		//when
		PieceIsThereValidator validator = new PieceIsThereValidator(from, boardManager.getBoard());

		// then
		validator.validate();
	}
}
