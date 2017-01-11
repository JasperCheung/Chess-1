public class Knight extends Piece{
    public Knight(boolean init) {
	super(init);
    }
    public String toString() {
	if (white)
	    return "N";
	else
	    return "n";
    }
}
