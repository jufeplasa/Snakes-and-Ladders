package model;

public class Player {
	
	private String token;
	private Square position;
	private int attempts;
	
	
	private Player next;
	private Player previous;
	
	public Player(String token, Square p) {
		this.token = token;
		attempts=0;
		position = p;
	}
	
	public String getToken() {
		return token;
	}
	
	public Square getPosition() {
		return position;
	}
	public void setPosition(Square position) {
		this.position = position;
	}

	public Player getNext() {
		return next;
	}
	
	public void setNext(Player p) {
		next=p;
	}

	public int getAttempts() {
		return attempts;
	}

	public void addAttempts() {
		attempts++;
	}

	public Player getPrevious() {
		return previous;
	}

	public void setPrevious(Player previous) {
		this.previous = previous;
	}
	
}
