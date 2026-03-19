package TestData;

import pojo.BookingDates;
import pojo.BookingResponse;
import pojo.CreateBooking;

public class TestDataBuild {
    CreateBooking bookingDetails =new CreateBooking();
    public  CreateBooking createBookingPayLoad(String roomid,String firstname, String lastname, boolean depositpaid, String Checkin, String checkout, String phone) {
        bookingDetails.setRoomid(roomid);
        bookingDetails.setFirstname(firstname);
        bookingDetails.setLastname(lastname);
        bookingDetails.setDepositpaid(depositpaid);
        BookingDates bookingdates=new BookingDates();
        bookingdates.setCheckin(Checkin);
        bookingdates.setCheckout(checkout);
        bookingDetails.setBookingdates(bookingdates);
        String email=RandomEmailGenerator.generateRandomEmail();
        bookingDetails.setEmail(email);
        bookingDetails.setPhone(phone);
        return bookingDetails;
    }

    public BookingResponse updateBookingDetailsPayload(int BookingId, int RoomId, String firstname, String lastname, boolean depositpaid, Object Checkin, Object checkout) {
        BookingResponse bookingById=new BookingResponse();
        bookingById.setBookingid(BookingId);
        bookingById.setRoomid(RoomId);
        bookingById.setFirstname(firstname);
        bookingById.setLastname(lastname);
        bookingById.setDepositpaid(depositpaid);
        BookingDates bookingDatesForUpdate=new BookingDates();
        bookingDatesForUpdate.setCheckin(Checkin);
        bookingDatesForUpdate.setCheckout(checkout);
        bookingById.setBookingdates(bookingDatesForUpdate);

        return bookingById;
    }
}
