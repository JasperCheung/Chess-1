public class King extends Piece {
    public King(boolean init) {
	super(init);
        name = "k";
    }
    
    public Movement createMovements() {
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

        Movement movement = new Movement(OM);
        return movement;
    }
}
