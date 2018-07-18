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
import com.capgemini.chess.algorithms.implementation.exceptions.SpaceBetweenNotEmpty;
import com.capgemini.chess.algorithms.implementation.validators.BishopValidator;

public class BishopValidatorTest {
	@Test
	public void shouldNotThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(7, 7);
		
		//when
		Validator validator = new BishopValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void shouldThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(2, 1);
		
		//when
		Validator validator = new BishopValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void shouldThrowException2() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(4, 0));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(6, 3);
		
		//when
		Validator validator = new BishopValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test
	public void shouldReturnAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(7, 7);
		
		//when
		Validator validator = new BishopValidator(from, to, board, Color.WHITE);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.BISHOP, move.getMovedPiece().getType());
	}
	
	@Test
	public void shouldReturnCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(7, 7);
		
		//when
		Validator validator = new BishopValidator(from, to, board, Color.WHITE);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.BISHOP, move.getMovedPiece().getType());
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 5));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(7, 7);
		
		//when
		Validator validator = new BishopValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked2() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(3, 5));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(1, 7);
		
		//when
		Validator validator = new BishopValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked3() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(3, 3));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(0, 0);
		
		//when
		Validator validator = new BishopValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked4() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 3));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(7, 1);
		
		//when
		Validator validator = new BishopValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
}
