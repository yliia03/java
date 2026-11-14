package ua.hospital.imaging;

import ua.hospital.common.Log;

import java.util.List;

public class PatientImageCard {
    private final String patientName;
    private final List<MedicalImage> images;

    public PatientImageCard(String patientName, List<MedicalImage> images) {
        this.patientName = patientName;
        this.images = List.copyOf(images);
    }

    public void show() {
        Log.log("PatientImageCard", "Відкрито картку пацієнта " + patientName
                + ", знімків: " + images.size());
        for (MedicalImage image : images) {
            Log.log("PatientImageCard", "  " + image.id() + " — " + image.title());
        }
    }

    public void openImage(String imageId, Employee viewer) {
        MedicalImage image = images.stream()
                .filter(i -> i.id().equals(imageId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Немає знімка " + imageId));
        try {
            image.open(viewer);
        } catch (AccessDeniedException e) {
            Log.log("PatientImageCard", "Не вдалося відкрити " + imageId + ": «" + e.getMessage() + "»");
        }
    }
}
