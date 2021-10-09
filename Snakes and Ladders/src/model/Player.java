package model;

public class Player {
	private String token;
	private int position;
	private Player next;
	
	public Player(String token, int position) {
		this.token = token;
		this.position = position;
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
	
}
