package com.despegar.finalproject;

public class Grid implements Minesweeper {
	
	int cols;
	int rows;
	int mines;

	public void Grid(int cols, int rows){
		this.cols = cols;
		this.rows = rows;
		int[][] grid = new int[this.cols][this.rows];
		mines = cols*rows - 10;
	}
	
	public void uncover(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	public void flagAsMine(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	public void clearMine(int row, int col) {
		// TODO Auto-generated method stub
		
	}

	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isWinningGame() {
		// TODO Auto-generated method stub
		return false;
	}

	public void display() {
		// TODO Auto-generated method stub
		
	}

	public void displayInternal() {
		// TODO Auto-generated method stub
		
	}

	public void displayRaw() {
		// TODO Auto-generated method stub
		
	}

}
