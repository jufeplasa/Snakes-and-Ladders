package model;

public class Player {
	
	private String token;
	private int position;
	private int attempts;
	
	
	private Player next;
	private Player previous;
	
	public Player(String token) {
		this.token = token;
		attempts=0;
		position = 1;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
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
