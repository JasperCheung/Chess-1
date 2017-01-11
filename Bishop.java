public class Bishop extends Piece {
    public Bishop(boolean init) {
	super(init);
    }
    public String toString() {
	if (white)
	    return "B";
	else
	    return "b";
    }
}
