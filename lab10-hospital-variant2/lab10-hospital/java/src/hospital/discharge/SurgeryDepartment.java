package hospital.discharge;

public class SurgeryDepartment extends Department {

    public SurgeryDepartment(DischargeArchive archive) {
        super("Хірургія", "Др. Коваль", archive);
    }

    @Override
    protected DischargeDocument createDocument(Patient patient) {
        return new SurgeryEpicrisis(patient);
    }
}
