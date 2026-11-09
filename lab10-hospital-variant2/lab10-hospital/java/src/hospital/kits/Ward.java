package hospital.kits;

import hospital.util.EventLog;
import java.util.Set;
import java.util.TreeSet;

public class Ward {

    private final String name;

    public Ward(String name) {
        this.name = name;
    }

    public MedicalKit prepareKit(String patient, MedicalKitFactory factory) {
        EventLog.log(name, "Підготовка набору для пацієнта «" + patient
                + "», категорія: " + factory.categoryName());

        BloodPressureCuff cuff = factory.createCuff();
        IvCatheter catheter = factory.createCatheter();
        DosingSyringe syringe = factory.createSyringe();

        EventLog.log(name, "Видано манжету [" + cuff.category() + "]: " + cuff.describe());
        EventLog.log(name, "Видано катетер [" + catheter.category() + "]: " + catheter.describe());
        EventLog.log(name, "Видано шприц-дозатор [" + syringe.category() + "]: " + syringe.describe());

        Set<String> categories = new TreeSet<>();
        categories.add(cuff.category());
        categories.add(catheter.category());
        categories.add(syringe.category());
        if (categories.size() == 1) {
            EventLog.log(name, "Перевірка: усі вироби набору належать до однієї категорії — "
                    + categories.iterator().next());
        } else {
            EventLog.log(name, "ПОМИЛКА: у наборі змішано категорії " + categories);
        }
        return new MedicalKit(cuff, catheter, syringe);
    }
}
