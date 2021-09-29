package com.sudoku;

import java.util.concurrent.atomic.AtomicBoolean;

public class SudokuSolverThread implements Runnable {

	private Sudoku view;
	public Thread worker;
	private AtomicBoolean running = new AtomicBoolean(false);
	private AtomicBoolean stopped = new AtomicBoolean(true);

	public SudokuSolverThread(Sudoku view) {
		this.view = view;
	}

	public void interrupt() {
		running.set(false);
		worker.interrupt();
	}

	boolean isRunning() {
		return running.get();
	}

	boolean isStopped() {
		return stopped.get();
	}

	public void start() {
		worker = new Thread(this);
		worker.start();
	}

	public void stop() {
		running.set(false);
	}

	public void run() {
		running.set(true);
		stopped.set(false);
		this.view.solveSudoku();
		this.view.finishSudoku();
		running.set(false);
		stopped.set(true);
	}

}
