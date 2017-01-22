//for testing printing arrays
//import java.util.Arrays;
import cs1.Keyboard;
public class Woo {
    public static void main(String[] args) {
	//Start
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("       _____   _                        ");
	System.out.println("      / ____| | |                       ");
	System.out.println("     | |      | |__     ___   ___   ___ ");
	System.out.println("     | |      | '_ \\   / _ \\ / __| / __|");
	System.out.println("     | |____  | | | | |  __/ \\__ \\ \\__ \\ ");
	System.out.println("      \\_____| |_| |_|  \\___| |___/ |___/");
	System.out.println("\n\nAPCS1 Pd5");
	System.out.println("By Fabiha Ahmed, Kenny Chen, Jasper Cheung");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("\n\nHow to Play...");
	System.out.println("The game will start with White making the first move.");
	System.out.println("Capital letters are white pieces");
	System.out.println("To move input the cordinates of the piece you want to move(ex. a1).");
	System.out.println("Then input the coords of the location (letternumber)");
	System.out.println("Castling works by moving your king to the cords of the rook");
	System.out.println("You can also type a command instead of cord, type h, ? or help for a list");
	System.out.println("Type play to begin: ");
	String x = Keyboard.readString();
	if (x.equals("play")){
	    System.out.print("\u001b[2J\u001b[H");
	    Chess c = new Chess();
	    c.play();}
        
        
        /* testing toString
	Queen q = new Queen(true);
	System.out.println(q);
        */
        /* testing Movement in pieces
        King k = new King(true);
        System.out.println(Arrays.deepToString(k.getMovements().getOtherMov()));
        System.out.println(Arrays.deepToString(k.getAttacks().getOtherMov()));
        
        Queen q = new Queen(true);
        System.out.println(Arrays.deepToString(q.getMovements().getOtherMov()));

        Bishop b = new Bishop(true);
        System.out.println(Arrays.deepToString(b.getMovements().getOtherMov()));

        Knight n = new Knight(true);
        System.out.println(Arrays.deepToString(n.getMovements().getOtherMov()));
        System.out.println(Arrays.deepToString(n.getAttacks().getOtherMov()));
        
        Rook r = new Rook(true);
        System.out.println(Arrays.deepToString(r.getMovements().getOtherMov()));

        Pawn p = new Pawn(true);
        System.out.println(Arrays.deepToString(p.getMovements().getOtherMov()));
        System.out.println(Arrays.deepToString(p.getAttacks().getOtherMov()));
        */
        /* testing doMove
	int[] from = {1, 1};
	int[] to = {1, 2};
	c.doMove(from, to);
	c.printBoard();

        int[] from2 = {0, 0};
        int[] to2 = {0, 2};
        c.doMove(from2, to2);
        c.printBoard();
        */
        /* testing posMoves()
	int[] from = {3, 0};
	int[] to = {3, 3};
	c.doMove(from, to);
	c.printBoard();

	System.out.println(Arrays.deepToString(c.posMoves(to[0], to[1], false).toArray()));

        int[] from2 = {2, 1};
        int[] to2 = {2, 5};
        c.doMove(from2, to2);
        c.printBoard();
        
	System.out.println(Arrays.deepToString(c.posMoves(to2[0], to2[1], true).toArray()));

        System.out.println(Arrays.deepToString(c.posMoves(1, 1, false).toArray()));
        System.out.println(Arrays.deepToString(c.posMoves(1, 1, true).toArray()));
        */
        /* testing inCheck()
        int[] from = {3, 1};
        int[] to = {3, 6};
        c.doMove(from, to);
        c.printBoard();

        System.out.println(Arrays.deepToString(c.posMoves(3, 6, true).toArray()));
        System.out.println(c.inCheck(false));

        //testing legalMoves()
        System.out.println(Arrays.deepToString(c.legalMoves(3, 7).toArray()));

        //testing noLegalMove()
        System.out.println(c.noLegalMoves(false));
        */
	/* testing islegalMove()
	int[] from = {3, 1};
	int[] to = {3, 5};
	c.doMove(from, to);
	c.printBoard();

        int[] from2 = {3, 5};
        int[] to2 = {4, 6};
	System.out.println(c.isLegalMove(from2, to2));
	c.printBoard();
	*/
        /* testing noLegalMoves()
        //simulate almost fool's mate
        int[] from = {3, 7};
        int[] to = {7, 3};
        c.doMove(from, to);

        int[] from2 = {5, 1};
        int[] to2 = {5, 3};
        c.doMove(from2, to2);

        int[] from3 = {6, 1};
        int[] to3 = {6, 3};
        c.doMove(from3, to3);
        
        c.printBoard();
        System.out.println(c.noLegalMoves(true));
        */
	/* test turn()
        c.turn(true);
        c.printBoard();
        */
        /* testing addCastling
        System.out.println(Arrays.deepToString(c.posMoves(4, 0).toArray()));
        
	int[] from = new int[2];
	int[] to = new int[2];
	for (int i = 1; i < 7; i++) {
            if (i == 4)
                continue;
	    from[0] = i; from[1] = 0;
	    to[0] = i; to[1] = 1;
	    c.doMove(from, to);
	}
	c.printBoard();

	System.out.println(Arrays.deepToString(c.posMoves(4, 0).toArray()));

	System.out.println(Arrays.deepToString(c.legalMoves(4, 0).toArray()));
        
        //testing isSpecialMove
        int[] from2 = {4, 0};
        int[] to2 = {0, 0};
        //int[] to2 = {7, 0};
        System.out.println(c.isSpecialMove(from2, to2));

        //testing doSpecialMove
        c.doSpecialMove(from2, to2);
        c.printBoard();
	*/
        /* testing pawn promotion
        int[] from = {0, 1};
        int[] to = {0, 5};
        c.doMove(from, to);
        c.printBoard();
        
	System.out.println(Arrays.deepToString(c.posMoves(0, 6).toArray()));
        System.out.println(Arrays.deepToString(c.legalMoves(0, 6).toArray()));
        c.printBoard();

        //have p moved
        c.turn(true);
        c.printBoard();

        int[] from2 = {1, 6};
        int[] to2 = {0, 7};
        System.out.println(c.isSpecialMove(from2, to2));
        //System.out.println(c.isPosMove(from2, to2));

        c.turn(true);
        c.printBoard();
        */
        /* test history and en passant
        int count = 0;
        for (boolean color = true; count < 4; color = !color) {
            c.printBoard();
            c.turn(color);
            count++;
        }
        c.printBoard();
        System.out.println(Arrays.deepToString(c.getHistory().toArray()));
        System.out.println(Arrays.deepToString(c.legalMoves(3, 4).toArray()));
        
        c.turn(true);
        c.printBoard();
        */
    }
}
