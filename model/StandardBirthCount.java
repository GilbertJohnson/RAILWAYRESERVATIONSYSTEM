package model;

public enum StandardBirthCount {
    L(1), // Lower Births count
    M(0), // Middle Births count
    U(0), // Upper Births count
    R(1), // RAC Births count
    W(1), // Waiting List count
    SU(0), // Side Upper Births count
    BIRTH(1); // All Confirm Births count

    private int numberOfBirths;

    StandardBirthCount(int numberOfBirths) {
        this.numberOfBirths = numberOfBirths;
    }

    public int getNumberOfBirths() { return numberOfBirths; }
    public void setNumberOfBirths(int numberOfBirths) { this.numberOfBirths = numberOfBirths; }
}
