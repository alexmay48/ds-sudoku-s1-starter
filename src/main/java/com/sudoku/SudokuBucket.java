package com.sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class SudokuBucket extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7881887532146180975L;
	
	JLabel label;
	
	public SudokuBucket() {
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		this.setPreferredSize(new Dimension(30, 30));
		this.setLayout(new FlowLayout());
		this.setBackground(Color.white);
		this.setBorder(border);
		label = new JLabel("", SwingConstants.CENTER);
		label.setFont(label.getFont().deriveFont(15.0f));
		this.add(label);
	}

}
