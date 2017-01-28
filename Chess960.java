public class Chess960 extends Chess {
    protected void populateBoard() {
        genPosition();

        super.populateBoard();
    }

    //generate random starting position
    //consult chess960 numbering scheme
    private String[] genPosition() {
        position = new String[8];
        
        int num = (int) (Math.random() * 960);

        int b1 = num % 4;
        position[b1 * 2] = "b";
        num /= 4;

        int b2 = num % 4;
        position[b2 * 2 + 1] = "b";
        num /= 4;

        int q = num % 6;
        Utils.place(position, "q", q);
        num /= 6;

        int n1 = 0;
        int n2 = 0;

        if (num < 4) {
            n1 = 0;
            n2 = num;
        } else if (num < 7) {
            n1 = 1;
            n2 = num - 3;
        } else if (num < 9) {
            n1 = 2;
            n2 = num - 5;
        } else {
            n1 = 3;
            n2 = 3;
        }
        Utils.place(position, "n", n1);
        Utils.place(position, "n", n2);

        Utils.place(position, "r", 0);
        Utils.place(position, "k", 0);
        Utils.place(position, "r", 0);

        return position;
    }
}
