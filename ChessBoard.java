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
	blankBoard();	
    }

    //creates a blank board
    private void blankBoard(){
	boolean clr = true;//color of square
	//column and row counters to check if white or black square
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
		//if column == square width, negate clr of square
		if(cctr == width){
		    cctr = 0;
		    clr = !clr;
		}
	    }
	    rctr++;
	    //if row == square length, negate clr of square
	    if(rctr == length){
		rctr = 0;
		clr = !clr;
	    }
	}
    }


    public void printBoard(Piece[][] board) {
	
	//pRow, pCol as coordinates of piece on printed board
	int pRow;
	int pCol;
	for(int r = 0; r < 8; r++){
	    for(int c = 0; c < 8; c++){
		Piece p = board[r][c];
		//shift to middle of square and skip appropriate number of spaces
		pRow = r*length + length/2;
		pCol = c*width + width/2;
		if(p != null){
		    printBoard[pRow][pCol] = p.toString().charAt(0);
		    //leaves a gap 
		    printBoard[pRow][pCol+1] = ' ';
		    printBoard[pRow][pCol-1] = ' ';
		}		
	    }
	}	

	//loops through printBoard to print out all of its elements, as well as adding border + row labels
	int rowNum = 8;
	for (int r = 0; r < printBoard.length; r++) {
	    //row labels + borders
	    if((r % length) == (length / 2)){
		System.out.print(rowNum + "|");
		rowNum--;
	    }
	    else{
		System.out.print(" |");
	    }

	    for (int c = 0; c < printBoard[r].length; c++) {
		System.out.print(printBoard[r][c]);
	    }
	    System.out.println();
	}
	
	System.out.print(" |");
	for(int i = 0; i < width * 8; i ++){
	    System.out.print("_");
	}

	//column labels
	String cols = "abcdefgh ";
	int ctrLetter = 0;	
	System.out.print("\n  ");
	for(int i = 0; i < width * 8; i ++){	   
	    if((i % width) == (width / 2)){
	        System.out.print( cols.substring(ctrLetter,ctrLetter+1));
		ctrLetter++;
	    }
	    else{
		System.out.print(" ");
	    }
	}
	System.out.println();
    }
    
}
