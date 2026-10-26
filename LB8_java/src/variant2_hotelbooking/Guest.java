package variant2_hotelbooking;

public class Guest {
    private final int id;
    private final String fullName;
    private final String phone;
    private final String passportNumber;

    public Guest(int id, String fullName, String phone, String passportNumber) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.passportNumber = passportNumber;
    }

    public int getId() { return id; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getPassportNumber() { return passportNumber; }
}