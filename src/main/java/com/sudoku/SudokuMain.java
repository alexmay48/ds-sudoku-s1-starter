package com.sudoku;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * @author AlexMay
 *
 * This Main uses the Sudoku class to solve sudoku puzzles. 
 *
 */
public class SudokuMain 
{
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
	}
	
	private static void createAndShowGui() {
		JFrame window = new JFrame("Sudoku Solver");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setPreferredSize(new Dimension(400, 600));
		window.setLayout(null);
		
		Sudoku sudokuView = new Sudoku();
		sudokuView.setVisible(true);
		
		
		window.setTitle("Sudoku");
		window.setContentPane(sudokuView);
		window.setJMenuBar(sudokuView.createMenuBar());
		window.setVisible(true);
		
		window.pack();
	}
}
