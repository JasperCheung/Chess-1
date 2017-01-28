import cs1.Keyboard;
public class Woo {
    public static void main(String[] args) {
        //Start
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("       _____   _                        ");
        System.out.println("      / ____| | |                       ");
        System.out.println("     | |      | |__     ___   ___   ___ ");
        System.out.println("     | |      | '_ \\   / _ \\ / __| / __|");
        System.out.println("     | |____  | | | | |  __/ \\__ \\ \\__ \\ ");
        System.out.println("      \\_____| |_| |_|  \\___| |___/ |___/");
        System.out.println("\n\nAPCS1 Pd5");
        System.out.println("By Fabiha Ahmed, Kenny Chen, Jasper Cheung");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        
        Utils.printInstructions();
        
        String s = "What would you like to play?\n";
        s += "\t1: Normal chess\n";
        s += "\t2: Chess960";
        System.out.println(s);

        Chess c;
        int i = Keyboard.readInt();
        if (i == 2)
            c = new Chess960();
        else
            c = new Chess();
        System.out.println();
        c.play();
    }
}
