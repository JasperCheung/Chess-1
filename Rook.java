public class Rook extends Piece {
    public Rook(boolean init) {
	super(init);
        name = "r";
    }

    public Movement createMovements() {
        Movement movement = new Movement(null, true, false);
        return movement;
    }
}
