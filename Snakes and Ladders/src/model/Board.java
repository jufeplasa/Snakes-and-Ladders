package model;


public class Board {
	private Player firstPlayer;
	private Square firstSquare;
	
	private int numRows;
	private int numColums;
	public Board(int n, int m) {
		numRows=n;
		numColums=m;
	}
	
	public Player getFirstPlayer() {
		return firstPlayer;
	}
	
	public Square getFirstSquare() {

		return firstSquare;
	}
	
	public void addPlayer(String[] token, int i) {
		if(i<token.length) {
			System.out.println("va a agregar la ficha: "+token[i]);
			Player newPlayer = new Player(token[i]);
			createPlayer(newPlayer, firstPlayer);
			addPlayer(token, i+1);
		}
	}
	
	public void createPlayer(Player np, Player current) {
		if(firstPlayer == null) {
			firstPlayer = np;
		}
		else {
			if(current.getNext()!=null) {
				current=current.getNext();
				createPlayer(np,current);			
			}
			else {
				current.setNext(np);
				System.out.println("agrego ficha : "+current.getNext().getToken());
			}
			
		}
	}

	public void createSquares() {
		firstSquare= new Square(0,0);
		createRow(0,0,firstSquare);
	}
	
	public void createRow(int i, int j, Square firstRow) {
		createColum(i ,j+1,firstRow,firstRow.getUp());
		if(i+1<numRows) {
			Square downSquare=new Square(i+1,j);
			firstRow.setDown(downSquare);
			downSquare.setUp(firstRow);
			createRow(i+1,j,downSquare);
		}
	}
	
	public void createColum(int i, int j, Square prev, Square rowPrev) {
		if(j<numColums) {
			Square current=new Square(i,j);
			current.setPrevious(prev);
			prev.setNext(current);
			
			
			if(rowPrev!=null) {
				rowPrev=rowPrev.getNext();
				current.setUp(rowPrev);
				rowPrev.setDown(current);
			}
			
			createColum(i,j+1,current, rowPrev );
		}
	}
	
	public String showBoard() {
		String message="";
		message=showRow(firstSquare);
		return message;
	}
	
	public String showRow(Square firstRow) {
		String message="";
		if(firstRow!=null) {
			message=showColumn(firstRow)+"\n";
			message+=showRow(firstRow.getDown());
		}
		return message;
	}
	
	private String showColumn(Square current) {
		String message="";
		if(current!=null) {
			message=current.toString();
			message+=showColumn(current.getNext());
		}
		return message;
	}
}
