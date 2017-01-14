public class Bishop extends Piece {
    public Bishop(boolean init) {
	super(init);
        createMovements();
        attacks = movements;
    }

    protected void createMovements() {
        movements = new Movement(null, false, true);
    }
    
    public String toString() {
	if (white)
	    return "B";
	else
	    return "b";
    }
}
