package service;

import model.Passenger;
import model.StandardBirthCount;
import java.util.*;
import static service.FormatWriter.*;

public class RailwayReservationSystem {
    // Total Births runtime counters
    private int LOWER_BOOKED = 0;
    private int MIDDLE_BOOKED = 0;
    private int UPPER_BOOKED = 0;
    private int SIDE_UPPER_BOOKED = 0;

    // runtime dbs
    private final List<Passenger> confirmed = new ArrayList<>();
    private final List<Passenger> rac = new ArrayList<>();
    private final List<Passenger> waiting = new ArrayList<>();
    private final List<Passenger> children = new ArrayList<>();

    // ticket map for easy access
    private final Map<String, Passenger> ticketMap = new HashMap<>();
    private final Map<String, Passenger> History = new HashMap<>();

    public void startSystem() {
        printHeader();
        printMenu();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                int choice = getIntInput(sc, "Enter your choice: ");
                switch (choice) {
                    case 1 -> bookTicket(sc);
                    case 2 -> cancelTicket(sc);
                    case 3 -> printAllTickets();
                    case 4 -> printAvailableBerths();
                    case 5 -> {
                        printFooter();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
                printMenu();
            }
        }
    }

    // Integer for Age input helper - Other valiations can be done similarly
    private int getIntInput(Scanner sc, String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        return sc.nextInt();
    }

    // --- Booking Logic ---
    private void bookTicket(Scanner sc) {
        // get passenger details
        Passenger p = getPassengerDetails(sc);

        // reserve ticket for passenger based on availability
        reserveTicket(p);

        // if no berth allocated, booking failed
        if (p.getAllottedBerth() == null) {
            System.out.println("\n\nNo Berths Available. Ticket Booking Failed.\n");
            return;
        }

        // store tickets in map with unique ID
        ticketMap.put(p.getTicketID(), p);
        System.out.println("\n\nTicket Booked Successfully!\n" + p);
        System.out.println("Ticket ID: " + p.getTicketID());
        System.out.println("Berth Alloted: " + p.getAllottedBerth() + "\n");
    }

    // --- Passenger Details Input --- and Returns updated passenger
    private Passenger getPassengerDetails(Scanner sc) {
        sc.nextLine(); // flush
        Passenger p = new Passenger();

        System.out.print("Enter Name: ");
        p.setName(sc.nextLine().trim());

        p.setAge(getIntInput(sc, "Enter Age: "));
        sc.nextLine();

        System.out.print("Enter Gender (M/F/O): ");
        p.setGender(sc.next().trim().toUpperCase());

        if (p.getGender().equals("F") && p.getAge() > 18) {
            System.out.print("Traveling with Child? (Y/N): ");
            p.setWithChild(sc.next().trim().equalsIgnoreCase("Y"));
        }

        System.out.print("Enter Berth Preference (L/M/U/SU): ");
        p.setBerthPreference(sc.next().trim().toUpperCase());

        return p;
    }

    // --- Ticket Reservation Logic ---
    private void reserveTicket(Passenger p) {

         if (p.getAge() < 5) {
            p.setAllottedBerth("Child");
            children.add(p);
            return;
        }
        if (isAvailable(StandardBirthCount.BIRTH)) {
            if (allocateConfirmedBerth(p))
                confirmed.add(p);
        } else if (isAvailable(StandardBirthCount.R)) {
            p.setAllottedBerth("RAC");
            rac.add(p);
        } else if (isAvailable(StandardBirthCount.W)) {
            p.setAllottedBerth("WL");
            waiting.add(p);
        }
       
    }

    // --- Confirmed Berth Allocation Logic With Priority ---
    // --- Returns true if allocated ---
    // Allocates based on age and preference
    private boolean allocateConfirmedBerth(Passenger p) {
        List<String> order = new ArrayList<>();
        if (p.getAge() > 60 || p.isWithChild())
            order = Arrays.asList("L", "M", "SU", "U");
        else {
            switch (p.getBerthPreference()) {
                case "L" -> order = Arrays.asList("L", "M", "U", "SU");
                case "M" -> order = Arrays.asList("M", "U", "L", "SU");
                case "U" -> order = Arrays.asList("U", "L", "M", "SU");
                case "SU" -> order = Arrays.asList("SU", "U", "L", "M");
                default -> {
                    System.out.println("Invalid preference");
                    return false;
                }
            }
        }
        for (String b : order)
            if (tryAllocate(b, p))
                return true;
        System.out.println("No Confirmed Berths Available.");
        return false;
    }


    // Try allocating specific berth type, if available
    // Returns false if not allocated, true if allocated. No berth allocated if false.
    private boolean tryAllocate(String berth, Passenger p) {
        switch (berth) {
            case "L" -> {
                if (LOWER_BOOKED < StandardBirthCount.L.getNumberOfBirths()) {
                    LOWER_BOOKED++;
                    p.setAllottedBerth("L");
                    return true;
                }
            }
            case "M" -> {
                if (MIDDLE_BOOKED < StandardBirthCount.M.getNumberOfBirths()) {
                    MIDDLE_BOOKED++;
                    p.setAllottedBerth("M");
                    return true;
                }
            }
            case "U" -> {
                if (UPPER_BOOKED < StandardBirthCount.U.getNumberOfBirths()) {
                    UPPER_BOOKED++;
                    p.setAllottedBerth("U");
                    return true;
                }
            }
            case "SU" -> {
                if (SIDE_UPPER_BOOKED < StandardBirthCount.SU.getNumberOfBirths()) {
                    SIDE_UPPER_BOOKED++;
                    p.setAllottedBerth("SU");
                    return true;
                }
            }
        }
        return false;
    }

