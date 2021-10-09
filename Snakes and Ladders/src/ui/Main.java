package ui;

import java.util.Scanner;

import model.Board;

public class Main {
	private static Board board;
	private static Scanner sc;
	public static void main(String[] args) {
		board=new Board();
		sc=new Scanner(System.in);
		enterValues();
	}

	public static void enterValues() {
		System.out.println("Please enter the values to the game in the next order:\n ");
		System.out.println("# files, # columns, # snakes, # ladders and tokens of players ");
		String values=sc.next();
	}	
}