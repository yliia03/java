package ua.hospital.admission;

import ua.hospital.common.Log;

public class AdmissionDepartment {
    private final HospitalizationFacade facade;

    public AdmissionDepartment(HospitalizationFacade facade) {
        this.facade = facade;
    }

    public HospitalizationResult admitEmergency(Patient patient) {
        Log.log("AdmissionDepartment", "Екстрена госпіталізація: " + patient.name());
        return facade.hospitalize(patient);
    }
}
