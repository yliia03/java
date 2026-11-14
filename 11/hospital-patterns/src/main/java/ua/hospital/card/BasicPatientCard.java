package ua.hospital.card;

import ua.hospital.common.Log;

public class BasicPatientCard implements PatientCard {
    private final String patientName;
    private final String ward;

    public BasicPatientCard(String patientName, String ward) {
        this.patientName = patientName;
        this.ward = ward;
    }

    @Override
    public String render() {
        Log.log("BasicPatientCard", "Формує базове відображення картки");
        return "Картка пацієнта: " + patientName + ", " + ward;
    }

    @Override
    public void assignProcedure(String procedure) {
        Log.log("BasicPatientCard", "Процедуру «" + procedure + "» призначено пацієнту " + patientName);
    }
}
