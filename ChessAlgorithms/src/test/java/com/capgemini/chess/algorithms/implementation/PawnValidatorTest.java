package com.capgemini.chess.algorithms.implementation;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.validators.PawnValidator;

public class PawnValidatorTest {
	@Test
	public void shouldNotThrowExceptionWhiteNormalMove() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 5);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test
	public void shouldNotThrowExceptionBlackNormalMove() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 3);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.BLACK);

		// then
		validator.validate();
	}
	
	@Test
	public void shouldNotThrowExceptionWhiteFirstMove() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(4, 1));
		Coordinate from = new Coordinate(4, 1);
		Coordinate to = new Coordinate(4, 3);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test
	public void shouldNotThrowExceptionBlackFirstMove() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 6));
		Coordinate from = new Coordinate(4, 6);
		Coordinate to = new Coordinate(4, 5);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.BLACK);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void shouldThrowExceptionWhiteMove() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 3);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void shouldThrowExceptionBlackMove() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 5);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.BLACK);

		// then
		validator.validate();
	}
}
