public class Pawn extends Piece {
    public Pawn(boolean init) {
	super(init);
        name = "p";
        createAttacks();
    }

    public Movement createMovements() {
        int[][] OM = { {0, 1} };
        Movement movement = new Movement(OM);
        return movement;
    }
    private void createAttacks() {
        int[][] OM = { {-1, 1},
                       {1, 1} };
        attacks = new Movement(OM);
    }
}
