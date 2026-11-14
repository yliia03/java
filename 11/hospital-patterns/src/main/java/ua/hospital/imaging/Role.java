package ua.hospital.imaging;

public enum Role {
    DOCTOR("лікар"),
    REGISTRAR("реєстратор"),
    NURSE("медсестра");

    private final String label;

    Role(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }
}
