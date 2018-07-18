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
import com.capgemini.chess.algorithms.implementation.validators.KnightValidator;
import com.capgemini.chess.algorithms.implementation.validators.QueenValidator;
import com.capgemini.chess.algorithms.implementation.validators.RookValidator;

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
	
	@Test(expected = InvalidMoveException.class)
	public void shouldThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(6, 3);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test
	public void shouldReturnAttack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(2, 6);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.QUEEN, move.getMovedPiece().getType());
	}
	
	@Test
	public void shouldReturnCapture() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(2, 6));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(2, 6);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.QUEEN, move.getMovedPiece().getType());
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 5));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(7, 7);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked2() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(3, 5));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(1, 7);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked3() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(3, 3));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(0, 0);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked4() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 3));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(7, 1);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	///////////
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked5() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(4, 5));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 6);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocke6() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(4, 3));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 2);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked7() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(6, 4);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
	
	@Test(expected = SpaceBetweenNotEmpty.class)
	public void shouldThrowExceptionWhenMoveBlocked8() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(3, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(2, 4);
		
		//when
		Validator validator = new QueenValidator(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
}
