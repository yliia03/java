package ua.hospital.imaging;

public interface MedicalImage {

    String id();

    String title();

    void open(Employee viewer);
}
