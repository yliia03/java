package ua.hospital.admission;

import ua.hospital.common.Log;

public class HospitalizationFacade {
    private final PatientRegistration registration;
    private final BedRegistry beds;
    private final DoctorAssignment doctors;
    private final ExaminationPlanner planner;
    private final KitchenNotifier kitchen;

    public HospitalizationFacade(PatientRegistration registration, BedRegistry beds,DoctorAssignment doctors, ExaminationPlanner planner,KitchenNotifier kitchen) {
        this.registration = registration;
        this.beds = beds;
        this.doctors = doctors;
        this.planner = planner;
        this.kitchen = kitchen;
    }

    public HospitalizationResult hospitalize(Patient patient) {
        Log.log("HospitalizationFacade", "Початок госпіталізації: " + patient.name());
        String id = registration.register(patient);
        String ward = beds.allocate(id, patient);
        String doctor = doctors.assign(id, patient);
        planner.plan(id, patient);
        kitchen.notifyKitchen(id, patient, ward);
        Log.log("HospitalizationFacade", "Госпіталізацію завершено: " + id);
        return new HospitalizationResult(id, ward, doctor);
    }
}
