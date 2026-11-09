package hospital.discharge;

public class TherapyDepartment extends Department {

    public TherapyDepartment(DischargeArchive archive) {
        super("Терапія", "Др. Іваненко", archive);
    }

    @Override
    protected DischargeDocument createDocument(Patient patient) {
        return new GeneralEpicrisis(patient);
    }
}
