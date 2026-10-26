package variant2_hotelbooking;

public class SmsNotificationService {
    public void sendConfirmation(String phone, int bookingId) {
        System.out.println("[SMS] Підтвердження надіслано на номер " + phone);
    }
}