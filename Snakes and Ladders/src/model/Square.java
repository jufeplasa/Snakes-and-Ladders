package model;

public class Square {

	private int rows;
	private int columns;
	private int ladder;
	private int position;
	private char snake;

	private Square next;
	private Square up;
	private Square down;
	private Square previous;
	private Player firstToken;

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
	
	public int cifras(int num) {
		int cifra=0;
		if(num!=0) {
			num=num/10;
			cifra++;
			cifra+=cifras(num);
		}
		return cifra;
	}

	public String toString() {
		
		int cifras = cifras(position);
		
		if(ladder!=0) {			
			if(cifras>=2) {
				return "["+position+""+ladder+"]";}
			else {return "[ "+position+""+ladder+"]";}

		}
		else if(snake!=0) {
			if(cifras>=2) {
			return "["+position+snake+"]";}
			else{return "[ "+position+snake+"]";}
			
		}
		else {
			if(cifras>=2) {
				return "["+position+" ]";}
			else {return "[ "+position+" ]";}

		}
	}

	public Player getFirstToken() {
		return firstToken;
	}

	public void setToken(Player token) {
		this.firstToken = token;
	}

	public void addToken(Player newToken, Player aux) {
		if(firstToken == null) {
			firstToken= newToken;
		}
		else {
			Player last =aux;
			if(last.getNextToken()!=null) {
				addToken(newToken, aux.getNextToken());
			}
			else {
				last.setNextToken(newToken);
			}
		}
	}

	public void remove(String token, Player aux) {
		Player removed=null;
	
		if(token.equalsIgnoreCase(firstToken.getToken())) {
			removed=firstToken;
			firstToken=firstToken.getNextToken();
			removed.setNextToken(null);
		}
		else {
			Player prev=aux;
			Player current=aux.getNextToken();
			if(current!=null) {
				if(current.getToken().equalsIgnoreCase(token)) {
					removed=current;
					prev.setNextToken(current.getNextToken());
					removed.setNextToken(null);
				}
				else {
					prev=current;
					remove(token,prev);
				}
			}
		}
		
	}

public String printTokens(Player newToken) {
	Player current = newToken;
	String message="";
	if(current!=null) {
		message+=current.toString();
		if(current.getNextToken()!=null) {
			message+=printTokens(current.getNextToken());
		}
	}
	return message;
}

public String showSquare() {
	String tokens=printTokens(firstToken);
	if(ladder!=0) {
		if(firstToken==null) {
			return "["+ladder+"  ]";
			
		}else {
		return "["+ladder+" "+tokens+"]";}
	}
	else if(snake!=0) {
		if(firstToken==null) {
			return "["+snake+"  ]";
		}else {
		return "["+snake+" "+tokens+"]";}
	}
	else if (tokens.equalsIgnoreCase("")) {
		return "[   ]";
		
	}
	else {
		return "[ "+tokens+" ]";}
		
	}

}


