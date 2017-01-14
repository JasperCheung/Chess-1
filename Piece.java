public abstract class Piece {
    protected Movement movements;
    protected Movement attacks;
    protected boolean white;

    public Piece(boolean init) {
	white = init;
    }

    public Movement getMovements() {
        return movements;
    }
    public Movement getAttacks() {
        return attacks;
    }

    //every piece should at least have another method to create movements
    protected abstract void createMovements();
}
