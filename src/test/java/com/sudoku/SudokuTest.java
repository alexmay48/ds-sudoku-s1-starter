package com.sudoku;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A Testing suite to test the Sudoku.java non-gui methods.
 * @author Alex May
 *
 */
class SudokuTest {

	/**
	 * Puzze: puzzle0.txt
	 * Row Col
	 *    0 1 2  3 4 5  6 7 8 
	 *    
	 * 0  0 1 2  3 4 6  0 0 0 
	 * 1  3 6 4  5 0 9  1 2 7 
	 * 2  9 5 8  0 1 2  0 4 6 
	 * 
	 * 3  6 2 7  0 0 0  0 3 1 
	 * 4  0 0 9  0 0 8  5 7 0 
	 * 5  0 8 5  0 7 0  4 0 9 
	 * 
	 * 6  0 4 3  0 0 0  7 1 0 
	 * 7  8 0 0  0 2 0  6 0 3 
	 * 8  5 0 0  8 0 0  2 0 0 
	 * 
	 */
	static Sudoku puzzle0unsolved1;
	static Sudoku puzzle0unsolved2;
	
	/**
	 * Puzzle: puzzle0Solved.txt
	 * Row Col
	 *    0 1 2  3 4 5  6 7 8 
	 *    
	 * 0  7 1 2  3 4 6  9 8 5 
	 * 1  3 6 4  5 8 9  1 2 7 
	 * 2  9 5 8  7 1 2  3 4 6 
	 * 
	 * 3  6 2 7  9 5 4  8 3 1 
	 * 4  4 3 9  1 6 8  5 7 2 
	 * 5  1 8 5  2 7 3  4 6 9 
	 * 
	 * 6  2 4 3  6 9 5  7 1 8 
	 * 7  8 9 1  4 2 7  6 5 3 
	 * 8  5 7 6  8 3 1  2 9 4 
	 * 
	 */
	static Sudoku puzzle0solved1;
	static Sudoku puzzle0solved2;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception {
		// PUZZLE 0 UNSOLVED
		puzzle0unsolved1 = new Sudoku("src/test/resources/samplePuzzles/puzzle0.txt");
		
		// PUZZLE 0 UNSOLVED
		puzzle0unsolved2 = new Sudoku("src/test/resources/samplePuzzles/puzzle0.txt");

		// PUZZLE 0 SOLVED
		puzzle0solved1 = new Sudoku("src/test/resources/samplePuzzles/puzzle0Solved.txt");

		// PUZZLE 0 SOLVED
		puzzle0solved2 = new Sudoku("src/test/resources/samplePuzzles/puzzle0Solved.txt");
	}
	
	@Test
	void testGet1DArray() {
		assertTrue(Arrays.equals(puzzle0unsolved1.getPuzzle1D(), puzzle0unsolved2.getPuzzle1D()));
	}
	
	@Test
	void testGet2DArray() {
		int[][] test = {
				{7, 1, 2, 3, 4, 6, 9, 8, 5}, 
				{3, 6, 4, 5, 8, 9, 1, 2, 7},
				{9, 5, 8, 7, 1, 2, 3, 4, 6},
				{6, 2, 7, 9, 5, 4, 8, 3, 1},
				{4, 3, 9, 1, 6, 8, 5, 7, 2},
				{1, 8, 5, 2, 7, 3, 4, 6, 9},
				{2, 4, 3, 6, 9, 5, 7, 1, 8},
				{8, 9, 1, 4, 2, 7, 6, 5, 3},
				{5, 7, 6, 8, 3, 1, 2, 9, 4}};
		assertTrue(Arrays.deepEquals(test, puzzle0solved1.getPuzzle2D()));
	}
	
	@Test
	void testValidForExistingValue() {
		assertTrue(puzzle0unsolved1.isValid(1, 1));
	}
	
