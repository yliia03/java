package ua.hospital.card;

import ua.hospital.common.Log;

public class AllergyMark extends CardMark {
    private final String allergen;

    public AllergyMark(PatientCard inner, String allergen) {
        super(inner);
        this.allergen = allergen;
    }

    @Override
    public String render() {
        Log.log("AllergyMark", "Додає попередження про алергію і передає картку далі");
        return super.render() + "\n    [АЛЕРГІЯ] Алерген: " + allergen;
    }
}
