package com.despegar.finalproject;

import java.util.HashSet;
import java.util.Set;

public class Cell {
	
	int col;
	int row;
	private boolean flag;
	private boolean mine;
	private int value;
	private boolean uncovered;
	
	public Cell(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void displayCell(){
		if (isUncovered()){
			if (isMine()){
				System.out.print("M");
			}else{
				System.out.print(getValue());
			}
		}else{
			System.out.print("- ");
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
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isMine() {
		return mine;
	}
	public void setMine(boolean mine) {
		this.mine = mine;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isUncovered() {
		return uncovered;
	}
	public void setUncovered(boolean uncovered) {
		this.uncovered = uncovered;
	}
	
	
	

}
