package ua.hospital.card;

import ua.hospital.common.Log;

public class FallRiskMark extends CardMark {

    public FallRiskMark(PatientCard inner) {
        super(inner);
    }

    @Override
    public String render() {
        Log.log("FallRiskMark", "Додає попередження про ризик падіння і передає картку далі");
        return super.render() + "\n    [РИЗИК ПАДІННЯ] Пересування лише в супроводі персоналу";
    }
}
