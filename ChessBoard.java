public class ChessBoard {
    private char[][] printBoard;
    private int width; //width of one square
    private int length; //length of one square

    public ChessBoard() {
        this(3,5);
    }

    public ChessBoard(int l, int w){
	length = l;
	width = w;
	printBoard = new char[length*8][width*8];	
	populatePrintBoard();	
    }

    private void populatePrintBoard(){
	boolean clr = true;
	int cctr = 0;
	int rctr = 0;
	for(int r = 0; r < length*8; r++){
	    for(int c = 0; c < width*8; c++){
		//if clr = true -> white space, else black space
		if(clr){
		    printBoard[r][c] = '-';
		}
		else{
		    printBoard[r][c] = ' ';
		}
		cctr++;
		if(cctr == width){
		    cctr = 0;
		    clr = !clr;
		}
	    }
	    rctr++;
	    if(rctr == length){
		rctr = 0;
		clr = !clr;
	    }
	}
    }

    public void addPieces(Piece[][] board) {
	int pRow;
	int pCol;
	for(int r = 0; r < 8; r++){
	    for(int c = 0; c < 8; c++){
		Piece p = board[r][c];
		pRow = r*length + length/2;
		pCol = c*width + width/2;
		if(p != null){
		    printBoard[pRow][pCol] = p.toString().charAt(0);
		    printBoard[pRow][pCol+1] = ' ';
		    printBoard[pRow][pCol-1] = ' ';
		}		
	    }
	}
	    
    }    

    public void printBoard() {
	//column labels
	String cols = "abcdefgh ";
	String border = "  ";
	String s = "  ";//letters
	int ctrLetter = 0;
	for(int i = 0; i < width * 8; i ++){
	    border += "_";//border
	    if((i % width) == (width / 2)){
		s += cols.substring(ctrLetter,ctrLetter+1);
		ctrLetter++;
	    }
	    else{
		s += " ";
	    }
	}
	System.out.println(border);
	//row labels + borders
	int row = 8;
	for (int r = 0; r < length*8; r++) {
	    if((r % length) == (length / 2)){
		System.out.print(row + "|");
		row--;
	    }
	    else{
		System.out.print(" |");
	    }
	    for (int c = 0; c < width*8; c++) {
		System.out.print(printBoard[r][c]);
	    }
	    System.out.println("|");
	}
	System.out.println(" |" + border.substring(2) + "|");
	System.out.println(s);
    }
    
}
