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
	board[r][c] = new Queen(true);
    }
    public void printBoard() {
	for (Piece[] row : board) {
	    for (Piece p : row) {
		System.out.print(p);
	    } 
	    System.out.println();
	}
    }
}
