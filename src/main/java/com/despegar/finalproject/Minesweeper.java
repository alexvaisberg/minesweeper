package com.despegar.finalproject;

public interface Minesweeper {
	
	public void uncover(int row, int col);
	

	public void flagAsMine(int row, int col);
	public void clearFlag(int row, int col);
	
	
	public boolean isGameOver();
	public boolean isWinningGame();
	

	public void display();
	public void displayInternal();
	public void displayRaw();
}
