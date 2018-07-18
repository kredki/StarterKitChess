package com.capgemini.chess.algorithms.implementation.utils;

import java.util.List;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.enums.PieceType;

public class PieceMoves {
	private final static int BOARD_MAX_SIZE = 7;
	private final static int BOARD_MIN_SIZE = 0;

	public static List<Coordinate> getPossibleMoves(PieceType pieceType, Coordinate pieceCoordinates) {
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
			return getPawnMoves(pieceCoordinates);
		default:
			return null;
		}
	}

	private static List<Coordinate> getKingMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = null;
		return moves;
	}

	private static List<Coordinate> getQueenMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = null;
		return moves;
	}

	private static List<Coordinate> getBishopMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = null;
		return moves;
	}

	private static List<Coordinate> getKnightMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = null;
		return moves;
	}

	private static List<Coordinate> getRookMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = null;
		return moves;
	}

	private static List<Coordinate> getPawnMoves(Coordinate pieceCoordinates) {
		List<Coordinate> moves = null;
		return moves;
	}

	protected boolean isCoordinateOnBoard(Coordinate coordinate) {
		int x = coordinate.getX();
		int y = coordinate.getY();
		if (x < BOARD_MAX_SIZE && x > BOARD_MIN_SIZE && y < BOARD_MAX_SIZE && y > BOARD_MIN_SIZE) {
			return true;
		} else {
			return false;
		}
	}
}
