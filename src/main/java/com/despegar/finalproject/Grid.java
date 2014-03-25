package com.despegar.finalproject;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.naming.BinaryRefAddr;

public class Grid implements Minesweeper {
	
	Cell[][] gridCell;
	int[][] binaryGrid;
	int cols;
	int rows;
	int mines;
	int uncoverCells;
	
	int statusGame;
	
	public Grid(int cols, int rows){
		//Create a grid and cells
		this.cols = cols;
		this.rows = rows;
		this.gridCell = new Cell[this.cols][this.rows];
		for (int c = 0; c < this.cols - 1; c++){
			for (int r = 0; r < this.rows - 1;c++){
				gridCell[c][r] = new Cell(c,r);
			}
		}
				
		//Place mines in the grid.
		this.mines = cols*rows - 10;
		for (int i=0; i <= this.mines; i++){
			Random rand = new Random();
			int x = rand.nextInt(this.cols - 1);
			int y = rand.nextInt(this.rows - 1);
			
			if (gridCell[x][y].getValue() == -1){
				i = i - 1;
			}else{
				gridCell[x][y].setValue(-1);
			}
		}
		
		//Create binary grid
		binaryGrid= new int[this.cols][this.rows];
		for (int c = 0; c < this.cols - 1; c++){
			for (int r = 0; r < this.rows - 1;c++){
				if (gridCell[c][r].getValue() == -1){
					binaryGrid[c][r] = 1;
				}else{
					binaryGrid[c][r] = 0;
				}
			}
		}
	}
	
	public void uncover(int row, int col){
		if(thereIsMine(row, col)){
			this.statusGame=0;
		}else{
			this.gridCell[row][col].setUncovered(true);
			this.uncoverCells++;
		}
	}

	public void flagAsMine(int row, int col) {
		this.gridCell[row][col].setFlag(true);
	}

	public void clearFlag(int row, int col){
		this.gridCell[row][col].setFlag(false);
	}

	public boolean isGameOver() {
		if (this.statusGame == 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean isWinningGame() {
		if (this.statusGame == 2){
			return true;
		}else{
			return false;
		}
	}

	public void display() {
		for (int c = 0; c < this.cols - 1; c++){
			for (int r = 0; r < this.rows - 1;c++){
				gridCell[c][r].displayCell();
			}
		}
		
	}

	public void displayInternal() {
		System.out.print(gridCell);
	}

	public void displayRaw() {
		System.out.print(binaryGrid);
	}
	
	private boolean thereIsMine(int row, int col){
		if (gridCell[row][col].getValue() == -1){
			return true;
		}else{
			return false;
		}
	}
	
	public void adjacentCell(int row, int col){
		Set<Cell> listCellCoordinates= new HashSet<Cell>();
		//Add adjacent cells
		listCellCoordinates.add(new Cell(row -1,col-1));
		listCellCoordinates.add(new Cell(row +1, col+1));
		listCellCoordinates.add(new Cell(row +1,col-1));
		listCellCoordinates.add(new Cell(row -1,col+1));
		listCellCoordinates.add(new Cell(row -1,col));
		listCellCoordinates.add(new Cell(row, col-1));
		listCellCoordinates.add(new Cell(row +1 ,col));
		listCellCoordinates.add(new Cell(row,col+1));
		
		int totalMinesAdj = 0;
		for(Cell adjCell:listCellCoordinates){
			if (adjCell.getRow() < 0 || adjCell.getCol() < 0 || adjCell.getRow() > this.rows || adjCell.getCol() > this.cols){
				//Cell not exist in the grid
			}else if (adjCell.isMine()){
					totalMinesAdj++;
					adjCell.setValue(totalMinesAdj++);
				}
			}
		
	}
	
	public static void main(String[] args){
		
	}

}
