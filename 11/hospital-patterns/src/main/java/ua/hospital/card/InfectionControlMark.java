package ua.hospital.card;

import ua.hospital.common.Log;

public class InfectionControlMark extends CardMark {

    public InfectionControlMark(PatientCard inner) {
        super(inner);
    }

    @Override
    public String render() {
        Log.log("InfectionControlMark", "Додає попередження про інфекційний контроль і передає картку далі");
        return super.render() + "\n    [ІНФЕКЦІЙНИЙ КОНТРОЛЬ] Ізоляційний режим";
    }

    @Override
    public void assignProcedure(String procedure) {
        Log.log("InfectionControlMark", "Нагадування: для процедури «" + procedure
                + "» використати засоби захисту (маска, рукавички, халат)");
        super.assignProcedure(procedure);
    }
}
