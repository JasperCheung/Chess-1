public class Queen extends Piece {
    public Queen(boolean init) {
	super(init);
        createMovements();
        attacks = movements;
    }

    protected void createMovements() {
        int[][] OM = { {-1, -1},
                       {-1, 1},
                       {1, -1},
                       {1, 1}};

        movements = new Movement(OM, true, true);
    }
    
    public String toString() {
	if (white)
	    return "Q";
	else
	    return "q";
    }
}
