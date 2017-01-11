public class Rook extends Piece {
    public Rook(boolean init) {
	super(init);
    }
    public String toString() {
	if (white)
	    return "R";
	else
	    return "r";
    }
}
