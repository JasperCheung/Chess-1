public class Queen extends Piece {
    public Queen(boolean init) {
	super(init);
        name = "q";
    }

    public Movement createMovements() {
        Movement movement = new Movement(null, true, true);
        return movement;
    }
}
