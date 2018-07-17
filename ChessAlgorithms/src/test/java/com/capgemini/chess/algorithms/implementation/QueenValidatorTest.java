package com.capgemini.chess.algorithms.implementation;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.validators.QueenValidator;

public class QueenValidatorTest {
	@Test
	public void shouldNotThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 2);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test
	public void shouldNotThrowException2() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(2, 6);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void shouldThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(3, 2);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
}
