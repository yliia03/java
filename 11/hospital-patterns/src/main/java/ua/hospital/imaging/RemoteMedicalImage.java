package ua.hospital.imaging;

import ua.hospital.common.Log;

public class RemoteMedicalImage implements MedicalImage {
    private final String id;
    private final String title;
    private final int loadedMb;

    public RemoteMedicalImage(String id, String title, int sizeMb, ImageStorage storage) {
        this.id = id;
        this.title = title;
        this.loadedMb = storage.download(id, sizeMb);
        Log.log(source(), "Знімок завантажено в пам'ять (" + loadedMb + " МБ)");
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
        Log.log(source(), "Відображає знімок «" + title + "» для "
                + viewer.name() + " (" + viewer.role().label() + ")");
    }

    private String source() {
        return "RemoteMedicalImage[" + id + "]";
    }
}
