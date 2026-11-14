package ua.hospital.admission;

import ua.hospital.common.Log;

public class ExaminationPlanner {

    public String plan(String patientId, Patient patient) {
        String plan = "ЕКГ, загальний аналіз крові, консультація за діагнозом «" + patient.diagnosis() + "»";
        Log.log("ExaminationPlanner", "Сформовано план обстеження для " + patientId + ": " + plan);
        return plan;
    }
}
