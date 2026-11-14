package ua.hospital.lab;

import ua.hospital.common.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChemProLinkAdapter implements LabAnalyzer {
    private static final Map<String, String> CODES = Map.of(
            "GLUCOSE", "Глюкоза",
            "UREA", "Сечовина",
            "CREATININE", "Креатинін");

    private final ChemProLink device;

    public ChemProLinkAdapter(ChemProLink device) {
        this.device = device;
    }

    @Override
    public String name() {
        return "Аналізатор ChemPro";
    }

    @Override
    public List<LabResult> fetchResults(String patientId) {
        Log.log("ChemProLinkAdapter", "Викликаю пристрій ChemProLink.dump(" + patientId + ")");
        String[] records = device.dump(patientId);

        List<LabResult> results = new ArrayList<>();
        for (String record : records) {
            String[] parts = record.split("@", 2);
            String indicator = CODES.get(parts[0]);
            if (indicator == null) {
                Log.log("ChemProLinkAdapter", "Невідомий код показника " + parts[0] + " — пропущено");
                continue;
            }
            results.add(new LabResult(indicator, Units.umolToMmol(Double.parseDouble(parts[1]))));
        }
        Log.log("ChemProLinkAdapter", "Записи розібрано й перетворено (мкмоль/л → ммоль/л), результатів: "
                + results.size());
        return results;
    }
}
