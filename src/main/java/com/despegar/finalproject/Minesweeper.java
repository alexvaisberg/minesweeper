package com.despegar.finalproject;

public interface Minesweeper {
	// Allow the player to uncover a cell
	public void uncover(int row, int col);
	
	// Show all mines.
	public void showMines();
	
	// Marking/unmarking suspicious cells
	public void flagAsMine(int row, int col);
	public void clearFlag(int row, int col);
	
	// Game termination
	public boolean isGameOver();
	public boolean isWinningGame();
	
	// Operations for showing the current state of game grid
	// Public/visible grid for the player
	public void display();
	// Grid with all cells uncovered. For debug purposes
	public void displayInternal();
	// Binary grid: 1 if cell has a mine, 0 otherwise. For debug purposes
	public void displayRaw();
}
