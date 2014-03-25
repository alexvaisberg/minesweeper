package com.despegar.finalproject;

public interface Minesweeper {
	
	//El jugador descubre una celda.
	public void uncover(int row, int col);
	
	//Marcar o desmarcar celdas que supone que son minas.
	public void flagAsMine(int row, int col);
	public void clearMine(int row, int col);
	
	//Metodos para saber el estado del juego (Gano/Perdio)
	public boolean isGameOver();
	public boolean isWinningGame();
	
	//
	public void display();
	public void displayInternal();
	public void displayRaw();
	

}
