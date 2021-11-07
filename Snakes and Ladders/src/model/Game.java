package model;


public class Game {

	private int numRows;
	private int numColums;
	private int auxColums;

	private Player firstPlayer;
	private Player head;
	private Player tail;
	private Player currentPlayer;
	private Square firstSquare;
	private Square lastSquare;

	public Game(int n, int m) {
		numRows=n;
		numColums=m;
		auxColums=0;
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

	public Square getLastSquare() {
		return lastSquare;
	}

	public void setLastSquare(Square lastSquare) {
		this.lastSquare = lastSquare;
	}

	public void addPlayer(String[] token, int i) {
		if(i<token.length) {
			System.out.println("va a agregar la ficha: "+token[i]);
			Player newPlayer = new Player(token[i], firstSquare);
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
		firstSquare= new Square(0,0,1);
		createRow(0,0,firstSquare,1);
	}

	public void createRow(int i, int j, Square firstRow, int p) {
		createColum(i ,j+1,firstRow,firstRow.getUp(), p); 
		if(i+1<numRows) { 
			i++;

			Square downSquare;
			if(i%2==0) {

				downSquare=new Square(i,j,p+1); 

				firstRow.setDown(downSquare); 
				downSquare.setUp(firstRow); 
				createRow(i,j,downSquare,p+1); 
			}
			else {
				auxColums++;
				downSquare=new Square(i,j,auxColums*(numColums+numColums)); 

				firstRow.setDown(downSquare); 
				downSquare.setUp(firstRow); 
				createRow(i,j,downSquare,auxColums*(numColums+numColums));

			}
		} 
	} 

	public void createColum(int i, int j, Square prev, Square rowPrev, int p) { 
		if(j<numColums) { 
			if(i%2==0) {
				Square current=new Square(i,j,p+1); 
				lastSquare=current;
				current.setPrevious(prev); 
				prev.setNext(current); 


				if(rowPrev!=null) { 
					rowPrev=rowPrev.getNext(); 
					lastSquare.setUp(rowPrev); 
					rowPrev.setDown(lastSquare); 
				} 

				createColum(i,j+1,current, rowPrev,p+1); 
			}
			else if(i%2!=0) {
				Square current=new Square(i,j,p-1); 
				lastSquare=current;
				current.setPrevious(prev); 
				prev.setNext(current); 


				if(rowPrev!=null) { 
					rowPrev=rowPrev.getNext(); 
					lastSquare.setUp(rowPrev); 
					rowPrev.setDown(lastSquare); 
				} 

				createColum(i,j+1,current, rowPrev,p-1);
			}
		}


	} 

	public String showBoard() { 
		String message=""; 
		message=showRow(lastSquare); 
		return message; 
	} 

	public String showRow(Square lastRow) { 
		String message=""; 
		if(lastRow!=null) { 
			message=showColumn(lastRow)+"\n"; 
			message+=showRow(lastRow.getUp()); 
		} 
		return message; 
	} 

	private String showColumn(Square current) { 
		String message=""; 
		if(current!=null) { 
			message=current.toString(); 
			message+=showColumn(current.getPrevious()); 
		}
		return message; 
	} 

	public boolean endGame() {
		return false;
	}

	public String move(int step) {
		int value=step+currentPlayer.getPosition().getPosition();
		Square nextSquare=findSquare(value,firstSquare);
		System.out.println(" valor de destino "+nextSquare.getPosition());
		currentPlayer.setPosition(nextSquare);
		String message="El jugador esta en la casilla "+currentPlayer.getPosition().getPosition();
		return message;
	}

	public Square findSquare(int num,Square findSquare) {
		int max=numColums*numRows;
		boolean find=false;
		if(num>=max) {
			return lastSquare;
		}
		else if(findSquare.getPosition()==num) {
			return findSquare;
		}
		else {
			return findSquare(num,findSquare.getNext());
		}
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void nextTurn() {
		currentPlayer = currentPlayer.getNext();
	}
}
