public class Queen extends Piece {
    public Queen(boolean init) {
        super(init);
        name = "q";
    }

    protected void createMovements() {
        movements = new Movement(null, true, true);
    }
}
