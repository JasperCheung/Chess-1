public class Chess {
    private Piece[][] board;

    public Chess() {
	board = new Piece[8][8];
	populateBoard();
    }
    private void populateBoard() {
	for (int r = 0; r < 8; r++) {
	    for (int c = 0; c < 8; c++) {
		setBoardPiece(r, c);
	    }
	}
    }
    // create 1 piece on board
    private void setBoardPiece(int r, int c) {
	Piece p = null;
        
	if (r == 0 || r == 7) {
	    boolean color = r == 7; //white if r == 7
	    if (c == 0 || c == 7)
		p = new Rook(color);
	    if (c == 1 || c == 6)
		p = new Knight(color);
	    if (c == 2 || c == 5)
		p = new Bishop(color);
	    if (c == 3)
		p = new Queen(color);
	    if (c == 4)
		p = new King(color);
	}
	if (r == 1)
	    p = new Pawn(false);
	if (r == 6)
	    p = new Pawn(true);
	
	if (p != null)
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
