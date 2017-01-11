public class King extends Piece {
    public King(boolean init) {
	super(init);
    }
    public String toString() {
	if (white)
	    return "K";
	else
	    return "k";
    }
}
