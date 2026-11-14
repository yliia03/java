package ua.hospital.imaging;

import ua.hospital.common.Log;


public class ImageProxy implements MedicalImage {
    private static final String DENIED_MESSAGE = "Доступ до знімків мають лише лікарі";

    private final String id;
    private final String title;
    private final int sizeMb;
    private final ImageStorage storage;
    private RemoteMedicalImage real; // null, доки знімок не відкривали

    public ImageProxy(String id, String title, int sizeMb, ImageStorage storage) {
        this.id = id;
        this.title = title;
        this.sizeMb = sizeMb;
        this.storage = storage;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public void open(Employee viewer) {
        Log.log(source(), "Запит на відкриття від " + viewer.name()
                + " (" + viewer.role().label() + ")");
        if (viewer.role() != Role.DOCTOR) {
            Log.log(source(), "Відмова: роль «" + viewer.role().label() + "» не має доступу");
            throw new AccessDeniedException(DENIED_MESSAGE);
        }
        if (real == null) {
            Log.log(source(), "Перше відкриття: створюю справжній знімок");
            real = new RemoteMedicalImage(id, title, sizeMb, storage);
        } else {
            Log.log(source(), "Знімок уже в пам'яті, завантаження не потрібне");
        }
        real.open(viewer);
    }

    private String source() {
        return "ImageProxy[" + id + "]";
    }
}
