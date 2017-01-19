public class Utils {

    //prints board
    public static void printBoard(Piece[][] board, int length, int width) {
	String clr = " ";//white or black square
	String label = "abcdefgh";
	
	//loop through board rows
	for(int i = 7; i > -1; i--){
	    
	    //loop through square rows
	    for(int r = 0; r < length; r++){
		//labeling the rows of the board
		//if on the middle row of square, print the label and border. Else print a space and the border
		if(r == length/2) System.out.print(label.charAt(i) + "|");
		else System.out.print(" |");

		//loop through board columns
		for(int j = 0; j < 8; j++){
		    if((i+j) % 2 == 0) clr = " ";//black square
		    else clr = "-";//white square

		    //check if there is a piece on the square
		    Piece p = board[j][i];
		    
		    if(p == null){//if there is no piece, prints " " or "-" based on color of piece			
			//loops with variable c are looping through the square's columns
			for(int c = 0; c < width; c++){
			    System.out.print(clr);
			}
		    }
		    else{
			//if there is a piece, prints piece in the center of the square and leaves a gap next to it
			//prints color in rest of square
			for(int c = 0; c < width; c++){
			    if(r == length/2){
				int center = width/2;
				if(c == center-1 || c == center+1) System.out.print(" ");
				else if(c == center) System.out.print(p.toString());
				else System.out.print(clr);
			    }
			    else System.out.print(clr);
			}
		    }
		}//end of board columns
		System.out.println();
	    }//end of square rows
	}//end of board rows

	//Bottom border |_______
	System.out.print(" |");
	for(int i = 0; i < width * 8; i++){
	    System.out.print("_");
	}

	//Labeling the columns
	System.out.print("\n  ");
	for(int j = 0; j < 8; j++){
	    for(int c = 0; c < width; c++){
		if(c == width/2) System.out.print(j+1);
		else System.out.print(" ");
	    }
	}
    }

}
