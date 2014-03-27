package com.despegar.finalproject;

public class Cell {
	
	private int col;
	private int row;
	private boolean flagAsMine;
	private boolean mine;
	private int qtyMines_around; //If the cell value is "-1", then the cell mine contains
	private boolean uncovered;
	
	public Cell(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void displayCell(){
		if (this.isUncovered()){
			if (this.isMine()){
				System.out.print("M ");
			}else{
				System.out.print(qtyMines_around+" ");
			}
		}else if(this.isFlagAsMine()){
				System.out.print("F ");
			}else{
				System.out.print("- ");
			}
		}
	
	public void display_CellInternal(){
		if (isMine()){
			System.out.print("M ");
		}else{
			System.out.print(this.qtyMines_around + " ");
		}
	}
	
	public void display_CellRaw(){
		if (isMine()){
			System.out.print(1 + " ");
		}else{
			System.out.print(0 + " ");
		}
	}
	
	//Getters and setters
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public boolean isFlagAsMine() {
		return flagAsMine;
	}
	public void setFlagAsMine(boolean flagAsMine) {
		this.flagAsMine = flagAsMine;
	}
	public boolean isMine() {
		return mine;
	}
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	public int getQtyMines_around() {
		return qtyMines_around;
	}
	public void setQtyMines_around(int qtyMines_around) {
		this.qtyMines_around = qtyMines_around;
	}
	public boolean isUncovered() {
		return uncovered;
	}
	public void setUncovered(boolean uncovered) {
		this.uncovered = uncovered;
	}
	
	
	

}
