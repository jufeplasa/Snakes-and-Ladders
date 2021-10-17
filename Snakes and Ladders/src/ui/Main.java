package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.Board;

public class Main {
	private static Scanner sc;
	private static Board board;
	public static void main(String[] args) throws IOException {
		board=new Board();
		sc=new Scanner(System.in);
		boolean conti=true;
		while(conti) {
			int choose=showMenu();
			switch(choose) {

			case 1:
				
				enterValues();
				break;

			case 2:
				break;

			case 3:
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
		System.out.println("3: Exit of the program ");
		option=sc.nextInt();
		sc.nextLine();
		return option;
	}
		

	public static void enterValues() throws IOException {
		String [] token;
		String [] part;
		BufferedReader br= new BufferedReader( new InputStreamReader(System.in));
		System.out.println("Please enter the values to the game in the next order:\n ");
		System.out.println("# files, # columns, # snakes, # ladders and tokens of players ( * ! O X % $ # + &) ");
		String values=br.readLine();
		part=values.split("\\ ");
		token= part[4].split("");
		board.addSquares(Integer.parseInt(part[0]),Integer.parseInt(part[1]));
		board.addSnakes(Integer.parseInt(part[2]));
		board.addLadders(Integer.parseInt(part[3]));
		board.addPlayer(token);
		for(int i=0;i<part.length;i++) {
			System.out.println(part[i]);
		}
		for(int i=0;i<token.length;i++) {
			System.out.println(token[i]);
		}
		
	}	
}