package task3_flyweight;

public class MedicationType {
    private final String name;
    private final String activeSubstance;
    private final String releaseForm;
    private final String instructions;
    private final String contraindications;
    private final String interactions;

    public MedicationType(String name, String activeSubstance, String releaseForm,
                          String instructions, String contraindications, String interactions) {
        this.name = name;
        this.activeSubstance = activeSubstance;
        this.releaseForm = releaseForm;
        this.instructions = instructions;
        this.contraindications = contraindications;
        this.interactions = interactions;
    }

    public String getName() { return name; }
    public String getActiveSubstance() { return activeSubstance; }
    public String getReleaseForm() { return releaseForm; }
    public String getInstructions() { return instructions; }
    public String getContraindications() { return contraindications; }
    public String getInteractions() { return interactions; }

    public String getShortInfo() {
        return String.format("%s (%s, %s) [hash=%d]",
                name, activeSubstance, releaseForm, System.identityHashCode(this));
    }

    public String getFullClinicalDetails() {
        return String.format("Інструкція: %s | Протипоказання: %s | Взаємодії: %s",
                instructions, contraindications, interactions);
    }
}
