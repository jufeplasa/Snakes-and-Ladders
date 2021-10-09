package model;


public class Board {
	private Player first;
	
	public Board() {
	}
	
	public void addPlayer(String token, int position) {
		Player newPlayer = new Player(token, position);
		if(first == null) {
			first = newPlayer;
		}
		else {
			Player last =first;
			while(last.getNext()!=null) {
				last=last.getNext();
			}
			
			last.setNext(newPlayer);
		}
	}

}
