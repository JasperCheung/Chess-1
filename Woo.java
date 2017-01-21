//for testing printing arrays
//import java.util.Arrays;
public class Woo {
    public static void main(String[] args) {
	//Start
        
	Chess c = new Chess();
        c.play();
        
        
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

        from[0] = 0;
        from[1] = 0;
        to[0] = 0;
        to[1] = 2;
        c.doMove(from, to);
        c.printBoard();
        */
        /* testing posMoves()
	int[] from = {3, 0};
	int[] to = {3, 3};
	c.doMove(from, to);
	c.printBoard();

	System.out.println(Arrays.deepToString(c.posMoves(to[0], to[1], false).toArray()));

        from[0] = 2;
        from[1] = 1;
        to[0] = 2;
        to[1] = 5;
        c.doMove(from, to);
	c.printBoard();

	System.out.println(Arrays.deepToString(c.posMoves(to[0], to[1], true).toArray()));

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

	from[0] = 3; from[1] = 5;
	to[0] = 4; to[1] = 6;
	System.out.println(c.isLegalMove(from, to));
	c.printBoard();
	*/
        /* testing noLegalMoves()
        //simulate almost fool's mate
        int[] from = {3, 7};
        int[] to = {7, 3};
        c.doMove(from, to);
        from[0] = 5; from[1] = 1;
        to[0] = 5; to[1] = 3;
        c.doMove(from, to);
        from[0] = 6; from[1] = 1;
        to[0] = 6; to[1] = 3;
        c.doMove(from, to);
        
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
	for (int i = 1; i < 4; i++) {
	    from[0] = i; from[1] = 0;
	    to[0] = i; to[1] = 1;
	    c.doMove(from, to);
	}
	c.printBoard();

	System.out.println(Arrays.deepToString(c.posMoves(4, 0).toArray()));
	c.printBoard();
	
	System.out.println(Arrays.deepToString(c.legalMoves(4, 0).toArray()));

        //testing isSpecialMove
        int[] from2 = {4, 0};
        int[] to2 = {2, 0};
        System.out.println(c.isSpecialMove(from2, to2));

        //testing doSpecialMove
        c.doSpecialMove(from2, to2);
        c.printBoard();
	*/
    }
}
