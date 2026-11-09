package hospital.discharge;

import hospital.util.EventLog;

import java.util.ArrayList;
import java.util.List;

public class DischargeArchive {

    private final List<DischargeDocument> documents = new ArrayList<>();

    public void store(DischargeDocument document) {
        documents.add(document);
        EventLog.log("Архів", "Документ «" + document.title() + "» пацієнта "+ document.patientName() + " прийнято (усього в архіві: " + documents.size() + ")");
    }

    public int size() {
        return documents.size();
    }
}
