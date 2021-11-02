package model;


public class Game {
	private Player firstPlayer;
	private Player head;
	private Player tail;
	private Player currentPlayer;
	private Square firstSquare;

	private int numRows;
	private int numColums;
	public Game(int n, int m) {
		numRows=n;
		numColums=m;
		
	}

	public Player getFirstPlayer() {
		return firstPlayer;
	}
	
	public Player getHead() {
		return head;
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
			firstPlayer.setNext(firstPlayer);
			firstPlayer.setPrevious(firstPlayer);
			head=firstPlayer;
			tail=firstPlayer;
			currentPlayer=firstPlayer;
		}
		else {
		
			if(current.getNext()==firstPlayer) {
				firstPlayer.setNext(np);
				firstPlayer.getNext().setNext(head);
				tail=firstPlayer.getNext();
			}
			else {
				Player aux=current.getNext();
				if(aux!=head) {
					if(aux.getNext()==head) {
						tail=np;
						tail.setNext(head);
						head.setPrevious(tail);
						aux.setNext(tail);
						tail.setPrevious(aux);
					}
					else {
						createPlayer(np, current.getNext());
					}
				}
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

	public boolean endGame() {
		return false;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void nextTurn() {
		currentPlayer = currentPlayer.getNext();
	}
}
