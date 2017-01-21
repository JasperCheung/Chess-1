public class Bishop extends Piece {
    public Bishop(boolean init) {
	super(init);
        name = "b";
    }

    public Movement createMovements() {
        Movement movement = new Movement(null, false, true);
        return movement;
    }
}
