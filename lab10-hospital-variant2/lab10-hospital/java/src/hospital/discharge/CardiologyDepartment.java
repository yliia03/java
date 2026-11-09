package hospital.discharge;

public class CardiologyDepartment extends Department {

    public CardiologyDepartment(DischargeArchive archive) {
        super("Кардіологія", "Др. Мельничук", archive);
    }

    @Override
    protected DischargeDocument createDocument(Patient patient) {
        return new CardiologyEpicrisis(patient);
    }
}
