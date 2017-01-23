public class Bishop extends Piece {
    public Bishop(boolean init) {
        super(init);
        name = "b";
    }

    protected void createMovements() {
        movements = new Movement(null, false, true);
    }
}
