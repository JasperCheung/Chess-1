public abstract class Piece {
    protected int[][] movements;
    protected int[][] attacks;
    protected boolean white;

    public Piece(boolean init) {
	white = init;
    }

    public String toString() {
	return "x";
    }
}
