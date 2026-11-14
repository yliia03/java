package ua.hospital.imaging;

import ua.hospital.common.Log;

public class ImageStorage {
    private int downloadCount;

    public int download(String imageId, int sizeMb) {
        downloadCount++;
        Log.log("ImageStorage", "Завантаження знімка " + imageId
                + " з віддаленого сховища (" + sizeMb + " МБ)...");
        return sizeMb;
    }

    public int getDownloadCount() {
        return downloadCount;
    }
}
