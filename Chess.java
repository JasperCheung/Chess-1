public class Chess {
    //board encoded as columns by rows
    //to faciliate easy transfer between Chess coordinates and board
    //(white left rook is (0, 0))
    private Piece[][] board;

    public Chess() {
	board = new Piece[8][8];
	populateBoard();
    }
    
    //Loops through the board/ [][] and populates
    private void populateBoard() {
	for (int r = 0; r < 8; r++) {
	    for (int c = 0; c < 8; c++) {
		setBoardPiece(c, r);
	    }
	}
    }
    // create 1 piece on board
    private void setBoardPiece(int c, int r) {
        if (r > 1 && r < 6)
            return;
        
	Piece p;
        
	if (r == 0 || r == 7) {
	    // white = true, black = false
	    boolean color = r == 0;
            
	    if (c == 0 || c == 7)
		p = new Rook(color);
	    else if (c == 1 || c == 6)
		p = new Knight(color);
	    else if (c == 2 || c == 5)
		p = new Bishop(color);
	    else if (c == 3)
		p = new Queen(color);
	    else // c == 4
		p = new King(color);
            
	} else { //r == 1 or 6
            boolean color = r == 1;
            p = new Pawn(color);
        }
        
        board[c][r] = p;
    }

    //complete a turn for a player
    public void turn(boolean color) {
	//get valid input from user: get coord
	//check if valid coordinate: there is piece at coord?
	//check if has legal moves
	//gets input again for move
	//does move
    }
    public void doMove(int[] from, int[] to) {
	//check if kill, and do stuff?
	//assume no kill for now, just move
	
	board[to[0]][to[1]] = board[from[0]][from[1]];
	board[from[0]][from[1]] = null;
    }

    public void printBoard() {
        //print the last (black) row first down to white
	for (int r = 7; r > -1; r--) {
            
            //from each row print all the columns
	    String s = "";
	    for (int c = 0; c < 8; c++) {
		Piece p = board[c][r];
		if (p != null) {
		    s += p + " ";
		} else {
		    //put white boxes ■ for empty squares
		    //Box should appear if the terminal supports Unicode characters
		    if ((r + c) % 2 == 1) {
		        s += "■ ";
		    } else {
			s += "  ";
		    }
		}
	    }
	    System.out.println(s);
	}
	System.out.println();
    }
}
