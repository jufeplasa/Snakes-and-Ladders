package model;


public class Board {
	private Player firstPlayer;
	private Square firstSquare;
	public Board() {
	}
	
	public void addPlayer(String[] token) {
		
		for(int i=0;i<token.length;i++) {
			Player newPlayer = new Player(token[i]);
			
			if(firstPlayer == null) {
				firstPlayer = newPlayer;
			}
			else {
				Player last =firstPlayer;
				while(last.getNext()!=null) {
					last=last.getNext();
				}
				
				last.setNext(newPlayer);
			}
		}
		
	}
	
	public void addSquares(int n, int m) {
		
	}
	
	public void addSnakes(int s) {
		
	}
	
	public void addLadders(int e) {
		
	}

}
