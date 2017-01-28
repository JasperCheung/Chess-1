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
        
        System.out.println("Type anything to begin:");
        Keyboard.readString();
        System.out.print("\n");
        Chess c = new Chess();
        c.play();
    }
}
