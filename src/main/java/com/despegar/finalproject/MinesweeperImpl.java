package com.despegar.finalproject;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.despegar.highflight.utils.Matrix2DCellPosition;
import com.despegar.highflight.utils.MatrixUtils;

public class MinesweeperImpl implements Minesweeper{

	private Grid grid;
	private int statusGame; //-1 = lose;0 = game; 1= win;
	private int uncoverCells;
	private int mines;
	
	public MinesweeperImpl(int rows, int cols){
		this.statusGame = 0;
		this.uncoverCells = 0;
		
		this.grid = new Grid(rows,cols);
		this.putMines();
		this.completeGridRaw();
		this.putQtyMines_around();
	}
	
	public void uncover(int row, int col){
		if (!thereIsMine(row,col)){
			if (grid.getGridCell()[row][col].getQtyMines_around() == 0){
				Set<Matrix2DCellPosition> collectionCells= MatrixUtils.cascade(grid.getBinaryGrid(), row, col);
				for(Matrix2DCellPosition adjCell:collectionCells){
					if (!grid.getGridCell()[adjCell.getRow()][adjCell.getColumn()].isUncovered()){
						this.uncoverCells++;
						grid.getGridCell()[adjCell.getRow()][adjCell.getColumn()].setUncovered(true);
					}
				}
			}else{
				grid.getGridCell()[row][col].setUncovered(true);
				this.uncoverCells++;
			}
		}else{
			this.statusGame = -1;
		}
	}
	
	public void showMines(){
		for (int r = 0; r < grid.getRows();r++){
			for (int c = 0; c < grid.getCols(); c++){
				if(thereIsMine(r,c)){
					grid.getGridCell()[r][c].setUncovered(true);
				}
			}
		}
	}
	
	public void flagAsMine(int row, int col) {
		if (!grid.getGridCell()[row][col].isUncovered()){
			grid.getGridCell()[row][col].setFlagAsMine(true);
		}
	}

	public void clearFlag(int row, int col){
		grid.getGridCell()[row][col].setFlagAsMine(false);
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
		int qtyCells_withoutMine = (grid.getRows() * grid.getCols()) - this.mines;
		//If cells without mines is equal to the number of uncovered cell
		if (this.uncoverCells == qtyCells_withoutMine){
			this.statusGame = 1;
			return true;
		}
		else{
			return false;
		}
	}

	public void display() {
		for (int r = 0; r < grid.getRows();r++){
			for (int c = 0; c < grid.getCols(); c++){
				grid.getGridCell()[r][c].displayCell();
			}
		System.out.println(" \n\t ");
		}
	}

	public void displayInternal() {
		for (int r = 0; r < grid.getRows();r++){
			for (int c = 0; c < grid.getCols(); c++){
				grid.getGridCell()[r][c].display_CellInternal();
			}
			System.out.println(" \n\t ");
		}	
	}

	public void displayRaw() {
		for (int r = 0; r < grid.getRows();r++){
			for (int c = 0; c < grid.getCols(); c++){
				grid.getGridCell()[r][c].display_CellRaw();
			}
			System.out.println(" \n\t ");
		}	}
	
	private boolean thereIsMine(int row, int col){
		return grid.getGridCell()[row][col].isMine();
	}
	
	private Set<Cell> complete_adjacentCells(int row, int col){		
		//A new object instance referred that references the currentCell
		Set<Cell> listCellCoordinates= new HashSet<Cell>();
		if (!thereIsMine(row,col)){		
			//Add adjacent cells - Total: 8
					listCellCoordinates.add(new Cell(grid.getGridCell()[row][col].getRow() -1,grid.getGridCell()[row][col].getCol()));
					listCellCoordinates.add(new Cell(grid.getGridCell()[row][col].getRow() +1,grid.getGridCell()[row][col].getCol()));
					listCellCoordinates.add(new Cell(grid.getGridCell()[row][col].getRow() +1,grid.getGridCell()[row][col].getCol() - 1));
					listCellCoordinates.add(new Cell(grid.getGridCell()[row][col].getRow() -1,grid.getGridCell()[row][col].getCol() + 1));
					listCellCoordinates.add(new Cell(grid.getGridCell()[row][col].getRow() -1,grid.getGridCell()[row][col].getCol() -1));
					listCellCoordinates.add(new Cell(grid.getGridCell()[row][col].getRow(),grid.getGridCell()[row][col].getCol() - 1));
					listCellCoordinates.add(new Cell(grid.getGridCell()[row][col].getRow(),grid.getGridCell()[row][col].getCol() + 1));
					listCellCoordinates.add(new Cell(grid.getGridCell()[row][col].getRow()+1,this.grid.getGridCell()[row][col].getCol() + 1));							
		}
		return listCellCoordinates;
	}
	
	private void putMines(){
		//Put mines in the grid.
		Random rand = new Random();
		this.mines = (int) Math.rint(grid.getRows()*grid.getCols()*0.15);
		int countMines = 0;
		while(countMines < this.mines){
			int x = rand.nextInt(grid.getRows() - 1);
			int y = rand.nextInt(grid.getCols() - 1);
			if (!thereIsMine(x,y)){
				countMines++;
				grid.getGridCell()[x][y].setMine(true);
			}
		}
	}
	
	private void completeGridRaw(){
		//Complete binaryGrid
		for (int r = 0; r < grid.getRows();r++){
			for (int c = 0; c < grid.getCols(); c++){
				if (thereIsMine(r,c)){
					grid.getBinaryGrid()[r][c] = 1;
				}else{
					grid.getBinaryGrid()[r][c] = 0;
				}
			}
		}
	}
	
	private void putQtyMines_around(){
		//Put quantity mines around
		for (int r = 0; r < grid.getRows();r++){
			for (int c = 0; c < grid.getCols(); c++){
				if (!thereIsMine(r,c)){
					Set<Cell> listCells = complete_adjacentCells(grid.getGridCell()[r][c].getRow(), grid.getGridCell()[r][c].getCol());
					int qty_MinesAdj = 0;
					//Get the eight adjacent cells of the collection
					for(Cell adjCell:listCells){
						if (adjCell.getRow() >= 0 && adjCell.getCol() >= 0 && adjCell.getRow() < grid.getRows() && adjCell.getCol() < grid.getCols()){
								if (grid.getGridCell()[adjCell.getRow()][adjCell.getCol()].isMine()){
									qty_MinesAdj++;
								}
						}
					}
					grid.getGridCell()[r][c].setQtyMines_around(qty_MinesAdj);
				}
			}
		}
	}

}
