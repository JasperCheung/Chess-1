public class Rook extends Piece {
    public Rook(boolean init) {
        super(init);
        name = "r";
    }

    protected void createMovements() {
        movements = new Movement(null, true, false);
    }
}
