//for testing printing arrays
//import java.util.Arrays;
import cs1.Keyboard;

public class Woo {
    public static void main(String[] args) {
	//Start
	//ChessBoard che = new ChessBoard(5,7);
	Chess c = new Chess();
	//che.printBoard();//empty board
	//che.addPieces(c.getBoard());
	//che.printBoard(c.getBoard());
	c.printBoard();
	
	//play
	System.out.println("Please input cords xy: ");
	int[] from = new int[2];
	int[] to = new int[2];
	String cords =  Keyboard.readString();
	from[0] =  Character.getNumericValue(cords.charAt(0));
	from[1] = Character.getNumericValue(cords.charAt(1));
	to[0] = Character.getNumericValue(cords.charAt(2));
	to[1] = Character.getNumericValue(cords.charAt(3));

	c.doMove(from,to);
	c.printBoard();
	    
	
	    
    
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
        Chess c = new Chess();
        c.printBoard();

	int[] from = {1, 1};
	int[] to = {1, 2};
	c.doMove(from, to);
	c.printBoard();

        from[0] = 0;
        from[1] = 0;
        to[0] = 0;
        to[1] = 2;
        c.doMove(from, to);
        c.printBoard();
        */
        /* testing posMove()
	Chess c = new Chess();
	c.printBoard();
	int[] from = {3, 0};
	int[] to = {3, 3};
	c.doMove(from, to);
	c.printBoard();

	System.out.println(Arrays.deepToString(c.posMove(to[0], to[1], false).toArray()));

        from[0] = 2;
        from[1] = 1;
        to[0] = 2;
        to[1] = 5;
        c.doMove(from, to);
	c.printBoard();

	System.out.println(Arrays.deepToString(c.posMove(to[0], to[1], true).toArray()));
        */
        /* testing inCheck()
        Chess c = new Chess();
        c.printBoard();
        int[] from = {3, 1};
        int[] to = {3, 6};
        c.doMove(from, to);
        c.printBoard();

        System.out.println(Arrays.deepToString(c.posMove(3, 6, true).toArray()));
        System.out.println(c.inCheck(false));
        */
<<<<<<< HEAD

=======
        
	//Start
	ChessBoard che = new ChessBoard();
	Chess c = new Chess();
	che.printBoard(c.getBoard(),5,7);//empty board
	//che.addPieces(c.getBoard());
	//che.printBoard(c.getBoard());
	
	/*todo
	c.play();
	*/
>>>>>>> 1728f60b8ce52d21634842098882e317b5f3173d
        
    }
}
