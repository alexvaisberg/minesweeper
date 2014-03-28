package com.despegar.finalproject;

import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.println("Indique la cantidad de filas del tablero:");
		int rows = sc.nextInt();
		
		System.out.println("Indique la cantidad de columnas del tablero:");
		int cols = sc.nextInt();
		
		Minesweeper game = new MinesweeperImpl(rows,cols);

		
		System.out.println();
		while(!game.isGameOver()){
			
			System.out.println("Indique la fila de la celda");
			int row = sc.nextInt();
			
			//Validate row
			if(row < 0 || row > rows){
				System.out.println("El numero excede la cantidad de filas del tablero. Vuelva a comenzar el proceso:");
				continue;
			}
			
			System.out.println("Indique la columna de la celda");
			int col = sc.nextInt();
			
			//Validate column
			if(col < 0 || col > cols){
				System.out.println("El numero excede la cantidad de columnas del tablero. Vuelva a comenzar el proceso:");
				continue;
			}
			
			//Uncover=1; FlagAsMine=2; ClearFlag=3;
			System.out.println("Indique la accion a realizar (1=UNCOVER, 2=FLAG, 3=CLEAR FLAG)");
			int action = sc.nextInt();
			
			//Validate action
			/*if(action != 1 || action != 2 || action!=3 ){
				System.out.println("Opción invalida. Introduzca el nro de opción: 1(UNCOVER), 2(FLAG), 3(CLEAR FLAG). Vuelva a comenzar el proceso:");
				continue;
			}*/
			
			
			switch(action){
				case 1:
					//uncover
					game.uncover(row, col);
					break;
				case 2:
					//flag
					game.flagAsMine(row, col);
					break;
				case 3:
					//clear flag
					game.clearFlag(row, col);
					break;
			}
			
			game.display();
			System.out.println();
		}
		
		if (game.isWinningGame()){
			System.out.println("¡Ganaste!");
		}else{
			System.out.println("¡Perdiste! ");
			System.out.println();
			game.display();
		}
	}
}
