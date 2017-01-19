public class Movement {
    int[][] otherMov; //other movements not included in horiz/vert/diags

    //goes through entire line
    boolean horizVert;
    boolean diags; //both diagonals

    public Movement(int[][] initOM) {
        this(initOM, false, false);
    }
    public Movement(int[][] initOM, boolean hv, boolean d) {
        if (initOM == null)
            otherMov = new int[0][0];
        else
            otherMov = initOM;
        horizVert = hv;
        diags = d;
    }

    public int[][] getOtherMov() {
        return otherMov;
    }
    public boolean isHorizVert() {
        return horizVert;
    }
    public boolean isDiags() {
        return diags;
    }
}
