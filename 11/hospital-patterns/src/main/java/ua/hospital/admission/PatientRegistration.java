package ua.hospital.admission;

import ua.hospital.common.Log;

public class PatientRegistration {
    private int counter = 1000;

    public String register(Patient patient) {
        String id = "P-" + (++counter);
        Log.log("PatientRegistration", "Зареєстровано пацієнта " + patient.name() + ", номер " + id);
        return id;
    }
}
