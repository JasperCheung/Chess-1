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
                    System.out.println("Checkmated!");
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
        
            //check if valid coordinate
            if (!Utils.validCoordinate(coord)) {
                System.out.println("Invalid coordinates");
                continue;
            }
            int[] from = Utils.coordToInts(coord);

            //check there is own piece at coord
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
            if (!Utils.validCoordinate(move)) {
                System.out.println("Invalid coordinates of move");
                continue;
            }
            int[] to = Utils.coordToInts(move);
            
            //does move if legal, otherwise repeat
            if (isPosMove(from, to) && isLegalMove(from, to)) {
		if (isSpecialMove(from, to))
                    doSpecialMove(from, to);
                else
                    doMove(from, to);
                p.moved();
                break;
            } else {
                System.out.println("Invalid move");
            }
        }
    }
    public boolean isPosMove(int[] from, int[] to) {
        return Utils.contains(posMoves(from[0], from[1]), to);
    }
    /*
      assume possible move
      legal if not checked when moved there
      returns true if legal
    */
    public boolean isLegalMove(int[] from, int[] to) {
	boolean attack = Utils.contains(posMoves(from[0], from[1], true), to);
	
	//do move (if attack, then keep track of attacked piece)
	Piece pieceFrom = board[from[0]][from[1]];
	Piece killed = board[to[0]][to[1]];  //used only for attack
	doMove(from, to);

	//if own king not in check -> legal
        boolean legal = !inCheck(pieceFrom.isWhite());

	//undo move
	doMove(to, from);
	if (attack)
	    board[to[0]][to[1]] = killed;
        
	return legal;
    }
    public void doMove(int[] from, int[] to) {
        if (Utils.equals(from, to))
            return; //don't change anything
	board[to[0]][to[1]] = board[from[0]][from[1]];
	board[from[0]][from[1]] = null;
    }
    
    public boolean isSpecialMove(int[] from, int[] to) {
        List<int[]> specialMoves = new ArrayList<int[]>();
        int xCoord = from[0];
        int yCoord = from[1];
        addSpecialMoves(xCoord, yCoord, specialMoves, false);
        addSpecialMoves(xCoord, yCoord, specialMoves, true);
        return Utils.contains(specialMoves, to);
    }
    //assume is special move
    public void doSpecialMove(int[] from, int[] to) {
        Piece p = board[from[0]][from[1]];

        //pawn 2-square movement
        if (p instanceof Pawn) {
            doMove(from, to);
            return;
        }
        
        //castling
        if (p instanceof King) {
            //move king
            doMove(from, to);
            
            //find rook: go in direction of to
            int xRook = -1;
            int dx = 1;
            if (to[0] < from[0]) {  //king goes left: rook goes right
                dx = -1;
            }
            for (int atX = from[0]; atX > -1 && atX < 8; atX += dx) {
                Piece rook = board[atX][from[1]];
                if (rook == null || !(rook instanceof Rook))
                    continue;
                xRook = atX;
                break;
            }
            
            int[] fromRook = {xRook, from[1]};
            int[] toRook = {to[0] - dx, from[1]};
            
            //move rook
            Piece rook = board[fromRook[0]][fromRook[1]];
            rook.moved();
            doMove(fromRook, toRook);
        }
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
        List<int[]> posMoves = posMoves(xCoord, yCoord);
        return selectLegalMoves(xCoord, yCoord, posMoves);
    }
    public List<int[]> selectLegalMoves(int xCoord, int yCoord, List<int[]> posMoves) {
	List<int[]> legalMoves = new ArrayList<int[]>();
        int[] from = {xCoord, yCoord};
        
        //go through all possible moves and add if they are legal
        for (int[] to : posMoves) {
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
      for movement: only include when moving to empty space
      for attack: only include when hit piece
      returns list of absolute coordinates
    */
    public List<int[]> posMoves(int xCoord, int yCoord, boolean attack) {
	Piece p = board[xCoord][yCoord];
	Movement m = p.getMovements();
        if (attack && p.getMovements() != p.getAttacks())  //movements and attacks don't point to same
            m = p.getAttacks();
        
        List<int[]> posMoves = new ArrayList<int[]>();

        //go through horiz/vert/diags by using differences
        //keep going w/ differences until piece or border reached
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
        //add all possible movements for differences
        for (int[] diffxy : differences)
            posMoves(xCoord, yCoord, posMoves, diffxy[0], diffxy[1], attack);

        //add other movements
        for (int[] move : m.getOtherMov()) {
            int atX = xCoord + move[0];
            int atY = yCoord + move[1];
            if (!p.isWhite()) {  //for black, consider movements flipped
                atX = xCoord - move[0];
                atY = yCoord - move[1];
            }
            checkAddPosMoves(xCoord, yCoord, posMoves, atX, atY, attack); //keep checking no matter return boolean
        }

        //add special moves
        addSpecialMoves(xCoord, yCoord, posMoves, attack);
        
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
        
        Piece to = board[x][y];
        if (to != null) { // piece there
            if (attack) {
                Piece from = board[xCoord][yCoord];
                if (to.isWhite() != from.isWhite()) { //different colors
                    int[] toCoord = {x, y};
                    posMoves.add(toCoord);
                }
            }
            return false;
        }
        if (!attack) {
            int[] toCoord = {x, y};
            posMoves.add(toCoord);
        }
        return true;
    }

    public void addSpecialMoves(int xCoord, int yCoord, List<int[]> posMoves, boolean attack) {
        Piece p = board[xCoord][yCoord];
        
        //pawn 2-square movement
        if (!attack && p instanceof Pawn && !p.isMoved()) {
            int dy = 2;
            if (!p.isWhite())
                dy = -2;
            checkAddPosMoves(xCoord, yCoord, posMoves, xCoord, yCoord + dy, attack);
        }

        //castling
	addCastling(xCoord, yCoord, posMoves, attack);
    }
    private void addCastling(int xCoord, int yCoord, List<int[]> posMoves, boolean attack) {
	Piece p = board[xCoord][yCoord];
	
	//castling (only with king coord)
	if (attack || !(p instanceof King) || p.isMoved())
	    return;

        addCastling(xCoord, yCoord, posMoves, p, 2);
        addCastling(xCoord, yCoord, posMoves, p, 6);
    }
    //with the end spot for the king, add the castling if possible
    private void addCastling(int xCoord, int yCoord, List<int[]> posMoves, Piece p, int kingEnd) {
        boolean kingGoesLeft = kingEnd < xCoord;
        
	//all spots from king to end spot are empty (exclude king)
        if (!isEmptyBetween(kingEnd, yCoord, xCoord))
            return;

	//get rook
        int xRook = -1;
        int dx = 1;
        if (kingGoesLeft) {
            dx = -1;
        }
        //go from king to left or right to find rook
        for (int atX = xCoord; atX > -1 && atX < 8; atX += dx) {
            Piece rook = board[atX][yCoord];
            if (rook == null || !(rook instanceof Rook))
                continue;
            xRook = atX;
            break;
        }
        if (xRook == -1) //not found
            return;
        
	Piece rook = board[xRook][yCoord];
	if (rook.isMoved())
	    return;
        
	//all spots from rook to end spot are empty (exclude rook)
        if (!isEmptyBetween(kingEnd, yCoord, xRook))
            return;

	//king can't be in check for any square while moving to end spot (including beginning)
        //have start be less than end
        int start = xCoord;
        int end = kingEnd;
        if (kingGoesLeft) {
            int temp = start;
            start = end;
            end = temp;
        }
	for (int atX = start; atX <= end; atX += 1) {
	    //do move, check, and undo
	    int[] from = {xCoord, yCoord};
	    int[] to = {atX, yCoord};
	    doMove(from, to);
	    boolean check = inCheck(p.isWhite());
	    doMove(to, from);
	    if (check)
		return;
	}
	
	checkAddPosMoves(xCoord, yCoord, posMoves, kingEnd, yCoord, false);
    }
    private boolean isEmptyBetween(int xStart, int yCoord, int xEnd) {
        //include xStart, exclude xEnd
        //have xStart be less than xEnd
        if (xEnd < xStart) {
            int temp = xStart;
            xStart = xEnd + 1;
            xEnd = temp + 1;
        }
        for (int atX = xStart; atX < xEnd; atX++) {
	    Piece at = board[atX][yCoord];
	    if (at != null)
		return false;
	}

        return true;
    }
    
    public void printBoard() {
        Utils.printBoard(board, 3, 5);
    }
}
