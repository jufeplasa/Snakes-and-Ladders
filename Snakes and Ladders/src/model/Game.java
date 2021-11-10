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

	public void setLastSquare() {
		int max=numColums*numRows;
		lastSquare =findSquare(max,firstSquare);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void nextTurn() {
		currentPlayer = currentPlayer.getNext();
	}

	public void addPlayer(String[] token, int i) {
		if(i<token.length) {
			Player newPlayer = new Player(token[i], firstSquare);
			firstSquare.addToken(newPlayer, firstSquare.getFirstToken());
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
				current.setPrevious(prev); 
				prev.setNext(current); 


				if(rowPrev!=null) { 
					rowPrev=rowPrev.getNext(); 
					current.setUp(rowPrev); 
					rowPrev.setDown(current); 
				} 

				createColum(i,j+1,current, rowPrev,p-1);
			}
		}


	} 

	public String showBoard() { 
		String message=""; 
		if(numRows%2==0) {
			message=showRow(lastSquare);
		}
		else {
			int auxPosition=(numRows*numColums)-(numColums-1);
			Square auxSquare=findSquare(auxPosition, firstSquare);
			message=showRow(auxSquare);
		}
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
			message+=showColumn(current.getNext()); 

		}
		return message; 
	} 


	public String showCurrentBoard() { 
		String message=""; 
		if(numRows%2==0) {
			message=showCurrentRow(lastSquare);
		}
		else {
			int auxPosition=(numRows*numColums)-(numColums-1);
			Square auxSquare=findSquare(auxPosition, firstSquare);
			message=showCurrentRow(auxSquare);
		} 
		return message; 
	} 

	public String showCurrentRow(Square lastRow) { 
		String message=""; 
		if(lastRow!=null) { 
			message=showCurrentColumn(lastRow)+"\n"; 
			message+=showCurrentRow(lastRow.getUp()); 
		} 
		return message; 
	} 

	private String showCurrentColumn(Square current) { 
		String message=""; 
		if(current!=null) {
			message=current.showSquare(); 
			message+=showCurrentColumn(current.getNext()); 

		}
		return message; 
	} 


	public boolean endGame() {
		int max=numColums*numRows;
		if(currentPlayer.getPosition().getPosition()==max) {
			return true;
		}
		else {
			return false;
		}
	}

	public String move(int step) {
		Square auxPosition=currentPlayer.getPosition();
		currentPlayer.getPosition().remove(currentPlayer.getToken(),auxPosition.getFirstToken() );
		int value=step+currentPlayer.getPosition().getPosition();
		if(value>=(numColums*numRows)) {
			value=numColums*numRows;
		}
		Square nextSquare=findSquare(value,firstSquare);
		currentPlayer.setPosition(nextSquare);
		currentPlayer.addAttempts();
		nextSquare.addToken(currentPlayer, nextSquare.getFirstToken());
		String message="The player is in the Square "+currentPlayer.getPosition().getPosition();
		return message;
	}

	public Square findSquare(int num,Square auxSquare) {
		Square findSquare; 
		findSquare=findRow(num,auxSquare); 
		return findSquare; 
	}

	public Square findRow(int step, Square auxSquare) {
		Square findSquare=null; 
		if(auxSquare!=null) { 
			findSquare=findColum(step, auxSquare);
			if(findSquare==null) {
				findSquare=findRow(step, auxSquare.getDown());
			}
		} 
		return findSquare; 
	}

	public Square findColum(int step, Square auxSquare) {
		Square findSquare=null; 
		if(auxSquare!=null) { 
			if(  auxSquare.getPosition()==step) {
				findSquare=auxSquare;
			}
			else {
				findSquare=findColum(step, auxSquare.getNext());
			}
		}
		return findSquare;
	}

	public void createLadders(int ladder) {
		if(ladder>0) {
			int max=numColums*numRows;
			int min=numColums+1;
			int tail=(int) Math.floor(Math.random()*(max-min)+min);
			int maxHead;
			if(tail>numColums) {
				maxHead=tail-numColums;
			}
			else {
				maxHead=tail-1;
			}
			int head=(int) Math.floor(Math.random()*(maxHead-2)+2);
			Square tailLadder=findSquare(tail, firstSquare);
			Square headLadder=findSquare(head, firstSquare);
			if(tailLadder.getLadder()==0 && headLadder.getLadder()==0 && tailLadder.getSnake()==0 && headLadder.getSnake()==0) {
				tailLadder.setLadder(ladder);
				headLadder.setLadder(ladder);
				createLadders(ladder-1);
			}
			else {
				createLadders(ladder);
			}
		}
	}

	public void createSnakes(int snake) {

		if(snake>0) {
			int max=(numColums*numRows)-1;
			int min=numColums+1;
			int head=(int) Math.floor(Math.random()*(max-min)+min);

			int maxTail;
			if(head>numColums) {
				maxTail=head-numColums;
			}
			else {
				maxTail=head-1;
			}
			int tail=(int) Math.floor(Math.random()*(maxTail-1)+1);
			Square tailLadder=findSquare(tail, firstSquare);
			Square headLadder=findSquare(head, firstSquare);
			if(tailLadder.getLadder()==0 && headLadder.getLadder()==0 && tailLadder.getSnake()==0 && headLadder.getSnake()==0) {
				tailLadder.setSnake((char) (64+snake));
				headLadder.setSnake((char) (64+snake));
				createSnakes(snake-1);
			}
			else {
				createSnakes(snake);
			}
		}
	}

	public String checkSnakeandLadder() {
		String message=null;
		if(currentPlayer.getPosition().getLadder()!=0) {
			Square current=currentPlayer.getPosition();
			Square ladder= findLadder(current.getLadder(),firstSquare);
			if(ladder.getPosition()>current.getPosition()) {
				currentPlayer.getPosition().remove(currentPlayer.getToken(),current.getFirstToken() );
				currentPlayer.setPosition(ladder);
				currentPlayer.addAttempts();
				ladder.addToken(currentPlayer, ladder.getFirstToken());
				message="You found a ladder so you move along to "+ladder.getPosition()+" position";
			}
		}
		else if(currentPlayer.getPosition().getSnake()!=0) {
			Square current=currentPlayer.getPosition();
			Square snake= findSnake(current.getSnake(),firstSquare);
			if(snake.getPosition()<current.getPosition()) {
				currentPlayer.getPosition().remove(currentPlayer.getToken(),current.getFirstToken() );
				currentPlayer.setPosition(snake);
				currentPlayer.addAttempts();
				snake.addToken(currentPlayer, snake.getFirstToken());
				message="OH OH you found a snake, so you come back to "+snake.getPosition()+" position";
			}
		}
		return message;
	}

	public Square findLadder(int ladder,Square auxSquare) {
		Square findSquare; 
		findSquare=findLadderRow(ladder,auxSquare); 
		return findSquare; 
	}

	public Square findLadderRow(int ladder, Square auxSquare) {
		Square findSquare=null; 
		if(auxSquare!=null) { 
			findSquare=findLadderColum(ladder, auxSquare);
			if(findSquare==null) {
				findSquare=findLadderRow(ladder, auxSquare.getDown());
			}
		} 
		return findSquare; 
	}

	public Square findLadderColum(int ladder, Square auxSquare) {
		Square findSquare=null; 
		if(auxSquare!=null) { 
			if(auxSquare!=currentPlayer.getPosition() && auxSquare.getLadder()==ladder) {
				findSquare=auxSquare;
			}
			else {
				findSquare=findLadderColum(ladder, auxSquare.getNext());
			}
		}
		return findSquare;
	}


	public Square findSnake(char snake,Square auxSquare) {
		Square findSquare; 
		findSquare=findSnakeRow(snake,auxSquare); 
		return findSquare; 
	}

	public Square findSnakeRow(char snake, Square auxSquare) {
		Square findSquare=null; 
		if(auxSquare!=null) { 
			findSquare=findSnakeColum(snake, auxSquare);
			if(findSquare==null) {
				findSquare=findSnakeRow(snake, auxSquare.getDown());
			}
		} 
		return findSquare; 
	}

	public Square findSnakeColum(char snake, Square auxSquare) {
		Square findSquare=null; 
		if(auxSquare!=null  ) { 
			if( auxSquare!=currentPlayer.getPosition()&& auxSquare.getSnake()==snake) {
				findSquare=auxSquare;
			}
			else {
				findSquare=findSnakeColum(snake, auxSquare.getNext());
			}
		}
		return findSquare;
	}


}
