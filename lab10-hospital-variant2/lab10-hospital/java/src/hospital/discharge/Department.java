package hospital.discharge;

import hospital.util.EventLog;
import java.util.Optional;

public abstract class Department {

    private final String name;
    private final String chiefDoctor;
    private final DischargeArchive archive;

    protected Department(String name, String chiefDoctor, DischargeArchive archive) {
        this.name = name;
        this.chiefDoctor = chiefDoctor;
        this.archive = archive;
    }

    public String getName() {
        return name;
    }

    protected abstract DischargeDocument createDocument(Patient patient);

    public final Optional<DischargeDocument> discharge(Patient patient) {
        EventLog.log(name, "Початок виписки пацієнта " + patient.getName());

        if (!patient.isTreatmentCompleted()) {
            EventLog.log(name, "Виписку скасовано: лікування пацієнта " + patient.getName()+ " не завершено");
            return Optional.empty();
        }
        EventLog.log(name, "Завершення лікування підтверджено");

        DischargeDocument document = createDocument(patient);
        EventLog.log(name, "Сформовано документ «" + document.title() + "»");

        document.sign(chiefDoctor);
        EventLog.log(name, "Документ підписано: " + chiefDoctor);

        archive.store(document);
        return Optional.of(document);
    }
}
