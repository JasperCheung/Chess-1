//for testing printing arrays
//import java.util.Arrays;
import cs1.Keyboard;
public class Woo {
    public static void main(String[] args) {
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
        
	//Start
	Chess c = new Chess();
	c.printBoard();
	
	/*todo
	play();
	*/
        
    }
}
