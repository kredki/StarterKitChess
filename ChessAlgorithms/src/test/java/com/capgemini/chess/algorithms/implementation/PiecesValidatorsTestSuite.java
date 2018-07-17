package com.capgemini.chess.algorithms.implementation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BishopValidatorTest.class, KingValidatorTest.class, KnightValidatorTest.class,
	PawnValidatorTest.class, QueenValidatorTest.class, RookValidatorTest.class})
public class PiecesValidatorsTestSuite {
	
}
