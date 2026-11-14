package ua.hospital.admission;

import ua.hospital.common.Log;

public class PlannedHospitalizationModule {
    private final HospitalizationFacade facade;

    public PlannedHospitalizationModule(HospitalizationFacade facade) {
        this.facade = facade;
    }

    public HospitalizationResult admitPlanned(Patient patient) {
        Log.log("PlannedHospitalizationModule", "Планова госпіталізація: " + patient.name());
        return facade.hospitalize(patient);
    }
}
