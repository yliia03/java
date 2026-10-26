package variant2_hotelbooking;

public class FileBookingRepository implements IBookingRepository {
    @Override
    public void save(Booking booking) {
        System.out.println("[Файлова БД] Запис про бронювання #" + booking.getBookingId() + " додано до bookings.dat.");
    }
}