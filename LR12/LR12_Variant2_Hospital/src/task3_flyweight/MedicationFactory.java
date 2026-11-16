package task3_flyweight;

import java.util.HashMap;
import java.util.Map;

public class MedicationFactory {
    private static final Map<String, MedicationType> cache = new HashMap<>();

    public static MedicationType getMedication(String name, String activeSubstance, String releaseForm,
                                               String instructions, String contraindications, String interactions) {
        return cache.computeIfAbsent(name, k ->
                new MedicationType(name, activeSubstance, releaseForm, instructions, contraindications, interactions)
        );
    }

    public static int getCreatedTypesCount() {
        return cache.size();
    }
}
