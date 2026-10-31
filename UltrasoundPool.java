package hospital;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UltrasoundPool {
    private final Deque<UltrasoundDevice> available = new ArrayDeque<>();
    private final Set<UltrasoundDevice> inUse = new HashSet<>();

    public UltrasoundPool(int size) {
        Logger.log("Пул УЗД", "Ініціалізація пулу на " + size + " апарати");
        for (int i = 1; i <= size; i++) {
            available.add(new UltrasoundDevice("US-0" + i));
        }
    }

    public synchronized Optional<UltrasoundDevice> acquire(Patient patient, String profile) {
        UltrasoundDevice device = available.poll();
        if (device == null) {
            Logger.log("Пул УЗД", "Усі апарати зайняті, " + patient + " очікує");
            return Optional.empty();
        }
        device.assign(patient, profile);
        inUse.add(device);
        Logger.log("Пул УЗД", "Видано " + device.getId() + " для " + patient + " (профіль «" + profile + "»). Вільних: " + available.size());
        return Optional.of(device);
    }

    public synchronized void release(UltrasoundDevice device) {
        if (!inUse.remove(device)) {
            Logger.log("Пул УЗД", "Апарат " + device.getId() + " не належить пулу або вже повернений");
            return;
        }
        Patient previous = device.getCurrentPatient();
        device.reset();
        available.add(device);
        Logger.log("Пул УЗД", device.getId() + " повернено після " + previous + ", налаштування скинуто. Вільних: " + available.size());
    }

    public synchronized int availableCount() {
        return available.size();
    }

    public synchronized int inUseCount() {
        return inUse.size();
    }
}
