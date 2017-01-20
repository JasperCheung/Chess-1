public class Pawn extends Piece {
    public Pawn(boolean init) {
	super(init);
        name = "p";
        createAttacks();
    }

    protected void createMovements() {
        //different behavior depending on color
        if (white) {
            int[][] OM = { {0, 1} };
            movements = new Movement(OM);
        } else {
            int[][] OM = { {0, -1} };
            movements = new Movement(OM);
        }
            
        
    }
    private void createAttacks() {
        //different behavior depending on color
        if (white) {
            int[][] OM = { {-1, 1},
                           {1, 1} };
            attacks = new Movement(OM);
        } else {
            int[][] OM = { {-1, -1},
                           {1, -1} };
            attacks = new Movement(OM);
        }
    }
}
