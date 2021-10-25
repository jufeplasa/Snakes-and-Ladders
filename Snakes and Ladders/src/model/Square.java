package model;

public class Square {
	
	private int rows;
	private int columns;
	
	private Square next;
	private Square up;
	private Square down;
	private Square previous;

	public Square(int n, int m) {
		rows=n;
		columns=m;
		
	}
	
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}


	public Square getNext() {
		return next;
	}
	public void setNext(Square next) {
		this.next = next;
	}
	public Square getUp() {
		return up;
	}
	public void setUp(Square up) {
		this.up = up;
	}
	public Square getDown() {
		return down;
	}
	public void setDown(Square down) {
		this.down = down;
	}
	public Square getPrevious() {
		return previous;
	}
	public void setPrevious(Square previous) {
		this.previous = previous;
	}
	
	public String toString() {
		return "["+rows+","+columns+"]";
	}
	
}
