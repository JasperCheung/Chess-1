public abstract class Piece {
    protected boolean white;
    protected String name;

    protected Movement movements;
    protected Movement attacks;

    //use in 2-square movement for pawns and castling
    protected boolean moved;
    
    public Piece(boolean init) {
        white = init;
        moved = false;
        createMovements();
        // point attacks to movements
        // this applies to most Pieces (except Pawn)
        attacks = movements;
    }
    
    public boolean isWhite() {
        return white;
    }
    public Movement getMovements() {
        return movements;
    }
    public Movement getAttacks() {
        return attacks;
    }
    public boolean isMoved() {
        return moved;
    }
    public void moved() {
        moved = true;
    }
    
    //every piece should at least have another method to create movements
    protected abstract void createMovements();

    public String toString() {
        if (white)
            return name.toUpperCase();
        return name.toLowerCase();
    }
}
