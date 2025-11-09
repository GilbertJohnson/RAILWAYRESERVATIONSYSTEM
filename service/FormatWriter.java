package service;

public class FormatWriter {
    public static void printHeader() {
        System.out.println("=======================================");
        System.out.println("   Welcome to the Railway Reservation  ");
        System.out.println("=======================================");
    }

    public static void printFooter() {
        System.out.println();
        System.out.println("=======================================");
        System.out.println();
        System.out.println("          Session Ended.              ");
        System.out.println("   Thank you for using our service!    ");
        System.out.println();
        System.out.println("=======================================");
        System.out.println();
    }

    public static void printMenu() {
        System.out.println("\n=======================================");
        System.out.println("\nMenu:");
        System.out.println("1. Book Ticket");
        System.out.println("2. Cancel Ticket");
        System.out.println("3. Print All Tickets");
        System.out.println("4. Print Available Berths");
        System.out.println("5. Exit");
        System.out.println();
        System.out.println("=======================================\n");
    }
}
