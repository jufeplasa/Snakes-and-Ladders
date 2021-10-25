package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.Board;
import model.Player;

public class Main {
	private static Scanner sc;
	private static Board board;
	public static void main(String[] args) throws IOException {
		
		sc=new Scanner(System.in);
		boolean conti=true;
		while(conti) {
			int choose=showMenu();
			switch(choose) {

			case 1:
				
				enterValues();
				break;

			case 2:
				System.out.println(board.showBoard());
				break;

			case 3:
				showPlayers(board.getFirstPlayer());
				break;
				
				
			case 4:
				conti=false;
				break;
			}
		}
	}

	public static int showMenu() {
		int option;

		System.out.println(" Select an option ");
		System.out.println("1: Registered game values");
		System.out.println("2: Show the board");
		System.out.println("3: Show players");
		System.out.println("4: Exit of the program ");
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
		board=new Board(Integer.parseInt(part[0]),Integer.parseInt(part[1]));
		board.createSquares();
		board.addPlayer(token,0);
	}
	
	public static void showPlayers(Player player) {
		Player current=player;
		if(current!=null) {
			System.out.println("el jugador: "+current.getToken());
			if(current.getNext()!=null) {
				showPlayers(player.getNext());
			}
		}
	}
}