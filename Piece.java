public abstract class Piece {
    protected boolean white;
    protected String name;
    
    protected Movement movements;
    protected Movement attacks;

    public Piece(boolean init) {
	white = init;
        createMovements();
        // point attacks to movements
        // this applies to most Pieces (except Pawn)
        attacks = movements;
    }

    public Movement getMovements() {
        return movements;
    }
    public Movement getAttacks() {
        return attacks;
    }

    //every piece should at least have another method to create movements
    protected abstract void createMovements();

    public String toString() {
        if (white)
            return name.toUpperCase();
        return name.toLowerCase();
    }
}
