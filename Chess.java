import java.util.*;
public class Chess {
    //board encoded as columns by rows (x by y)
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
	for (int x = 0; x < 8; x++) {
	    for (int y = 0; y < 8; y++) {
		setBoardPiece(x, y);
	    }
	}
    }
    // create 1 piece on board
    private void setBoardPiece(int x, int y) {
        if (y > 1 && y < 6)
            return;
        
	Piece p;
        
	if (y == 0 || y == 7) {
	    // white = true, black = false
	    boolean color = y == 0;
            
	    if (x == 0 || x == 7)
		p = new Rook(color);
	    else if (x == 1 || x == 6)
		p = new Knight(color);
	    else if (x == 2 || x == 5)
		p = new Bishop(color);
	    else if (x == 3)
		p = new Queen(color);
	    else // x == 4
		p = new King(color);
            
	} else { //y == 1 or 6
            boolean color = y == 1;
            p = new Pawn(color);
        }
        
        board[x][y] = p;
    }

    //complete a turn for a player
    public void turn(boolean color) {
	//get valid input from user: get coord
	//check if valid coordinate: there is own piece at coord?
	//gets input again for move
	//check if legal move
	//does move if legal
    }
    //does move and return true if legal
    //undoes move and return false if illegal;
    public boolean checkAddLegalMove(int[] from, int[] to) {
	//is possible move
	boolean movement = contains(posMoves(from[0], from[1], false), to);
	boolean attack = contains(posMoves(from[0], from[1], true), to);
	if (!movement && !attack)
	    return false;
	
	//and then do move (if attack, then keep track of attacked movement)

	Piece pieceFrom = board[from[0]][from[1]];
	Piece killed = board[to[0]][to[1]];  //used only for attack
	doMove(from, to);

	//if own king not in check -> legal
	if (!inCheck(pieceFrom.isWhite()))
	    return true;

	//undo move (illegal)
	if (movement)
	    doMove(to, from);
	else {
	    doMove(to, from);
	    board[to[0]][to[1]] = killed;
	}
	return false;
    }
    public boolean inCheck(boolean color) {
        //get coordinate of king
        int[] kingCoord = new int[2];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] == null)
                    continue;
                Piece p = board[x][y];
                if (p.isWhite() == color && p instanceof King) {
                    kingCoord[0] = x;
                    kingCoord[1] = y;
                    break;
                }
                
            }
        }

        //go through all pieces of enemy player
	//if they can attack friendly king -> in check
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] == null)
                    continue;
                Piece p = board[x][y];
                if (p.isWhite() != color && contains(posMoves(x, y, true), (kingCoord)))
                    return true;
            }
        }
        
	return false;
    }
    //checks if list contains el (same values) (array doesn't override .equals)
    //(maybe define Point...)
    public static boolean contains(List<int[]> list, int[] el) {
        for (int[] a : list) {
            if (equals(a, el))
                return true;
        }
        return false;
    }
    public static boolean equals(int[] a, int[] b) {
        if (a.length != b.length)
            return false;
        if (a.length == 0)
            return true;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i])
                return false;
        }
        return true;
    }
    
    public void doMove(int[] from, int[] to) {
	board[to[0]][to[1]] = board[from[0]][from[1]];
	board[from[0]][from[1]] = null;
    }
    
    //get possible move from coordinate (piece) (either movement or attack)
    //for movement: stop when hit piece or end of border
    //for attack: only include when hit piece
    //returns list of absolute coordinates
    public List<int[]> posMoves(int xCoord, int yCoord, boolean attack) {
	Piece p = board[xCoord][yCoord];
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
            posMoves(xCoord, yCoord, posMoves, diffxy[0], diffxy[1], attack);

        //add other movements
        for (int[] move : m.getOtherMov()) {
            int atX = xCoord + move[0];
            int atY = yCoord + move[1];
            checkAddPosMoves(xCoord, yCoord, posMoves, atX, atY, attack); //keep checking no matter return boolean
        }
        
	return posMoves;
    }
    //add possible movements given a dx and dy (for horiz/vert/diags)
    public void posMoves(int xCoord, int yCoord, List<int[]> posMoves, int dx, int dy, boolean attack) {
	int atX = xCoord;
	int atY = yCoord;
	for (;;) {
	    atX += dx;
	    atY += dy;
	    if (!checkAddPosMoves(xCoord, yCoord, posMoves, atX, atY, attack)) //false, don't check anymore
                break;
	}
    }
    //try to add possible move given coordinates.
    //return false if out of border or there was piece there
    public boolean checkAddPosMoves(int xCoord, int yCoord, List<int[]> posMoves, int x, int y, boolean attack) {
        if (x < 0 || x > 7 || y < 0 || y > 7)
            return false;
        
        Piece at = board[x][y];
        if (at != null) { // piece there
            if (attack) {
                Piece here = board[xCoord][yCoord];
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
	for (int y = 7; y > -1; y--) {
            
            //from each row print all the columns
	    String s = "";
	    for (int x = 0; x < 8; x++) {
		Piece p = board[x][y];
		if (p != null) {
		    s += p + " ";
		} else {
		    //put white boxes ■ for empty squares
		    //Box should appear if the terminal supports Unicode characters
		    if ((y + x) % 2 == 1) {
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
