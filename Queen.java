public class Queen extends Piece {
    public Queen(boolean init) {
	super(init);
    }
    public String toString() {
	if (white)
	    return "Q";
	else
	    return "q";
    }
}
