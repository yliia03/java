package ua.hospital.lab;

final class Units {
    private Units() {
    }

    static double umolToMmol(double umol) {
        return umol / 1000.0;
    }
}
