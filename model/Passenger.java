package model;

import java.util.Random;
import java.util.UUID;


public class Passenger {
    String ticketID; // unique ticket identifier for each passenger - public use
    String name;
    String gender;
    String berthPreference;
    String allottedBerth;
    boolean withChild;
    int age;
    UUID uuid; // unique identifier for each passenger for internal use if required later.

    public Passenger() {
        this.uuid = UUID.randomUUID();
        this.ticketID = this.generateTicketID();
        this.name = "";
        this.gender = null;
        this.berthPreference = null;
        this.allottedBerth = null;
        this.withChild = false;
        this.age = 0;
    }
    private String generateTicketID() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder ticketID = new StringBuilder();
        Random rnd = new Random();
        while (ticketID.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            ticketID.append(chars.charAt(index));
        }
        return ticketID.toString();
    }

    // --- Getters and Setters ---
    public String getTicketID() { return ticketID; }
    public void setTicketID(String ticketID) { this.ticketID = ticketID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getBerthPreference() { return berthPreference; }
    public void setBerthPreference(String berthPreference) { this.berthPreference = berthPreference; }

    public String getAllottedBerth() { return allottedBerth; }
    public void setAllottedBerth(String allottedBerth) { this.allottedBerth = allottedBerth; }

    public boolean isWithChild() { return withChild; }
    public void setWithChild(boolean withChild) { this.withChild = withChild; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }


    @Override
    public String toString() {
        return "Passenger{ " +
                "name: " + name +
                ", age: " + age +
                ", gender: " + gender +
                ", berthPreference: " + berthPreference +
                ", allotedBirth: " + allottedBerth +
                ", Ticket ID: " + ticketID +" "+
                '}';
    }
}
