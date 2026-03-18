package TestData;

import pojo.BookingDates;
import pojo.CreateBooking;

public class TestDataBuild {
    CreateBooking bookingDetails =new CreateBooking();
    public  CreateBooking createBookingPayLoad(String firstname, String lastname, boolean depositpaid, String Checkin, String checkout, String phone)
    {

        String roomid= String.valueOf(RoomIdGenerator.randomRoomID());
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
}
