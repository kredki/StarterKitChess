package com.capgemini.chess.algorithms.implementation.utils;

import java.util.LinkedList;
import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.PieceType;

public class PieceMoves {
	private final static int BOARD_MAX_SIZE = 7;
	private final static int BOARD_MIN_SIZE = 0;

	public static List<Coordinate> getPossibleMoves(PieceType pieceType,
			Coordinate pieceCoordinates, Color color) {
		switch (pieceType) {
		case KING:
			return getKingMoves(pieceCoordinates);
		case QUEEN:
			return getQueenMoves(pieceCoordinates);
		case BISHOP:
			return getBishopMoves(pieceCoordinates);
		case KNIGHT:
			return getKnightMoves(pieceCoordinates);
		case ROOK:
			return getRookMoves(pieceCoordinates);
		case PAWN:
			return getPawnMoves(pieceCoordinates, color);
		default:
			return null;
		}
	}

	private static List<Coordinate> getKingMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = new LinkedList<>();
		int x = pieceCoordinates.getX();
		int y = pieceCoordinates.getY();
		Coordinate coordinateToAdd;
		
		coordinateToAdd = new Coordinate(x, y+1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x+1, y+1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x-1, y+1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x, y-1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x+1, y-1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x-1, y-1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x+1, y);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x-1, y);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		
		return moves;
	}

	private static List<Coordinate> getQueenMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = new LinkedList<>();
		addXPlusYPlusMoves(moves, pieceCoordinates);
		addXPlusYMinusMoves(moves, pieceCoordinates);
		addXMinusYPlusMoves(moves, pieceCoordinates);
		addXMinusYMinusMoves(moves, pieceCoordinates);
		addXConstYPlusMoves(moves, pieceCoordinates);
		addXConstYMinusMoves(moves, pieceCoordinates);
		addXPlusYConstMoves(moves, pieceCoordinates);
		addXMinusYConstMoves(moves, pieceCoordinates);
		return moves;
	}

	private static List<Coordinate> getBishopMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = new LinkedList<>();
		addXPlusYPlusMoves(moves, pieceCoordinates);
		addXPlusYMinusMoves(moves, pieceCoordinates);
		addXMinusYPlusMoves(moves, pieceCoordinates);
		addXMinusYMinusMoves(moves, pieceCoordinates);
		return moves;
	}

	private static List<Coordinate> getKnightMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = new LinkedList<>();
		int x = pieceCoordinates.getX();
		int y = pieceCoordinates.getY();
		Coordinate coordinateToAdd;
		coordinateToAdd = new Coordinate(x+2, y+1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x+2, y-1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x-2, y+1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x-2, y-1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x+1, y+2);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x+1, y-2);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x-1, y+2);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x-1, y-2);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			moves.add(coordinateToAdd);
		}
		return moves;
	}

	private static List<Coordinate> getRookMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = new LinkedList<>();
		addXConstYPlusMoves(moves, pieceCoordinates);
		addXConstYMinusMoves(moves, pieceCoordinates);
		addXPlusYConstMoves(moves, pieceCoordinates);
		addXMinusYConstMoves(moves, pieceCoordinates);
		return moves;
	}

	private static List<Coordinate> getPawnMoves(Coordinate pieceCoordinates, Color color) {
		List<Coordinate> moves = new LinkedList<>();
		
		if(color.equals(Color.WHITE)) {
			addWhitePawnMoves(moves, pieceCoordinates);
		} else {
			addBlackPawnMoves(moves, pieceCoordinates);
		}
		return moves;
	}
	
	private static void addXPlusYPlusMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {		
		int x = pieceCoordinates.getX() + 1;
		int y = pieceCoordinates.getY() + 1;
		while(x <= BOARD_MAX_SIZE && y <= BOARD_MAX_SIZE) {
			destMoves.add(new Coordinate(x, y));
			x++;
			y++;
		}
	}
	
	private static void addXPlusYMinusMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {		
		int x = pieceCoordinates.getX() + 1;
		int y = pieceCoordinates.getY() - 1;
		while(x <= BOARD_MAX_SIZE && y >= BOARD_MIN_SIZE) {
			destMoves.add(new Coordinate(x, y));
			x++;
			y--;
		}
	}
	
	private static void addXMinusYPlusMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {		
		int x = pieceCoordinates.getX() - 1;
		int y = pieceCoordinates.getY() + 1;
		while(x >= BOARD_MIN_SIZE && y <= BOARD_MAX_SIZE) {
			destMoves.add(new Coordinate(x, y));
			x--;
			y++;
		}
	}
	
	private static void addXMinusYMinusMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {		
		int x = pieceCoordinates.getX() - 1;
		int y = pieceCoordinates.getY() - 1;
		while(x >= BOARD_MIN_SIZE && y >= BOARD_MIN_SIZE) {
			destMoves.add(new Coordinate(x, y));
			x--;
			y--;
		}
	}
	
	private static void addXConstYPlusMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {		
		int x = pieceCoordinates.getX();
		int y = pieceCoordinates.getY() + 1;
		while(y <= BOARD_MAX_SIZE) {
			destMoves.add(new Coordinate(x, y));
			y++;
		}
	}
	
	private static void addXConstYMinusMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {		
		int x = pieceCoordinates.getX();
		int y = pieceCoordinates.getY() - 1;
		while(y >= BOARD_MIN_SIZE) {
			destMoves.add(new Coordinate(x, y));
			y--;
		}
	}
	
	private static void addXPlusYConstMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {		
		int x = pieceCoordinates.getX() + 1;
		int y = pieceCoordinates.getY();
		while(x <= BOARD_MAX_SIZE) {
			destMoves.add(new Coordinate(x, y));
			x++;
		}
	}
	
	private static void addXMinusYConstMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {		
		int x = pieceCoordinates.getX() - 1;
		int y = pieceCoordinates.getY();
		while(x >= BOARD_MIN_SIZE) {
			destMoves.add(new Coordinate(x, y));
			x--;
		}
	}
	
	private static void addWhitePawnMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {
		int x = pieceCoordinates.getX();
		int y = pieceCoordinates.getY();
		Coordinate coordinateToAdd;
		coordinateToAdd = new Coordinate(x, y+1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			destMoves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x+1, y+1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			destMoves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x-1, y+1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			destMoves.add(coordinateToAdd);
		}
	}
	
	private static void addBlackPawnMoves(List<Coordinate> destMoves, Coordinate pieceCoordinates) {
		int x = pieceCoordinates.getX();
		int y = pieceCoordinates.getY();
		Coordinate coordinateToAdd;
		coordinateToAdd = new Coordinate(x, y-1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			destMoves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x+1, y-1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			destMoves.add(coordinateToAdd);
		}
		coordinateToAdd = new Coordinate(x-1, y-1);
		if(isCoordinateOnBoard(coordinateToAdd)) {
			destMoves.add(coordinateToAdd);
		}
	}

	private static boolean isCoordinateOnBoard(Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();
		if (x <= BOARD_MAX_SIZE && x >= BOARD_MIN_SIZE && y <= BOARD_MAX_SIZE && y >= BOARD_MIN_SIZE) {
			return true;
		} else {
			return false;
		}
	}
}
