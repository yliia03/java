package task1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PatientChart {
    private final String patientName;
    private final Map<String, Integer> medications = new HashMap<>();
    private final Set<String> procedures = new HashSet<>();

    public PatientChart(String patientName) {
        this.patientName = patientName;
        this.procedures.add("Рентген грудної клітки");
        this.procedures.add("Загальний аналіз крові");
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
        if (doseMg <= 0) {
            medications.remove(drug);
        } else {
            medications.put(drug, doseMg);
        }
    }

    public void addProcedure(String procedure) {
        procedures.add(procedure);
    }

    public boolean removeProcedure(String procedure) {
        return procedures.remove(procedure);
    }

    public void printChartState() {
        System.out.printf("   >>> Лист призначень (%s): ліки=%s, процедури=%s%n",
                patientName, medications, procedures);
    }
}
