package com.capgemini.chess.algorithms.implementation;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingOnToException;
import com.capgemini.chess.algorithms.implementation.validators.KingOnToValidator;

public class KingOnToValidatorTest {
	@Test
	public void shouldNotThrowException2() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		Coordinate to = new Coordinate(2, 6);

		// when
		Validator validator = new KingOnToValidator(to, board);

		// then
		validator.validate();
	}

	@Test(expected = KingOnToException.class)
	public void shouldThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(2, 6));
		Coordinate to = new Coordinate(2, 6);

		// when
		Validator validator = new KingOnToValidator(to, board);

		// then
		validator.validate();
	}
}
