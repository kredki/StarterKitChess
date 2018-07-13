package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class ValidatorTest {
	@Test(expected = InvalidMoveException.class)
	public void wrongColorTest() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(0, 6);
		Coordinate to = new Coordinate(0, 5);
		
		//when
		Validator validator = new Validator(from, to, boardManager.getBoard(), Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void pieceNotThereTest() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(0, 2);
		Coordinate to = new Coordinate(0, 3);
		
		//when
		Validator validator = new Validator(from, to, boardManager.getBoard(), Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void myPieceIsOnToTest() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(0, 0);
		Coordinate to = new Coordinate(0, 1);
		
		//when
		Validator validator = new Validator(from, to, boardManager.getBoard(), Color.WHITE);

		// then
		validator.validate();
	}
	
	@Test(expected = InvalidMoveException.class)
	public void coordinatesNotOnBoardTest() throws InvalidMoveException {
		// given
		List<Move> moves = new ArrayList<>();
		BoardManager boardManager = new BoardManager(moves);
		Coordinate from = new Coordinate(1, 0);
		Coordinate to = new Coordinate(0, -1);
		
		//when
		Validator validator = new Validator(from, to, boardManager.getBoard(), Color.WHITE);

		// then
		validator.validate();
	}
}