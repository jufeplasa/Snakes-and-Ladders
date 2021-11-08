package model;

public class Square {

	private int rows;
	private int columns;
	private int ladder;
	private char snake;

	private Square next;
	private Square up;
	private Square down;
	private Square previous;
	private int position;

	public Square(int n, int m, int p) {
		rows=n;
		columns=m;
		position = p;

		ladder=0;
		snake=0;
	}

	public int getLadder() {
		return ladder;
	}

	public void setLadder(int ladder) {
		this.ladder = ladder;
	}

	public char getSnake() {
		return snake;
	}

	public void setSnake(char snake) {
		this.snake = snake;
	}


	public int getPosition() {
		return position;
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
		if(ladder!=0) {
			return "["+position+" "+ladder+"]";
		}
		else if(snake!=0) {
			return "["+position+" "+snake+"]";
		}
		else {
			return "["+position+"]";
		}
	}

}
