package com.despegar.finalproject;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.naming.BinaryRefAddr;

import com.despegar.highflight.utils.Matrix2DCellPosition;
import com.despegar.highflight.utils.MatrixUtils;

public class Grid {
	
	private Cell[][] gridCell;
	private int[][] binaryGrid;
	
	private int cols;
	private int rows;
		
	public Grid(int cols, int rows){
		
		this.cols = cols;
		this.rows = rows;
		
		//Create Grids
		this.binaryGrid = new int[this.cols][this.rows];
		this.gridCell = new Cell[this.cols][this.rows];
		
		//Method that complete grid of cells;
		this.completeGrid();
	}
	
	private void completeGrid(){
		//Complete Grid
		for (int r = 0; r < this.rows;r++){
			for (int c = 0; c < this.cols; c++){
				Cell oCell = new Cell(r,c);
				this.gridCell[r][c] = oCell;
			}
		}
	}

	//Getters and setters
	public Cell[][] getGridCell() {
		return gridCell;
	}

	public void setGridCell(Cell[][] gridCell) {
		this.gridCell = gridCell;
	}

	public int[][] getBinaryGrid() {
		return binaryGrid;
	}

	public void setBinaryGrid(int[][] binaryGrid) {
		this.binaryGrid = binaryGrid;
	}

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
}

	
	