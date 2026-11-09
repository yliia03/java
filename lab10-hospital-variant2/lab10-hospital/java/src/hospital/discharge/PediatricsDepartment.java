package hospital.discharge;

public class PediatricsDepartment extends Department {

    public PediatricsDepartment(DischargeArchive archive) {
        super("Педіатрія", "Др. Гончар", archive);
    }

    @Override
    protected DischargeDocument createDocument(Patient patient) {
        return new PediatricEpicrisis(patient);
    }
}
