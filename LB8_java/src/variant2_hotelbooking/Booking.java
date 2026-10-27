package variant2_hotelbooking;

public class Booking {
    private final int bookingId;
    private final Guest guest;
    private final Room room;
    private final int nights;
    private final String season; 
    private final boolean includeBreakfast;
    private final boolean includeParking;
    
    private double roomTotal;
    private double servicesTotal;
    private double touristTax;
    private double grandTotal;
    private String status;

    public Booking(int bookingId, Guest guest, Room room, int nights, String season, boolean includeBreakfast, boolean includeParking) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.nights = nights;
        this.season = season;
        this.includeBreakfast = includeBreakfast;
        this.includeParking = includeParking;
        this.status = "Created";
        calculateTotals();
    }

    private void calculateTotals() {
        double seasonalMultiplier = 1.0;
        if ("Peak".equalsIgnoreCase(season)) {
            seasonalMultiplier = 1.5;
        } else if ("High".equalsIgnoreCase(season)) {
            seasonalMultiplier = 1.25;
        } else if ("Low".equalsIgnoreCase(season)) {
            seasonalMultiplier = 0.9;
        }

        this.roomTotal = room.getBasePricePerNight() * nights * seasonalMultiplier;

        double services = 0.0;
        if (includeBreakfast) {
            services += 300.0 * nights;
        }
        if (includeParking) {
            services += 150.0 * nights;
        }
        this.servicesTotal = services;

        this.touristTax = (roomTotal + servicesTotal) * 0.01;
        this.grandTotal = roomTotal + servicesTotal + touristTax;
        this.status = "Confirmed";
    }

    public int getBookingId() { return bookingId; }
    public Guest getGuest() { return guest; }
    public Room getRoom() { return room; }
    public double getRoomTotal() { return roomTotal; }
    public double getServicesTotal() { return servicesTotal; }
    public double getTouristTax() { return touristTax; }
    public double getGrandTotal() { return grandTotal; }
    public String getStatus() { return status; }
}
