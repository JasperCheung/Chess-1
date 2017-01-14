public class King extends Piece {
    public King(boolean init) {
	super(init);
        createMovements();
        attacks = movements;
    }
    
    protected void createMovements() {
        int[][] OM = new int[8][2];

        int index = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                //exclude own cell
                if (i == 0 && j == 0)
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
	    return "K";
	else
	    return "k";
    }
}
