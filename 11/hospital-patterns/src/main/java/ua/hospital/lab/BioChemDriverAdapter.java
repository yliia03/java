package ua.hospital.lab;

import ua.hospital.common.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BioChemDriverAdapter implements LabAnalyzer {
    private static final Map<String, String> CODES = Map.of(
            "GLU", "Глюкоза",
            "UREA", "Сечовина",
            "CREA", "Креатинін");

    private final BioChemDriver driver;

    public BioChemDriverAdapter(BioChemDriver driver) {
        this.driver = driver;
    }

    @Override
    public String name() {
        return "Біохімічний аналізатор BioChem";
    }

    @Override
    public List<LabResult> fetchResults(String patientId) {
        Log.log("BioChemDriverAdapter", "Викликаю драйвер BioChemDriver.requestRawResult(" + patientId + ")");
        String raw = driver.requestRawResult(patientId);

        List<LabResult> results = new ArrayList<>();
        for (String token : raw.split(";")) {
            String[] kv = token.split("=", 2);
            String key = kv[0];
            if (key.equals("SMP")) {
                continue;
            }
            if (key.equals("UNIT")) {
                if (!kv[1].equals("umol/L")) {
                    throw new IllegalStateException("Непідтримувана одиниця: " + kv[1]);
                }
                continue;
            }
            String indicator = CODES.get(key);
            if (indicator == null) {
                Log.log("BioChemDriverAdapter", "Невідомий код показника " + key + " — пропущено");
                continue;
            }
            double mmol = Units.umolToMmol(Double.parseDouble(kv[1]));
            results.add(new LabResult(indicator, mmol));
        }
        Log.log("BioChemDriverAdapter", "Рядок розібрано й перетворено (мкмоль/л → ммоль/л), результатів: "
                + results.size());
        return results;
    }
}
