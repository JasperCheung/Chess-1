public class Pawn extends Piece{
    public Pawn(boolean init) {
	super(init);
    }
    public String toString() {
	if (white)
	    return "P";
	else
	    return "p";
    }
}
