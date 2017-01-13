public class Chess {
    private Piece[][] board;

    public Chess() {
	board = new Piece[8][8];
	populateBoard();
    }
    
    //Loops through the board/ [][] and populates
    private void populateBoard() {
	for (int r = 0; r < 8; r++) {
	    for (int c = 0; c < 8; c++) {
		setBoardPiece(r, c);
	    }
	}
    }
    // create 1 piece on board
    private void setBoardPiece(int r, int c) {
        if (r > 1 && r < 6)
            return;
        
	Piece p;
        
	if (r == 0 || r == 7) {
	    // white = true, black = false
	    boolean color = r == 7;
            
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
            
	} else if (r == 1)
	    p = new Pawn(false);
	else // r == 6
	    p = new Pawn(true);
        
        board[r][c] = p;
    }
    public void printBoard() {
	for (int r = 0; r < 8; r++) {
	    String s = "";
	    for (int c = 0; c < 8; c++) {
		Piece p = board[r][c];
		if (p != null) {
		    s += p + " ";
		} else {
		    //put white boxes ■ for empty squares
		    //Warning: Boxes only works for unix terminal
		    if ((r + c) % 2 == 0) {
		        s += "■ ";
		    } else {
			s += "  ";
		    }
		}
	    }
	    System.out.println(s);
	}
    }
}
