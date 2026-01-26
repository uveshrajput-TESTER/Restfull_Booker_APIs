package org.thetestingAcademy.Tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.thetestingAcademy.Base.BaseTest;
import org.thetestingAcademy.Endpoints.API_Constants;
import org.thetestingAcademy.Manager.Payload_Manager;
import org.thetestingAcademy.Pojos.RequestPojos.Bookingdates;
import org.thetestingAcademy.Pojos.ResponsePojo.BookingResponse;

import javax.xml.validation.Validator;

public class Create_New_BookingIDs extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Create_New_BookingIDs.class);
    @Test
    @Description( "TC#1 :- Check That Single  Bookings Get Successfully  Successfully ")
    @Owner("Uvesh  Ranghar")
    public void test_Create_new_Booking(){
        requestSpecification.basePath(API_Constants.CREATE_GET_ALL_Booking).body(Payload_Manager.Create_Booking());
        response = requestSpecification.when().log().all().post();
        System.out.println(response.asString());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
          BookingResponse bookingReasponse = Payload_Manager.getNewBooking(response.asString());
          assertActions.verifyIntegerKeyNotNull( bookingReasponse.getBookingid());





    }
}
