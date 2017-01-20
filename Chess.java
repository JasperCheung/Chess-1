import java.util.*;
import cs1.Keyboard;

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
    
    public void play() {
	// note: the for loop doesn't have a conditional
        //keep changing players until checkmated
        //also announce when player is checked (and checkmated)
        for (boolean color = true; ; color = !color) {
            printBoard();
            if (inCheck(color)) {
                System.out.println("In check!");
                if (noLegalMoves(color)) {
                    System.out.println("Is checkmated!");
                    break;
                }
            }
            
            String s;
            if (color)
                s = "White";
            else
                s = "Black";
            System.out.println(s + "'s turn:");
            turn(color);
            System.out.println();
        }
    }
    //complete a turn for a player
    public void turn(boolean color) {
        for (;;) {
            //get coordinate input from user
            System.out.print("Enter coordinate of piece:\t");
            String coord = Keyboard.readString();
        
            //check if valid coordinate && there is own piece at coord
            if (coord.length() != 2 || !Utils.validCoordinate(coord)) {
                System.out.println("Invalid coordinates");
                continue;
            }
            int[] from = Utils.coordToInts(coord);
            
            Piece p = board[from[0]][from[1]];
            if (p == null || p.isWhite() != color) {
                System.out.println("Invalid piece at coordinate");
                continue;
            }
            System.out.print(p + " at " + coord.substring(0, 1) + coord.substring(1, 2));
            
            //get input again for move
            System.out.print("\nEnter move:\t");
            String move = Keyboard.readString();

            //check if valid coordinate
            if (move.length() != 2 || !Utils.validCoordinate(move)) {
                System.out.println("Invalid coordinates of move");
                continue;
            }
            int[] to = Utils.coordToInts(move);
            
            //does move if legal, otherwise repeat
            if (isLegalMove(from, to)) {
                doMove(from, to);
                break;
            } else {
                System.out.println("Invalid move");
            }
        }
    }
    //returns true if legal
    //simulates the move (undoes after doing)
    public boolean isLegalMove(int[] from, int[] to) {
	//is possible move
	boolean movement = Utils.contains(posMoves(from[0], from[1], false), to);
	boolean attack = Utils.contains(posMoves(from[0], from[1], true), to);
	if (!movement && !attack)
	    return false;
	
	//and then do move (if attack, then keep track of attacked movement)
	Piece pieceFrom = board[from[0]][from[1]];
	Piece killed = board[to[0]][to[1]];  //used only for attack
	doMove(from, to);

	//if own king not in check -> legal
        boolean legal = !inCheck(pieceFrom.isWhite());

	//undo move
	if (movement)
	    doMove(to, from);
	else {
	    doMove(to, from);
	    board[to[0]][to[1]] = killed;
	}
        
	return legal;
    }
    public void doMove(int[] from, int[] to) {
	board[to[0]][to[1]] = board[from[0]][from[1]];
	board[from[0]][from[1]] = null;
    }
    
    public boolean inCheck(boolean color) {
        //get coordinate of king
        int[] kingCoord = new int[2];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece p = board[x][y];
                if (p == null || p.isWhite() != color)
                    continue;
                if (p instanceof King) {
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
                Piece p = board[x][y];
                if (p == null || p.isWhite() == color)
                    continue;
                if (Utils.contains(posMoves(x, y, true), (kingCoord)))
                    return true;
            }
        }
        
	return false;
    }
    
    //if checked and noLegalMoves -> checkmated
    //otherwise stalemate
    public boolean noLegalMoves(boolean color) {
        //go through all of own pieces
        //if any has legal moves, return false
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece p = board[x][y];
                if (p == null || p.isWhite() != color)
                    continue;
                if (legalMoves(x, y).size() > 0)
                    return false;
            }
        }

        return true;
    }
    
    //returns all legal moves at coordinate
    public List<int[]> legalMoves(int xCoord, int yCoord) {
        List<int[]> legalMoves = new ArrayList<int[]>();
        int[] from = {xCoord, yCoord};
        
        //go through all possible moves and add if they are legal
        for (int[] to : posMoves(xCoord, yCoord)) {
            if (isLegalMove(from, to)) {
                legalMoves.add(to);
            }
        }

        return legalMoves;
    }
    //get all possible moves (attack and movement)
    public List<int[]> posMoves(int xCoord, int yCoord) {
        List<int[]> posMoves = posMoves(xCoord, yCoord, true);
        posMoves.addAll(posMoves(xCoord, yCoord, false));
        return posMoves;
    }
    /*
      get possible move from coordinate (piece) (either movement or attack)
      for movement: stop when hit piece or end of border
      for attack: only include when hit piece
      returns list of absolute coordinates
    */
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
        Utils.printBoard(board, 3, 5);
    }
}
