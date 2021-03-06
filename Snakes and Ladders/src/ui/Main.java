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
		menu(true);
		menu2(true);
	}
	
	public static void menu(boolean conti) throws IOException {
		
		if(conti) {
			int choose=showMenu();
			switch(choose) {

			case 1:
				enterValues();
				board.setLastSquare();
				conti=false;
				System.out.println(board.showBoard());
				
				break;
				
			case 2:
				conti=false;
				System.out.println("You have exit of the game.");
				break;
			}
		}
		
	}
	
	public static void menu2(boolean conti) {
		if(conti) {
			String choose=showMenu2();
			switch(choose) {

			case "":
				System.out.println(board.move(throwDices()));
				System.out.println(board.showCurrentBoard());
				String changeSquare=board.checkSnakeandLadder();
				if(changeSquare!=null) {
					System.out.println(changeSquare);
					System.out.println(board.showCurrentBoard());
				}
				if(board.endGame()) {
					System.out.println("The player "+board.getCurrentPlayer().getToken()+" has win the game, with "+board.getCurrentPlayer().getAttempts()+" attemps");
					System.out.println("CONGRATULATIONS!!!");
					menu2(false);
				}
				else {
					board.nextTurn();
					menu2(true);
				}
				
				break;
				
			case "num":
				System.out.println(board.showBoard());
				menu2(true);
				break;
				
			case "menu":
				showMenu();
				menu2(true);
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
	
	
	public static String showMenu2() {
		String option="";
		System.out.println("It's turn to player: "+showPlayerTurn()+"\nWhat do you want to do?");
		System.out.println("\nPress enter to keep playing ");
		System.out.println("num: Show the board game ");
		System.out.println("menu: Show the board game ");
		option=sc.nextLine();
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
		board.createSnakes(Integer.parseInt(part[2]));
		board.createLadders(Integer.parseInt(part[3]));
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