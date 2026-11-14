package ua.hospital.lab;

import ua.hospital.common.Log;

public class ChemProLink {

    public String[] dump(String patientRef) {
        String[] data = {"GLUCOSE@5900", "UREA@4100", "CREATININE@95"};
        Log.log("ChemProLink (виробник 2)", "Повернув записи для " + patientRef + ": "
                + String.join(", ", data));
        return data;
    }
}
