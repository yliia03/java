package ua.hospital.lab;

import ua.hospital.common.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalLabSystem {
    private final Map<String, List<LabResult>> cards = new HashMap<>();

    public void receive(LabAnalyzer analyzer, String patientId) {
        Log.log("HospitalLabSystem", "Запит результатів у «" + analyzer.name()
                + "» для пацієнта " + patientId);
        List<LabResult> results = analyzer.fetchResults(patientId);
        for (LabResult result : results) {
            cards.computeIfAbsent(patientId, k -> new ArrayList<>()).add(result);
            Log.log("HospitalLabSystem", "Записано у картку " + patientId + ": " + result);
        }
    }

    public List<LabResult> cardOf(String patientId) {
        return List.copyOf(cards.getOrDefault(patientId, List.of()));
    }
}
