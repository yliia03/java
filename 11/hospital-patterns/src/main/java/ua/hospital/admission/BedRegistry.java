package ua.hospital.admission;

import ua.hospital.common.Log;

import java.util.HashMap;
import java.util.Map;

public class BedRegistry {
    private final Map<String, String> wardByPatientId = new HashMap<>();
    private int nextWard = 201;

    public String allocate(String patientId, Patient patient) {
        String ward = "Палата " + (nextWard++) + " (" + patient.department() + ")";
        wardByPatientId.put(patientId, ward);
        Log.log("BedRegistry", "Пацієнту " + patientId + " виділено ліжко: " + ward);
        return ward;
    }

    public void transfer(String patientId, String newWard) {
        String old = wardByPatientId.put(patientId, newWard);
        Log.log("BedRegistry", "Пацієнта " + patientId + " переведено: " + old + " → " + newWard);
    }

    public String wardOf(String patientId) {
        return wardByPatientId.get(patientId);
    }
}
