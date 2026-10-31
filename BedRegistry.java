package hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Реєстр ліжкового фонду лікарні — SINGLETON.
 * Єдине джерело правди про зайнятість ліжок: приймальне відділення і профільні
 * відділення працюють з одним екземпляром, тому одне ліжко не можна
 * призначити двом пацієнтам, а дороге завантаження фонду виконується один раз.
 */
public final class BedRegistry {

    /** Лічильник створених екземплярів (для демонстрації). */
    private static int createdInstances = 0;

    /** Lazy holder: ліниве й потокобезпечне створення без явної синхронізації. */
    private static final class Holder {
        private static final BedRegistry INSTANCE = new BedRegistry();
    }

    private final List<Bed> beds = new ArrayList<>();

    private BedRegistry() {
        createdInstances++;
        Logger.log("Реєстр ліжок", "Завантаження ліжкового фонду з бази лікарні...");
        Logger.work(30);
        beds.add(new Bed("101", "Терапія"));
        beds.add(new Bed("102", "Терапія"));
        beds.add(new Bed("201", "Хірургія"));
        beds.add(new Bed("301", "Кардіологія"));
        beds.add(new Bed("302", "Кардіологія"));
        Logger.log("Реєстр ліжок", "Завантажено " + beds.size() + " ліжок. Реєстр готовий (екземпляр №"
                + createdInstances + ")");
    }

    public static BedRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public static int getCreatedInstances() {
        return createdInstances;
    }

    /** Призначає першe вільне ліжко у відділенні пацієнта. */
    public synchronized Optional<Bed> admit(Patient patient) {
        for (Bed bed : beds) {
            if (bed.getDepartment().equals(patient.getDepartment()) && bed.isFree()) {
                bed.occupy(patient);
                Logger.log("Реєстр ліжок", patient + " розміщено: " + bed);
                return Optional.of(bed);
            }
        }
        Logger.log("Реєстр ліжок", "Для " + patient + " немає вільних ліжок у відділенні «"
                + patient.getDepartment() + "»");
        return Optional.empty();
    }

    /** Виписка: звільняє ліжко пацієнта. */
    public synchronized boolean discharge(Patient patient) {
        for (Bed bed : beds) {
            if (bed.getPatient() == patient) {
                bed.release();
                Logger.log("Реєстр ліжок", patient + " виписано, звільнено " + bed);
                return true;
            }
        }
        return false;
    }

    public synchronized long freeCount(String department) {
        return beds.stream()
                .filter(b -> b.getDepartment().equals(department) && b.isFree())
                .count();
    }
}
