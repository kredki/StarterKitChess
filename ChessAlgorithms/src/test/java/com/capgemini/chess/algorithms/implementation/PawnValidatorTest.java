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
import com.capgemini.chess.algorithms.implementation.validators.PawnValidator;
import com.capgemini.chess.algorithms.implementation.validators.RookValidator;

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
	
	@Test
	public void shouldReturnAttackWhite() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 5);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.WHITE);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.PAWN, move.getMovedPiece().getType());
	}
	
	@Test
	public void shouldReturnCaptureWhite() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(4, 4));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 5));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(5, 5);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.WHITE);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.PAWN, move.getMovedPiece().getType());
	}
	
	@Test
	public void shouldReturnAttackBlack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 4));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(4, 3);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.BLACK);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.ATTACK, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.PAWN, move.getMovedPiece().getType());
	}
	
	@Test
	public void shouldReturnCaptureBlack() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(4, 4));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 3));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(5, 3);
		
		//when
		Validator validator = new PawnValidator(from, to, board, Color.BLACK);
		Move move = validator.validate();
		
		//then
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(from, move.getFrom());
		assertEquals(to, move.getTo());
		assertEquals(PieceType.PAWN, move.getMovedPiece().getType());
	}
}
