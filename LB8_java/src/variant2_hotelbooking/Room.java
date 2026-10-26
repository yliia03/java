package variant2_hotelbooking;

public class Room {
    private final int roomNumber;
    private final String category; // "Standard", "Deluxe", "Suite"
    private final double basePricePerNight;

    public Room(int roomNumber, String category, double basePricePerNight) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.basePricePerNight = basePricePerNight;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public double getBasePricePerNight() { return basePricePerNight; }
}