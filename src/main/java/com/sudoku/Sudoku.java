package com.sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Sudoku extends JPanel implements ChangeListener {

	private static final long serialVersionUID = 6823925350476888214L;

	/**
	 * Declare fields here as needed.
	 *
	 * You may choose to store your internal data as either a 1D array or a 2D
	 * array. The 1D array will make the recursive solver slightly easier to write
	 * (why?). The 2D array might make traversing the puzzle slightly easier (why?).
	 * 
	 * Also, the 2D array will be a little harder to use because there is some GUI code you 
	 * would have to change to accommodate that.
	 */
	private int[] sudokuArray;
	private int[] originalSudokuArray;
	private long guessCount;
	private static final int empty = 0;

	// *** GUI Fields and Variables ***
	private boolean solving = false;
	private boolean stopSudoku = false;
	private long solveDelay;
	private static final int STEPS_PER_SEC_MIN = 0;
	private static final int STEPS_PER_SEC_MAX = 1000;
	private boolean useGui = false;
	JButton solveSudokuButton;
	JButton pauseSudokuButton;
	JMenuItem importMenuItem;
	JFileChooser fileChooser;
	JSlider solverPerSecond = new JSlider(JSlider.HORIZONTAL, STEPS_PER_SEC_MIN, STEPS_PER_SEC_MAX, 0);
	JLabel gameStatus;
	SudokuBucket[][] buckets;
	SudokuSolverThread solver;
	// *** END GUI Fields and Variables ***

	/**
	 * Constructor
	 * 
	 * Takes in a file name to build the sudoku puzzle.
	 * 
	 * @param fileName - Takes in a valid sudoku .txt file
	 */
	public Sudoku(String fileName) {
		openFile(fileName);
	}

	/**
	 * Default Constructor
	 * 
	 * Will set up the GUI of the sudoku puzzle.
	 */
	public Sudoku() {
		createAndSetupGui();
	}

	/**
	 * Read in the .txt file to create the sudoku puzzle.
	 * 
	 * @param fileName
	 */
	public void openFile(String fileName) {
		// FIXME: read the file into the sudoku puzzle array

	}

	/**
	 * @return a copy of the puzzle as a 1D matrix
	 */
	public int[] getPuzzle1D() {
		// FIXME: return the puzzle as a 1D int array
		return null;
	}

	/**
	 * @return a copy of the puzzle as a 2D matrix
	 */
	public int[][] getPuzzle2D() {
		// FIXME: return the puzzle as a 2D int array
		return null;
	}

	/**
	 * @return how many guesses it took to recursively solve the problem.
	 */
	public long getGuessCount() {
		// FIXME: return the guess count
		return 0;
	}

	/**
	 * Determine if the given number can be placed in the given row without violating 
	 * the rules of sudoku.
	 * 
	 * In other words, if the given number is already present in the row, it
	 * is not possible to place it again (return false), otherwise it is
	 * possible to place it (return true);
	 * 
	 * @param row - The row to check if the number is valid.
	 * @param number - The number to check is valid for the row.
	 * @return - If the number can be placed in the row (because the value is not present in the row)
	 */
	public boolean validForRow(int row, int number) {
		// FIXME: 
		return false;
	}

	/**
	 * Function: validForColumn (see above)
	 */
	public boolean validForColumn(int col, int number) {
		// FIXME:
		return false;
	}

	/**
	 * Function: validForBox (see above)
	 *
	 * The sudoku boxes are:
	 *
	 *  0 | 1 | 2 
	 * ---+---+--- 
	 *  3 | 4 | 5 
	 * ---+---+--- 
	 *  6 | 7 | 8
	 *
	 * where each box represents a 3x3 square in the game.
	 *
	 */
	public boolean validForBox(int box, int number) {
		// FIXME: 
		return false;
	}

	/**
	 * Determine if the given value is valid in the puzzle at that position. This means 
	 * that the value does not already occur in the current row, column, or box. If the 
	 * value already occurs in the position, this is valid, so return true.
	 *
	 * @param position      - which bucket in the puzzle to check for validity -
	 *                      should be empty
	 * @param possibleValue - the value to check (1-9)
	 * @return true if valid
	 */
	public boolean isValid(int position, int possibleValue) {
		// FIXME:
		return false;
	}

	/**
	 * Solve the sudoku problem
	 * 
	 * @return true if the sudoku puzzle is solvable, and solved.
	 */
	public boolean solveSudoku() {
		solving = true;
		return solveSudoku(0);
	}

	/**
	 * Recursively check to see if the puzzle can be solved following class
	 * algorithm
	 * 
	 * NOTE: The "stopSudoku" variable allows us to "rewind" all of the recursive call
	 * stacks made so that our solver thread doesn't get stuck on two different"solveSudoku" methods
	 * 
	 * @param position - the index of the current "bucket" in the array. (should be
	 * set to 0 by initial call)
	 * @return - the base case, the recursive call, or if it is invalid
	 */
	public boolean solveSudoku(int position) {

		// *** GUI CODE ***
		if (stopSudoku)
			return true;
		guiBucketEnter(position);
		guiSolverPause();
		guiSolverDelay();
		// *** END GUI CODE ***

		
		// FIXME: Place the recursive sudoku algorithm here.

		
		// *** GUI CODE ***
		guiBucketExit(position);
		// *** END GUI CODE
		// You will return false if there is not valid solution.
		return false;
	}

	/**
	 * Given a puzzle (filled or partial), verify that every element does not repeat
	 * in row, col, or box.
	 * 
	 * @return true if a validly solved puzzle
	 */
	public boolean verify() {
		// FIXME:
		return false;
	}

	/**
	 * Given a sudoku puzzle, every "bucket" is validly filled and 
	 * there is no empty bucket. Note that this is very close to the {@link #verify()} 
	 * method, but this will return false if there is any empty buckets because that 
	 * means the puzzle is not solved.
	 * 
	 * @return - If the sudoky puzzle is solved.
	 */
	public boolean solved() {
		// FIXME: 
		return false;
	}

	/**
	 * Helper method to find the "box" that the position is in in the Sudoku puzzle.
	 * 
	 * @param position - the position of the sudoku puzzle
	 * @return - the box that the bucket at that positiokn resides in.
	 */
	public static int findBox(int position) {
		// FIXME: 
		return 0;
	}

	/**
	 * A sudoku puzzle is equal to another sudoku puzzle if all buckets have the same values. 
	 * This can be filled or partially filled puzzles.
	 *  
	 * @param puzzle - the other puzzle to compare to
	 * @return - true if all "buckets" have the same values in both this and the other puzzle.
	 */
	public boolean equals(Sudoku puzzle) {
		// FIXME:
		return false;
	}

	/**
	 * 
	 * @return a string showing the state of the board
	 *
	 */
	@Override
	public String toString() {
		// TODO: This is not in the JUnit tests, but you should write this to help you with debugging 
		// if you need to.
		return null;
	}

	/*
	 * ***** GUI METHODS *****
	 * You should not need to alter these methods unless you change from a 1D to a 2D array, 
	 * or if you want to add more effects for extra credit.
	 * 
	 * You can write your own methods before or after these, but not in the middle.
	 */
	
	/**
	 * Sets up the GUI for the Sudoku Puzzle.
	 */
	public void createAndSetupGui() {
		useGui = true;
		JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout(new FlowLayout());
		JLabel sliderLabel = new JLabel("Solver Delay (In Milliseconds)");
		sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		solverPerSecond.addChangeListener(this);
		solverPerSecond.setMajorTickSpacing(200);
		solverPerSecond.setMinorTickSpacing(50);
		solverPerSecond.setPaintTicks(true);
		solverPerSecond.setPaintLabels(true);

		sliderPanel.add(sliderLabel);
		sliderPanel.add(solverPerSecond);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout());

		solveSudokuButton = new JButton("Solve Sudoku");
		solveSudokuButton.setEnabled(false);
		solveSudokuButton.addActionListener((ActionEvent e) -> {
			gameStatus.setText("Solving...");
			solveSudokuButton.setEnabled(false);
			pauseSudokuButton.setEnabled(true);
			solving = true;
			if (solver.isStopped()) {
				solver.start();
			}

		});

		pauseSudokuButton = new JButton("Pause Sudoku");
		pauseSudokuButton.setEnabled(false);
		pauseSudokuButton.addActionListener((ActionEvent e) -> {
			gameStatus.setText("Game Paused!");
			solveSudokuButton.setEnabled(true);
			pauseSudokuButton.setEnabled(false);
			solving = false;
		});

		buttonsPanel.add(solveSudokuButton);
		buttonsPanel.add(pauseSudokuButton);

		JPanel sudokuPanel = new JPanel();
		sudokuPanel.setVisible(true);
		sudokuPanel.setLayout(new GridLayout(9, 9));

		buckets = new SudokuBucket[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				SudokuBucket label = new SudokuBucket();
				sudokuPanel.add(label);
				buckets[i][j] = label;
			}
		}

		fileChooser = new JFileChooser("samplePuzzles/");

		gameStatus = new JLabel("Welcome to Sudoku!");

		add(gameStatus);
		add(sudokuPanel);
		add(buttonsPanel, BorderLayout.SOUTH);
		add(sliderPanel, BorderLayout.NORTH);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// Used to change the delay or speed of the GUI solver
		JSlider source = (JSlider) e.getSource();
		if (!source.getValueIsAdjusting()) {
			solveDelay = (int) source.getValue();
		}
	}

	/**
	 * Creates the Menu Bar element and actions
	 * 
	 * @return
	 */
	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		importMenuItem = new JMenuItem("Import Sudoku Puzzle");
		importMenuItem.addActionListener((ActionEvent e) -> {
			openPuzzleFile();
		});

		menuBar.add(fileMenu);
		fileMenu.add(importMenuItem);
		return menuBar;
	}

	/**
	 * Pauses the GUI until the "solving" variable is turned back to true.
	 */
	public void guiSolverPause() {
		while (!solving) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sleeps the solver thread to cause a delay or change the "speed" of the sudoky
	 * algorithm
	 */
	public void guiSolverDelay() {
		if (solveDelay != 0) {
			try {
				Thread.sleep(solveDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Changes the color and text of the bucket we are looking at depending of the
	 * state of the bucket when we "enter" the bucket to attempt guessing.
	 * 
	 * @param position - The box that we are entering.
	 */
	public void guiBucketEnter(int position) {
		if (useGui && position < 81) {
			buckets[position / 9][position % 9].label.setText(sudokuArray[position] + "");
			if (position > 0) {
				buckets[(position - 1) / 9][(position - 1) % 9].label.setText(sudokuArray[position - 1] + "");
				if (originalSudokuArray[position - 1] != empty) {
					buckets[(position - 1) / 9][(position - 1) % 9].setBackground(Color.LIGHT_GRAY);
				} else {
					buckets[(position - 1) / 9][(position - 1) % 9].setBackground(Color.GREEN);
				}
			}

			buckets[position / 9][position % 9].setBackground(Color.RED);

			this.repaint();
		}
	}

	/**
	 * Changes the color and text of the bucket we are looking at depending of the
	 * state of the bucket when we "exit" the bucket from an attempted guess.
	 * 
	 * @param position - The box that we are entering.
	 */
	public void guiBucketExit(int position) {
		if (useGui && position < 81) {
			buckets[position / 9][position % 9].label.setText(sudokuArray[position] + "");
			if (sudokuArray[position] == empty) {
				buckets[position / 9][position % 9].setBackground(Color.WHITE);
			}
			if (position > 0) {
				if (originalSudokuArray[position - 1] != empty) {
					buckets[(position - 1) / 9][(position - 1) % 9].setBackground(Color.LIGHT_GRAY);
				} else {
					buckets[(position - 1) / 9][(position - 1) % 9].setBackground(Color.RED);
				}
			}
		}
	}

	/**
	 * When the sudoku puzzle is finished, this should be called to set the colors and 
	 * game status text appropriately.
	 */
	public void finishSudoku() {
		pauseSudokuButton.setEnabled(false);
		if (!stopSudoku) {
			boolean solved = this.solved();
			if (solved) {
				gameStatus.setText("Sudoku Puzzle Solved!");
			} else {
				gameStatus.setText("No possible solution...");
			}
			for (int i = 0; i < 81; i++) {
				if (originalSudokuArray[i] != empty) {
					buckets[i / 9][i % 9].setBackground(Color.LIGHT_GRAY);
				} else {
					if (solved) {
						buckets[i / 9][i % 9].setBackground(Color.GREEN);
						buckets[i / 9][i % 9].label.setText(sudokuArray[i] + "");
					} else {
						buckets[i / 9][i % 9].setBackground(Color.WHITE);
						buckets[i / 9][i % 9].label.setText(originalSudokuArray[i] + "");
					}
				}
			}
		}
	}
	
	/**
	 * GUI action to take when opening a sudoku puzzle. This will open File Chooser.
	 */
	private void openPuzzleFile() {

		int returnOption = fileChooser.showOpenDialog(this);

		if (returnOption == JFileChooser.APPROVE_OPTION) {
			solving = true;
			if (solver != null && solver.isRunning()) {
				stopSudoku = true;
				while (solver.isRunning()) {
				}
				stopSudoku = false;
			}
			solving = false;
			solver = new SudokuSolverThread(this);
			File file = fileChooser.getSelectedFile();
			openFile(file.getPath());
			solveSudokuButton.setEnabled(true);
			pauseSudokuButton.setEnabled(false);

			for (int i = 0; i < 81; i++) {
				if (sudokuArray[i] != 0) {
					buckets[i / 9][i % 9].setBackground(Color.LIGHT_GRAY);
				} else {
					buckets[i / 9][i % 9].setBackground(Color.WHITE);
				}
				buckets[i / 9][i % 9].label.setText(sudokuArray[i] + "");
			}
		}

		this.repaint();

	}
	
	/*
	 * *** END GUI METHODS ***
	 * You can write your own methods before or after these, but not in the middle.
	 * 
	 */

}