    // --- Cancel Ticket ---
    private void cancelTicket(Scanner sc) {
        if(ticketMap.isEmpty()) {
            System.out.println("\nNo Tickets Booked Yet.\n");
            return;
        }
        System.out.println();
        System.out.print("Enter Ticket ID to Cancel: ");
        String id = sc.next();
        Passenger p = ticketMap.get(id);
        if (p == null) {
            System.out.println("Invalid Ticket ID.\n");
            return;
        }
        removeFromList(p);
        adjustBerthCounts(p);
        upgradePassengers(p);
        History.put(p.getTicketID(),ticketMap.remove(id));
        System.out.println("Ticket Cancelled Successfully!\n");
    }

    // --- Utility Methods ---
    // Check if berth type is available during runtime.
    // Generic method for all types.
    // Database integration can be done here.
    private boolean isAvailable(StandardBirthCount type) {
        return switch (type) {
            case BIRTH -> confirmed.size() < type.getNumberOfBirths();
            case R -> rac.size() < type.getNumberOfBirths();
            case W -> waiting.size() < type.getNumberOfBirths();
            default -> false;
        };
    }

    // Very generic. Could be optimized with better data structures.
    // Remove passenger from respective list on cancellation.
    private void removeFromList(Passenger p) {
        switch (p.getAllottedBerth()) {
            case "L","M","U","SU" -> confirmed.remove(p);
            case "RAC" -> rac.remove(p);
            case "WL" -> waiting.remove(p);
            case "Child" -> children.remove(p);
        }
    }

    // Adjust berth counts on cancellation of confirmed tickets
    private void adjustBerthCounts(Passenger p) {
        switch (p.getAllottedBerth()) {
            case "L" -> LOWER_BOOKED--;
            case "M" -> MIDDLE_BOOKED--;
            case "U" -> UPPER_BOOKED--;
            case "SU" -> SIDE_UPPER_BOOKED--;
        }
    }

    // Upgrade passengers from RAC to Confirmed and from Waiting to RAC
    private void upgradePassengers(Passenger p) {

        // If RAC passenger cancelled, and if waiting list has passenger upgrade from Waiting to RAC
        if(p.getAllottedBerth().equals("RAC") && !waiting.isEmpty()) {
            Passenger wlPassenger = waiting.remove(0);
            wlPassenger.setAllottedBerth("RAC");
            rac.add(wlPassenger);
            //System.out.println("\nUpgraded from Waiting List to RAC: " + wlPassenger.getTicketID());
        }
        // If Confirmed passenger cancelled, upgrade from RAC to Confirmed if present
        if(p.getAllottedBerth().matches("L|M|U|SU") && !rac.isEmpty()) {
            Passenger racPassenger = rac.remove(0);
            allocateConfirmedBerth(racPassenger);
            confirmed.add(racPassenger);
            //System.out.println("\nUpgraded from RAC to Confirmed: " + racPassenger.getTicketID());
            
            // After upgrading RAC to Confirmed, check if waiting list has passenger to upgrade to RAC
            if(!waiting.isEmpty()) {
                Passenger wlPassenger = waiting.remove(0);
                wlPassenger.setAllottedBerth("RAC");
                rac.add(wlPassenger);
                //System.out.println("\nUpgraded from Waiting List to RAC: " + wlPassenger.getTicketID());
            }
        }
    }

    // Other print methods and Helpers
    
    //--- Print All Tickets ---

    private void printAllTickets() {
        System.out.println("\nAll Passengers:");
        printList("\nConfirmed", confirmed);
        printList("\nRAC", rac);
        printList("\nWaiting", waiting);
        printList("\nChild", children);
    }

    // --- Print Available Berths ---

    private void printAvailableBerths() {
        System.out.println("\nAvailable Berths:\n");
        System.out.println("Lower: " + (StandardBirthCount.L.getNumberOfBirths() - LOWER_BOOKED));
        System.out.println("Middle: " + (StandardBirthCount.M.getNumberOfBirths() - MIDDLE_BOOKED));
        System.out.println("Upper: " + (StandardBirthCount.U.getNumberOfBirths() - UPPER_BOOKED));
        System.out.println("Side Upper: " + (StandardBirthCount.SU.getNumberOfBirths() - SIDE_UPPER_BOOKED));
        System.out.println("RAC: " + (StandardBirthCount.R.getNumberOfBirths() - rac.size()));
        System.out.println("Waiting List: " + (StandardBirthCount.W.getNumberOfBirths() - waiting.size()));
    }

    // Helper to print list of passengers

    private void printList(String title, List<Passenger> list) {
        System.out.println("\n--- " + title + " ---");
        if (list.isEmpty())
            System.out.println("None");
        else
            list.forEach(System.out::println);
    }

}
