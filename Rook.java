public class Rook extends Piece {
    public Rook(boolean init) {
	super(init);
        createMovements();
        attacks = movements;
    }

    protected void createMovements() {
        movements = new Movement(null, true, false);
    }
    
    public String toString() {
	if (white)
	    return "R";
	else
	    return "r";
    }
}
