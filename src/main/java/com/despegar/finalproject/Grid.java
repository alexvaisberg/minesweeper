package com.despegar.finalproject;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.naming.BinaryRefAddr;

import com.despegar.highflight.utils.Matrix2DCellPosition;
import com.despegar.highflight.utils.MatrixUtils;

public class Grid implements Minesweeper {
	
	Cell[][] gridCell;
	int[][] binaryGrid;
	
	private int cols;
	private int rows;
	
	private int mines;
	private int uncoverCells;
	
	private int statusGame; //-1 = lose;0 = game; 1= win;
		
	public Grid(int cols, int rows){
		
		this.cols = cols;
		this.rows = rows;
		this.statusGame = 0;
		this.uncoverCells = 0;
		
		//Create Grids
		this.binaryGrid = new int[this.cols][this.rows];
		this.gridCell = new Cell[this.cols][this.rows];
		
		//Complete Grid
		for (int r = 0; r < this.rows;r++){
			for (int c = 0; c < this.cols; c++){
				Cell oCell = new Cell(r,c);
				this.gridCell[r][c] = oCell;
			}
		}
		
		//Put mines in the grid.
		Random rand = new Random();
		this.mines = 5;
		int countMines = 0;
		while(countMines < this.mines){
			countMines++;
			int x = rand.nextInt(this.rows - 1);
			int y = rand.nextInt(this.cols - 1);
			this.gridCell[x][y].setMine(true);
		}
		
		//Complete binaryGrid
		for (int r = 0; r < this.rows;r++){
			for (int c = 0; c < this.cols; c++){
				if (thereIsMine(r,c)){
					this.binaryGrid[r][c] = 1;
				}else{
					this.binaryGrid[r][c] = 0;
				}
			}
		}
		
		//Put quantity mines around
		for (int r = 0; r < this.rows;r++){
			for (int c = 0; c < this.cols; c++){
				if (!thereIsMine(r,c)){
					Set<Cell> listCells = complete_adjacentCells(this.gridCell[r][c].getRow(), this.gridCell[r][c].getCol());
					int qty_MinesAdj = 0;
					//Get the eight adjacent cells of the collection
					for(Cell adjCell:listCells){
						if (adjCell.getRow() >= 0 && adjCell.getCol() >= 0 && adjCell.getRow() < this.rows && adjCell.getCol() < this.cols){
								if (this.gridCell[adjCell.getRow()][adjCell.getCol()].isMine()){
									qty_MinesAdj++;
								}
						}
					}
					this.gridCell[r][c].setQtyMines_around(qty_MinesAdj);
				}
			}
		}
	}
	
	public void uncover(int row, int col){
		if (!thereIsMine(row,col)){
			if (this.gridCell[row][col].getQtyMines_around() == 0){
				Set<Matrix2DCellPosition> collectionCells= MatrixUtils.cascade(this.binaryGrid, row, col);
				for(Matrix2DCellPosition adjCell:collectionCells ){
					this.uncoverCells++;
					this.gridCell[adjCell.getRow()][adjCell.getColumn()].setUncovered(true);
				}
			}else{
				this.gridCell[row][col].setUncovered(true);
				this.uncoverCells++;
			}
		}else{
			this.statusGame = -1;
		}
	}

	public void flagAsMine(int row, int col) {
		this.gridCell[row][col].setFlagAsMine(true);
	}

	public void clearFlag(int row, int col){
		this.gridCell[row][col].setFlagAsMine(false);
	}

	public boolean isGameOver() {
		//Two posibilities:
		//Winning Game or Losing Game
		if (isWinningGame() || this.statusGame == -1){
			showMines();
			return true;
		}else{
			return false;
		}
	}

	public boolean isWinningGame() {
		int qtyCells_withoutMine = (this.rows * this.cols) - this.mines;
		if (this.uncoverCells == qtyCells_withoutMine){
			this.statusGame = 1;
			return true;
		}
		else{
			return false;
		}
	}
	
	public void showMines(){
		for (int r = 0; r < this.rows;r++){
			for (int c = 0; c < this.cols; c++){
				if(thereIsMine(r,c)){
					this.gridCell[r][c].setUncovered(true);
				}
			}
		}
	}

	public void display() {
		for (int r = 0; r < this.rows;r++){
			for (int c = 0; c < this.cols; c++){
				this.gridCell[r][c].displayCell();
			}
		System.out.println(" \n\t ");
		}
	}

	public void displayInternal() {
		for (int r = 0; r < this.rows;r++){
			for (int c = 0; c < this.cols; c++){
				this.gridCell[r][c].display_CellInternal();
			}
			System.out.println(" \n\t ");
		}	
	}

	public void displayRaw() {
		for (int r = 0; r < this.rows;r++){
			for (int c = 0; c < this.cols; c++){
				this.gridCell[r][c].display_CellRaw();
			}
			System.out.println(" \n\t ");
		}	}
	
	private boolean thereIsMine(int row, int col){
		return this.gridCell[row][col].isMine();
	}

	public Set<Cell> complete_adjacentCells(int row, int col){		
		//A new object instance referred that references the currentCell
		Set<Cell> listCellCoordinates= new HashSet<Cell>();
		if (!thereIsMine(row,col)){		
			//Add adjacent cells - Total: 8
			listCellCoordinates.add(new Cell(this.gridCell[row][col].getRow() -1,this.gridCell[row][col].getCol()));
			listCellCoordinates.add(new Cell(this.gridCell[row][col].getRow() +1,this.gridCell[row][col].getCol()));
			listCellCoordinates.add(new Cell(this.gridCell[row][col].getRow() +1,this.gridCell[row][col].getCol() - 1));
			listCellCoordinates.add(new Cell(this.gridCell[row][col].getRow() -1,this.gridCell[row][col].getCol() + 1));
			listCellCoordinates.add(new Cell(this.gridCell[row][col].getRow() -1,this.gridCell[row][col].getCol() -1));
			listCellCoordinates.add(new Cell(this.gridCell[row][col].getRow(),this.gridCell[row][col].getCol() - 1));
			listCellCoordinates.add(new Cell(this.gridCell[row][col].getRow(),this.gridCell[row][col].getCol() + 1));
			listCellCoordinates.add(new Cell(this.gridCell[row][col].getRow()+1,this.gridCell[row][col].getCol() + 1));
		}
		return listCellCoordinates;
	}
	
	//Getters and setters
	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getMines() {
		return mines;
	}

	public void setMines(int mines) {
		this.mines = mines;
	}

	public int getUncoverCells() {
		return uncoverCells;
	}

	public void setUncoverCells(int uncoverCells) {
		this.uncoverCells = uncoverCells;
	}

	public int getStatusGame() {
		return statusGame;
	}

	public void setStatusGame(int statusGame) {
		this.statusGame = statusGame;
	}
}