	@Test
	void testValidForRow1() throws IOException {
		assertTrue(puzzle0unsolved1.validForRow(0, 5));
		assertFalse(puzzle0unsolved1.validForRow(0, 1));
	}
	
	@Test
	void testValidForColumn1() throws IOException {
		assertTrue(puzzle0unsolved1.validForColumn(0, 4));
		assertFalse(puzzle0unsolved1.validForColumn(0, 6));
	}
	
	@Test
	void testValidForBox1() throws IOException {
		assertTrue(puzzle0unsolved1.validForBox(2, 3));
		assertFalse(puzzle0unsolved1.validForBox(8, 6));
	}
	
	@Test
	void testIsValid1() throws IOException {
		assertTrue(puzzle0unsolved1.isValid(0, 7));
		assertTrue(puzzle0unsolved1.isValid(80, 4));
		assertFalse(puzzle0unsolved1.isValid(80, 3));
	}

	@Test
	void testValidForRow() {
		assertTrue(puzzle0unsolved2.validForRow(0, 5));
	}

	@Test
	void testValidForBox() {
		assertTrue(puzzle0unsolved2.validForBox(0, 7));
	}

	@Test
	void testValidForColumn() {
		assertTrue(puzzle0unsolved2.validForColumn(0, 1));
	}

	@Test
	void testIsValid() {
		assertTrue(puzzle0unsolved2.isValid(8, 5));
	}

	@Test
	void testIsEquals() {
		assertTrue(puzzle0solved1.equals(puzzle0solved2));
	}

	@Test
	void solveAllPuzzlesRecursively() {		
		for (int i = 0; i < 41; i++) {
			// puzzle1.txt takes the longest to complete. Commenting out this line will solve that puzzle.
			if (i == 1) continue; 

			Sudoku puzzle = new Sudoku("src/test/resources/samplePuzzles/puzzle" + i + ".txt");
			puzzle.solveSudoku();
			
			// Puzzles 15, 20, 25 and 40 are not solvable
			if (i == 15 || i == 20 || i == 25 || i == 40) {
				assertFalse(puzzle.solved());
			} else {				
				assertTrue(puzzle.solved());
			}
			System.out.println("Puzzle: " + i + " counts: " + puzzle.getGuessCount());
		}
	}
	
	@Test
	void solvePuzzle() {
		assertTrue(puzzle0unsolved1.solveSudoku());
	}
	
	@Test
	void solvePuzzleAllSteps() {
		assertTrue(puzzle0unsolved1.verify());
		assertTrue(puzzle0unsolved1.solveSudoku());
		assertTrue(puzzle0unsolved1.verify());
		assertTrue(puzzle0unsolved1.solved());
		assertTrue(puzzle0unsolved1.equals(puzzle0solved1));
	}
	
	@Test
	void testVerifyFalse() {
		Sudoku puzzle = new Sudoku("src/test/resources/samplePuzzles/puzzle40.txt");
		assertFalse(puzzle.verify());
	}
	
	@Test
	void testVerify() {
		puzzle0unsolved1.verify();
	}
	
	@Test
	void testEquals() {
		assertTrue(puzzle0solved1.equals(puzzle0solved2));
	}
	
	@Test
	void testEqualsFalse() {
		assertFalse(puzzle0unsolved1.equals(puzzle0solved1));
	}
	
