package ua.hospital.admission;

import ua.hospital.common.Log;

import java.util.Map;

public class DoctorAssignment {
    private static final Map<String, String> DOCTORS = Map.of(
            "Кардіологія", "д-р Бондаренко",
            "Неврологія", "д-р Савчук",
            "Хірургія", "д-р Литвин");

    public String assign(String patientId, Patient patient) {
        String doctor = DOCTORS.getOrDefault(patient.department(), "черговий лікар");
        Log.log("DoctorAssignment", "Пацієнту " + patientId + " призначено лікаря: " + doctor);
        return doctor;
    }
}
