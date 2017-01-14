public class Knight extends Piece {
    public Knight(boolean init) {
	super(init);
        createMovements();
        attacks = movements;
    }

    protected void createMovements() {
        int[][] OM = new int[8][2];

        int index = 0;
        for (int i = -3; i < 4; i++) {
            for (int j = -3; j < 4; j++) {
                if (Math.abs(i) + Math.abs(j) != 3)
                    continue;
                if (i == 0 || j == 0)
                    continue;
                int[] p = {i, j};
                OM[index] = p;
                index++;
            }
        }

        movements = new Movement(OM);
    }
    
    public String toString() {
	if (white)
	    return "N";
	else
	    return "n";
    }
}
