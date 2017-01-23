public class Pawn extends Piece {
    public Pawn(boolean init) {
        super(init);
        name = "p";
        createAttacks();
    }

    protected void createMovements() {
        int[][] OM = { {0, 1} };
        movements = new Movement(OM);
    }
    private void createAttacks() {
        int[][] OM = { {-1, 1},
                       {1, 1} };
        attacks = new Movement(OM);
    }
}
