package com.capgemini.chess.algorithms.implementation;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.validators.BishopValidator;
import com.capgemini.chess.algorithms.implementation.validators.KnightValidator;

public class KnightValidatorTest {
	@Test
	public void shouldNotThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(4, 0));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(5, 6);
		
		//when
		Validator validator = new KnightValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void shouldThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(4, 0));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(5, 5);
		
		//when
		Validator validator = new KnightValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void shouldThrowException2() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(4, 0));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(3, 1);
		
		//when
		Validator validator = new KnightValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
}
