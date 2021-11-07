package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.Game;
import model.Player;

public class Main {
	private static Scanner sc;
	private static Game board;
	public static void main(String[] args) throws IOException {
		
		sc=new Scanner(System.in);
		boolean conti=true;
		boolean gameMenu=false;
		while(conti) {
			int choose=showMenu();
			switch(choose) {

			case 1:
				enterValues();
				conti=false;
				gameMenu=true;
				break;
				
			case 2:
				conti=false;
				System.out.println("You have exited of the game.");
				break;
			}
		}
		
		
		while(gameMenu) {
			int choose=showMenu2();
			switch(choose) {

			case 1:
				System.out.println(board.move(throwDices()));
				if(board.endGame()) {
					
				}
				else {
					board.nextTurn();
				}
				
				break;
				
			case 2:
				System.out.println(board.showBoard());
				break;
				
			case 3:
				showPlayers(board.getFirstPlayer());
				break;
			}
		}
		
	}

	public static int showMenu() {
		int option;
		System.out.println(" Select an option ");
		System.out.println("1: Registered game values");
		System.out.println("2: Exit of the program ");
		option=sc.nextInt();
		sc.nextLine();
		return option;
	}
	
	
	public static int showMenu2() {
		int option;
		System.out.println("It's turn to player: "+showPlayerTurn()+"\nWhat do you want to do?");
		System.out.println("1: Throw the dices");
		System.out.println("2: Show the board game ");
		System.out.println("3: Show the players ");
		
		option=sc.nextInt();
		sc.nextLine();
		return option;
	}
		

	public static void enterValues() throws IOException {
		String [] token;
		String [] part;
		BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
		System.out.println("Please enter the values to the game in the next order:\n ");
		System.out.println("# Rows, # columns, # snakes, # ladders and tokens of players ( * ! O X % $ # + &) ");
		String values=br.readLine();
		part=values.split("\\ ");
		token= part[4].split("");
		board=new Game(Integer.parseInt(part[0]),Integer.parseInt(part[1]));
		board.createSquares();
		board.addPlayer(token,0);
	}
	
	public static void showPlayers(Player player) {
		Player current=player;
		if(current!=null) {
			System.out.println("el jugador: "+current.getToken());
			if(current.getNext()!=board.getHead()) {
				showPlayers(player.getNext());
			}
		}
	}
	
	public static String showPlayerTurn() {
		String playerToken=board.getCurrentPlayer().getToken();
		return playerToken;
	}
	
	public static int throwDices() {
		int valueOfThrow=(int) Math.floor(Math.random()*6+1);
		System.out.println("The player has thrown the dice and it gets "+valueOfThrow);
		return valueOfThrow;
	}
}