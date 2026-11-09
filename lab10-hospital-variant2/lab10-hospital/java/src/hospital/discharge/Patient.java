package hospital.discharge;

import java.util.Map;

public final class Patient {

    private final String name;
    private final String diagnosis;
    private final boolean treatmentCompleted;
    private final Map<String, String> clinicalData;

    public Patient(String name, String diagnosis, boolean treatmentCompleted,Map<String, String> clinicalData) {
        this.name = name;
        this.diagnosis = diagnosis;
        this.treatmentCompleted = treatmentCompleted;
        this.clinicalData = Map.copyOf(clinicalData);
    }

    public String getName() {
        return name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public boolean isTreatmentCompleted() {
        return treatmentCompleted;
    }

    public String data(String key) {
        return clinicalData.getOrDefault(key, "не вказано");
    }
}
