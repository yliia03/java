package variant2_hotelbooking;

public class BookingController {
    private final IBookingRepository repository;
    private final SmsNotificationService notificationService;

    public BookingController(IBookingRepository repository, SmsNotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public Booking processNewBooking(String guestName, String season) {
        Guest guest = new Guest(10, guestName, "+380971112233", "AA123456");
        Room room = new Room(302, "Deluxe", 2000.0);

        // Creator / Information Expert в дії
        Booking booking = new Booking(501, guest, room, 3, season, true, true);

        repository.save(booking);
        notificationService.sendConfirmation(guest.getPhone(), booking.getBookingId());

        return booking;
    }
}
