package variant2_hotelbooking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Бронювання номера в готелі (GRASP Refactored) ===");
        System.out.print("Введіть ПІБ гостя: ");
        String name = scanner.nextLine();
        System.out.print("Введіть сезон (Low/High/Peak): ");
        String season = scanner.nextLine();

        IBookingRepository repo = new FileBookingRepository();
        SmsNotificationService notifier = new SmsNotificationService();
        BookingController controller = new BookingController(repo, notifier);

        Booking booking = controller.processNewBooking(name, season);

        System.out.println("\n--- Квитанція ---");
        System.out.println("Бронювання #" + booking.getBookingId() + " для " + booking.getGuest().getFullName());
        System.out.println("Проживання: " + booking.getRoomTotal() + " грн");
        System.out.println("Послуги: " + booking.getServicesTotal() + " грн");
        System.out.println("Туристичний збір: " + booking.getTouristTax() + " грн");
        System.out.println("Всього: " + booking.getGrandTotal() + " грн");
    }
}