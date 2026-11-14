package ua.hospital.lab;

import java.util.Locale;

public record LabResult(String indicator, double valueMmolPerL) {
    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%s = %.3f ммоль/л", indicator, valueMmolPerL);
    }
}
