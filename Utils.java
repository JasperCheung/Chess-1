import java.util.*;
import cs1.Keyboard;

public class Utils {
    private static final String letters = "abcdefgh";
    private static final String numbers = "12345678";

    private static final String[] commands = { "history", "record", "r", "pieces", "p", "resign", "draw", "help", "h", "?", "instructions", "i", "quit", "q", "exit", "e" };

    //~~~~~Coordinates
    //return true if valid coordinate
    public static boolean validCoordinate(String coord) {
        if (coord.length() != 2)
            return false;
        
        String xStr = coord.substring(0, 1);
        String yStr = coord.substring(1, 2);
        
        return letters.contains(xStr) && numbers.contains(yStr);
    }
    
    //get coord, return corresponding int[]
    //assume coord is valid
    public static int[] coordToInts(String coord) {
        int[] ret = new int[2];
        ret[0] = stringToNum(coord.substring(0, 1));
        ret[1] = stringToNum(coord.substring(1, 2));
        return ret;
    }
    //assume s is valid
    public static int stringToNum(String s) {
        if (letters.contains(s))
            return letters.indexOf(s);
        return numbers.indexOf(s);
    }

    //get coord, return corresponding string
    //assume coord is valid
    public static String coordToString(int[] coord) {
        int letter = coord[0];
        int number = coord[1];
        return letters.substring(letter, letter + 1) + numbers.substring(number, number + 1);
    }

    //~~~~~Other X to Y
    public static String colorToString(boolean color) {
        if (color)
            return "White";
        else
            return "Black";
    }

    //assume valid s
    public static Piece stringToPiece(String s, boolean color) {
        switch (s.toLowerCase()) {
        case "r": return new Rook(color);
        case "n": return new Knight(color);
        case "b": return new Bishop(color);
        case "q": return new Queen(color);
        case "k": return new King(color);
        default: return null;
        }
    }
    
    //~~~~~~Commands
    //return true if valid command
    public static boolean validCommand(String command) {
        return Arrays.asList(commands).contains(command.toLowerCase());
    }
    
    public static void printPieces(List<Piece> blackPiecesTaken, List<Piece> whitePiecesTaken) {
        String s = "Black pieces taken: ";
        for (Piece p : blackPiecesTaken)
            s += p + " ";
        s += "\nWhite pieces taken: ";
        for (Piece p : whitePiecesTaken)
            s += p + " ";
        System.out.println(s);
    }
    
    //return true if resign, draw, or quit
    public static boolean confirmResign() {
        System.out.println("Resign? (y/n)");
        boolean res = queryYesNo();
        if (res)
            System.out.println("Resigned!");
        return res;
    }
    public static boolean confirmDraw() {
        System.out.println("Confirm draw with both players? (y/n)");
        boolean res = queryYesNo();
        if (res)
            System.out.println("Draw!");
        return res;
    }
    public static boolean confirmQuit() {
        System.out.println("Confirm quit? (y/n)");
        boolean res = queryYesNo();
        if (res)
            System.out.println("Quitting...");
        return res;
    }
    public static boolean queryYesNo() {
        String res = Keyboard.readString().toLowerCase();
        switch(res) {
        case "yes": case "y": return true;
        case "no": case "n": return false;
        default: return false;
        }
    }
    
    public static void printHelp() {
        String s = "help, h, ?: Shows all commands\n";
        s += "history, record, r: Shows all moves done\n";
        s += "pieces, p: Shows all pieces taken\n";
        s += "resign: Forfeit the game, opponent wins\n";
        s += "draw: Offer a draw between the players\n";
        s += "instructions, i: How to play the game\n";
        s += "quit, q, exit, e: Leaves the game\n";
        System.out.println(s);
    }

    public static void printInstructions() {
        String instructions = "\nHow to Play:\n";

        instructions +="\nThe game will start with White making the first move.";
        instructions +="\nCapital letters are white pieces, lowercase letters are black pieces.";
        instructions +="\nTo move, input the coordinates of the piece you want to move (eg. d4).";
        instructions +="\nThen input the coordinates of the location (eg. d6).";
        instructions +="\nCastling works by moving your king to the coordinates of the rook.";
        instructions +="\nYou can also type a command instead of moving; type h, ?, or help for a list of possible commands.\n";
        
        System.out.println(instructions);
        
    }

    //~~~~~contains and equals
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

    //~~~~~place
    //puts el at the numth free spot (marked by nulls) in arr
    public static void place(String[] arr, String el, int num) {
        int freeSpot = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                if (freeSpot == num) {
                    arr[i] = el;
                    break;
                }
                freeSpot++;
            }
        }
    }
    
    //~~~~~printBoard
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
                
                if(r == length/2){
                    System.out.print(i + 1 + "|");
                    
                }
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
                if(c == width/2) System.out.print(label.charAt(j));
                else System.out.print(" ");
            }
        }
        
        System.out.println();
    }
}