	@Test
	void testFindBox() {
		assertEquals(0, Sudoku.findBox(0));
		assertEquals(0, Sudoku.findBox(1));
		assertEquals(0, Sudoku.findBox(2));
		assertEquals(0, Sudoku.findBox(9));
		assertEquals(0, Sudoku.findBox(10));
		assertEquals(0, Sudoku.findBox(11));
		assertEquals(0, Sudoku.findBox(18));
		assertEquals(0, Sudoku.findBox(19));
		assertEquals(0, Sudoku.findBox(20));
		
		assertEquals(1, Sudoku.findBox(3));
		assertEquals(1, Sudoku.findBox(4));
		assertEquals(1, Sudoku.findBox(5));
		assertEquals(1, Sudoku.findBox(12));
		assertEquals(1, Sudoku.findBox(13));
		assertEquals(1, Sudoku.findBox(14));
		assertEquals(1, Sudoku.findBox(21));
		assertEquals(1, Sudoku.findBox(22));
		assertEquals(1, Sudoku.findBox(23));
		
		assertEquals(2, Sudoku.findBox(6));
		assertEquals(2, Sudoku.findBox(7));
		assertEquals(2, Sudoku.findBox(8));
		assertEquals(2, Sudoku.findBox(15));
		assertEquals(2, Sudoku.findBox(16));
		assertEquals(2, Sudoku.findBox(17));
		assertEquals(2, Sudoku.findBox(24));
		assertEquals(2, Sudoku.findBox(25));
		assertEquals(2, Sudoku.findBox(26));

		assertEquals(3, Sudoku.findBox(27));
		assertEquals(3, Sudoku.findBox(28));
		assertEquals(3, Sudoku.findBox(29));
		assertEquals(3, Sudoku.findBox(36));
		assertEquals(3, Sudoku.findBox(37));
		assertEquals(3, Sudoku.findBox(38));
		assertEquals(3, Sudoku.findBox(45));
		assertEquals(3, Sudoku.findBox(46));
		assertEquals(3, Sudoku.findBox(47));
		
		assertEquals(4, Sudoku.findBox(30));
		assertEquals(4, Sudoku.findBox(31));
		assertEquals(4, Sudoku.findBox(32));
		assertEquals(4, Sudoku.findBox(39));
		assertEquals(4, Sudoku.findBox(40));
		assertEquals(4, Sudoku.findBox(41));
		assertEquals(4, Sudoku.findBox(48));
		assertEquals(4, Sudoku.findBox(49));
		assertEquals(4, Sudoku.findBox(50));
		
		assertEquals(5, Sudoku.findBox(33));
		assertEquals(5, Sudoku.findBox(34));
		assertEquals(5, Sudoku.findBox(35));
		assertEquals(5, Sudoku.findBox(42));
		assertEquals(5, Sudoku.findBox(43));
		assertEquals(5, Sudoku.findBox(44));
		assertEquals(5, Sudoku.findBox(51));
		assertEquals(5, Sudoku.findBox(52));
		assertEquals(5, Sudoku.findBox(53));

		assertEquals(6, Sudoku.findBox(54));
		assertEquals(6, Sudoku.findBox(55));
		assertEquals(6, Sudoku.findBox(56));
		assertEquals(6, Sudoku.findBox(63));
		assertEquals(6, Sudoku.findBox(64));
		assertEquals(6, Sudoku.findBox(65));
		assertEquals(6, Sudoku.findBox(72));
		assertEquals(6, Sudoku.findBox(73));
		assertEquals(6, Sudoku.findBox(74));
		
		assertEquals(7, Sudoku.findBox(57));
		assertEquals(7, Sudoku.findBox(58));
		assertEquals(7, Sudoku.findBox(59));
		assertEquals(7, Sudoku.findBox(66));
		assertEquals(7, Sudoku.findBox(67));
		assertEquals(7, Sudoku.findBox(68));
		assertEquals(7, Sudoku.findBox(75));
		assertEquals(7, Sudoku.findBox(76));
		assertEquals(7, Sudoku.findBox(77));
		
		assertEquals(8, Sudoku.findBox(60));
		assertEquals(8, Sudoku.findBox(61));
		assertEquals(8, Sudoku.findBox(62));
		assertEquals(8, Sudoku.findBox(69));
		assertEquals(8, Sudoku.findBox(70));
		assertEquals(8, Sudoku.findBox(71));
		assertEquals(8, Sudoku.findBox(78));
		assertEquals(8, Sudoku.findBox(79));
		assertEquals(8, Sudoku.findBox(80));
	}

}
