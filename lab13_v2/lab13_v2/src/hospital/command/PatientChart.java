package hospital.command;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class PatientChart {
    private final String patientName;
    private final Map<String, Integer> medications = new LinkedHashMap<>();
    private final Set<String> procedures = new LinkedHashSet<>();

    public PatientChart(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void addMedication(String drug, int doseMg) {
        medications.put(drug, doseMg);
    }

    public Integer removeMedication(String drug) {
        return medications.remove(drug);
    }

    public Integer getDose(String drug) {
        return medications.get(drug);
    }

    public void setDose(String drug, int doseMg) {
        medications.put(drug, doseMg);
    }

    public void addProcedure(String procedure) {
        procedures.add(procedure);
    }

    public boolean removeProcedure(String procedure) {
        return procedures.remove(procedure);
    }

    public boolean hasProcedure(String procedure) {
        return procedures.contains(procedure);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Лист пацієнта ").append(patientName).append(": препарати {");
        boolean first = true;
        for (Map.Entry<String, Integer> e : medications.entrySet()) {
            if (!first) sb.append(", ");
            sb.append(e.getKey()).append(" ").append(e.getValue()).append(" мг");
            first = false;
        }
        return sb.append("}, процедури ").append(procedures).toString();
    }
}
