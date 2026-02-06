package org.thetestingAcademy.Manager;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.thetestingAcademy.Pojos.RequestPojos.Auth;
import org.thetestingAcademy.Pojos.RequestPojos.Booking;
import org.thetestingAcademy.Pojos.RequestPojos.Bookingdates;
import org.thetestingAcademy.Pojos.RequestPojos.WrongBooking;
import org.thetestingAcademy.Pojos.ResponsePojo.BookingResponse;
import org.thetestingAcademy.Pojos.ResponsePojo.TokenResponse;

public class Payload_Manager {
    Faker faker;
    // Convert the Java Object into the JSON String to use as Payload.
    // Serialization
    public String createPayloadBookingAsString(){
        Booking booking = new Booking();
        booking.setFirstName("Uvesh");
        booking.setLastName("Ranghar");
        booking.setTotalPrice(112);
        booking.setDepositPaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalNeeds("Breakfast");
        System.out.println(booking);
// Java Object -> JSON
       Gson gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }
    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstName("Uvesh");
        booking.setLastName("Rajput");
        booking.setTotalPrice(112);
        booking.setDepositPaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalNeeds("Breakfast");
        System.out.println(booking);
// Java Object -> JSON
        Gson gson = new Gson();
        String FullBooking = gson.toJson(booking);
        return FullBooking;
    }
    public static String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");

       Gson  gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the -> " + jsonPayloadString);
        return jsonPayloadString;

    }
    public static String setWrongAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("Admin");
        auth.setPassword("password1234");

        Gson  gson = new Gson();
        String jsonPayloadString = gson.toJson(auth);
        System.out.println("Payload set to the -> " + jsonPayloadString);
        return jsonPayloadString;

    }
    public static String Create_Booking(){
        Booking booking = new Booking();
        booking.setFirstName("Uvesh");
        booking.setLastName("Gupta");
        booking.setTotalPrice(12344);
        booking.setDepositPaid(false);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalNeeds("need Breakfast");

        Gson gson  = new Gson();
        String createBooking = gson.toJson(booking);
        return createBooking;

    }
    public static String Create_Booking_with_Wrong_payload(){
        Booking booking = new Booking();
        booking.setTotalPrice(12344);
        booking.setDepositPaid(false);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalNeeds("need Breakfast");
        Gson gson  = new Gson();
        String createBooking = gson.toJson(booking);
        return createBooking;

    }
    public static String FUll_Update_Booking(){
        Booking booking = new Booking();
        booking.setFirstName("Uvesh");
        booking.setLastName("papa");
        booking.setTotalPrice(12547657);
        booking.setDepositPaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalNeeds("need Breakfast");

        Gson gson  = new Gson();
        String updateBooking = gson.toJson(booking);
        return updateBooking;
    }
    public static String FUll_Update_Booking_Wrong_Payload(){
        Booking booking = new Booking();
        booking.setTotalPrice(12547657);
        booking.setDepositPaid(true);
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalNeeds("need Breakfast");

        Gson gson  = new Gson();
        String updateBooking = gson.toJson(booking);
        return updateBooking;
    }
    public static String Partially_Update_Booking(){
        Booking booking = new Booking();
        booking.setFirstName("Uvesh");
        booking.setLastName("papa");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-07-01");
        bookingdates.setCheckout("2024-10-05");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalNeeds("need Breakfast");

        Gson gson  = new Gson();
        String updateBooking = gson.toJson(booking);
        return updateBooking;
    }
    public static String Partially_Update_Booking_with_wrongpayload(){
        WrongBooking booking = new WrongBooking();
        booking.setFirstname(543545);
        booking.setLastname(32534543);
        booking.setTotalprice(35464);
        Gson gson  = new Gson();
        String updateBooking = gson.toJson(booking);
        return updateBooking;
    }
    // Convert the JSON String to Java Object so that we can verify response Body
    // DeSerialization
    public BookingResponse bookingResponseJava(String responseString) {
       Gson  gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }
    public static TokenResponse AuthresponseJava(String responseString){
        Gson gson = new Gson();
        TokenResponse tokenresponse = gson.fromJson(responseString,TokenResponse.class);
        return tokenresponse;
    }
    public static  BookingResponse getNewBooking(String responseString){
        Gson gson = new Gson();
        BookingResponse bookingresponse = gson.fromJson(responseString,BookingResponse.class);
        return bookingresponse;
    }
    public static Booking getSingleBooking(String responseString){
        Gson gson = new Gson();
        Booking booking = gson.fromJson(responseString,Booking.class);return booking;
    }

}
