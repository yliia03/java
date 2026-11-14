package ua.hospital.admission;

import ua.hospital.common.Log;

public class KitchenNotifier {

    public void notifyKitchen(String patientId, Patient patient, String ward) {
        Log.log("KitchenNotifier", "Харчоблок отримав повідомлення: " + patientId + ", " + ward + ", дієта: " + patient.diet());
    }
}
