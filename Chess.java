import java.util.*;
public class Chess {
    //board encoded as columns by rows
    //to faciliate easy transfer between Chess coordinates and board
    //(white left rook is (0, 0))
    private Piece[][] board;

    public Chess() {
	board = new Piece[8][8];
	populateBoard();
    }
    
    public Piece[][] getBoard(){
	return board;
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
	//check if valid coordinate: there is own piece at coord?
	//gets input again for move
	//check if legal move
	//does move if legal
    }
    public boolean isLegalMove(int[] from, int[] to) {
	//is possible move (using Movement) 
	//and then do move (if attack, then keep track of attacked movement)
	//if own king in check -> not legal
	//undo move
	return true;
    }
    public boolean inCheck(boolean color) {
	//go through all pieces of other player
	//if they can attack own king -> in check
	return true;
    }
    public void doMove(int[] from, int[] to) {
	board[to[0]][to[1]] = board[from[0]][from[1]];
	board[from[0]][from[1]] = null;
    }
    //get possible move from coordinate (piece) (either movement or attack)
    //for movement: stop when hit piece or end of border
    //for attack: only include when hit piece
    public List<int[]> posMove(int[] coord, boolean attack) {
	Piece p = board[coord[0]][coord[1]];
	Movement m = p.getMovements();
        if (attack && p.getMovements() != p.getAttacks())  //movements and attacks don't point to same
            m = p.getAttacks();
        
        List<int[]> posMoves = new ArrayList<int[]>();

        List<int[]> differences = new ArrayList<int[]>();
        //add horizVert differences
	if (m.isHorizVert()) {
            int[][] differenceHV = { {-1, 0}, {1, 0},   //horiz
                                     {0, -1}, {0, 1} }; //vert
            differences.addAll(Arrays.asList(differenceHV));
	}
	//add diags differences
	if (m.isDiags()) {
            int[][] differenceD = { {-1, -1}, {-1, 1},
                                    {1, -1}, {1, 1} };
            differences.addAll(Arrays.asList(differenceD));
	}
        //add all possible movements for horiz/vert/diags
        for (int[] diffxy : differences)
            posMove(coord, posMoves, diffxy[0], diffxy[1], attack);

        //add other movements
        for (int[] move : m.getOtherMov()) {
            int atX = coord[0] + move[0];
            int atY = coord[1] + move[1];
            checkAddPosMove(coord, posMoves, atX, atY, attack); //keep checking no matter return boolean
        }
        
	return posMoves;
    }
    //add possible movements given a dx and dy (for horiz/vert/diags)
    public void posMove(int[] coord, List<int[]> posMoves, int dx, int dy, boolean attack) {
	int atX = coord[0];
	int atY = coord[1];
	for (;;) {
	    atX += dx;
	    atY += dy;
	    if (!checkAddPosMove(coord, posMoves, atX, atY, attack)) //false, don't check anymore
                break;
	}
    }
    //try to add possible move given coordinates.
    //return false if out of border or there was piece there
    public boolean checkAddPosMove(int[] coord, List<int[]> posMoves, int x, int y, boolean attack) {
        if (x < 0 || x > 7 || y < 0 || y > 7)
            return false;
        
        Piece at = board[x][y];
        if (at != null) { // piece there
            if (attack) {
                Piece here = board[coord[0]][coord[1]];
                if (at.isWhite() != here.isWhite()) { //different colors
                    int[] atCoord = {x, y};
                    posMoves.add(atCoord);
                }
            }
            return false;
        }
        if (!attack) {
            int[] atCoord = {x, y};
            posMoves.add(atCoord);
        }
        return true;
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
