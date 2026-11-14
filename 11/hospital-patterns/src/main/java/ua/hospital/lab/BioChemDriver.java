package ua.hospital.lab;

import ua.hospital.common.Log;

public class BioChemDriver {

    public String requestRawResult(String sampleCode) {
        String raw = "SMP=" + sampleCode + ";GLU=5600;UREA=4200;CREA=88;UNIT=umol/L";
        Log.log("BioChemDriver (виробник)", "Повернув рядок: " + raw);
        return raw;
    }
}
