public class Pawn extends Piece {
    public Pawn(boolean init) {
	super(init);
        createMovements();
        createAttacks();
    }

    protected void createMovements() {
        int[][] OM = { {0, 1} };
        movements = new Movement(OM);
    }
    private void createAttacks() {
        int[][] OM = { {-1, 1},
                       {1, 1}};
        attacks = new Movement(OM);
    }
    
    public String toString() {
	if (white)
	    return "P";
	else
	    return "p";
    }
}
