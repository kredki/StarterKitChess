package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.validators.QueenValidator;
import com.capgemini.chess.algorithms.implementation.validators.RookValidator;

public class RookValidatorTest {
	@Test
	public void shouldNotThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 2);
		
		//when
		Validator validator = new RookValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void shouldThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(3, 5);
		
		//when
		Validator validator = new RookValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test
	public void shouldReturnAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 2);
		
		//when
		Validator validator = new RookValidator(from, to, board, Color.WHITE);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.ROOK, move.getMovedPiece().getType());
	}
	
	@Test
	public void shouldReturnCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 2));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 2);
		
		//when
		Validator validator = new RookValidator(from, to, board, Color.WHITE);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.ROOK, move.getMovedPiece().getType());
	}
}
