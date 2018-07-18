package com.capgemini.chess.algorithms.implementation.utils;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

public class PieceMovesTest {
	@Test
	public void shouldReturn8KingMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.KING;
		Coordinate pieceCoordinates = new Coordinate(4, 4);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(8, moves.size());
	}
	
	@Test
	public void shouldReturn3KingMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.KING;
		Coordinate pieceCoordinates = new Coordinate(0, 0);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(3, moves.size());
	}
	
	@Test
	public void shouldReturn7BishopMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.BISHOP;
		Coordinate pieceCoordinates = new Coordinate(0, 0);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(7, moves.size());
	}
	
	@Test
	public void shouldReturn13BishopMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.BISHOP;
		Coordinate pieceCoordinates = new Coordinate(4, 4);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(13, moves.size());
	}
	
	@Test
	public void shouldReturn8KnightMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.KNIGHT;
		Coordinate pieceCoordinates = new Coordinate(4, 4);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(8, moves.size());
	}
	
	@Test
	public void shouldReturn2KnightMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.KNIGHT;
		Coordinate pieceCoordinates = new Coordinate(0, 0);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(2, moves.size());
	}
	
	@Test
	public void shouldReturn3WhitePawnMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.PAWN;
		Coordinate pieceCoordinates = new Coordinate(4, 4);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(3, moves.size());
	}
	
	@Test
	public void shouldReturn0WhitePawnMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.PAWN;
		Coordinate pieceCoordinates = new Coordinate(4, 7);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(0, moves.size());
	}
	
	@Test
	public void shouldReturn3BlackPawnMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.PAWN;
		Coordinate pieceCoordinates = new Coordinate(4, 4);
		Color color = Color.BLACK;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(3, moves.size());
	}
	
	@Test
	public void shouldReturn0BlackPawnMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.PAWN;
		Coordinate pieceCoordinates = new Coordinate(4, 0);
		Color color = Color.BLACK;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(0, moves.size());
	}
	
	@Test
	public void shouldReturn27QueenMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.QUEEN;
		Coordinate pieceCoordinates = new Coordinate(4, 4);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(27, moves.size());
	}
	
	@Test
	public void shouldReturn21QueenMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.QUEEN;
		Coordinate pieceCoordinates = new Coordinate(0, 0);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(21, moves.size());
	}
	
	@Test
	public void shouldReturn14RookMoves() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.ROOK;
		Coordinate pieceCoordinates = new Coordinate(0, 0);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(14, moves.size());
	}
	
	@Test
	public void shouldReturn14RookMoves2() throws InvalidMoveException {
		// given
		PieceType pieceType = PieceType.ROOK;
		Coordinate pieceCoordinates = new Coordinate(4, 4);
		Color color = Color.WHITE;
		
		//when
		List<Coordinate>moves = PieceMoves.getPossibleMoves(pieceType, pieceCoordinates, color);
		
		//then
		assertEquals(14, moves.size());
	}
}
