package com.capgemini.chess.algorithms.implementation;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

public class ValidationManagerTest {
	@Test(expected = KingInCheckException.class)
	public void shouldThrowException() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(4, 4));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(4, 7));
		Coordinate from = new Coordinate(4, 4);
		Coordinate to = new Coordinate(2, 4);
		
		//when		
		ValidationManager validator = new ValidationManager(from, to, board, Color.WHITE);
		
		//then
		validator.validate();
	}
}
