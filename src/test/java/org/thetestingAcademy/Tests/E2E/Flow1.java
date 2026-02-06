package org.thetestingAcademy.Tests.E2E;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.thetestingAcademy.Base.BaseTest;
import org.thetestingAcademy.Endpoints.API_Constants;
import org.thetestingAcademy.Manager.Payload_Manager;
import org.thetestingAcademy.Pojos.RequestPojos.Booking;
import org.thetestingAcademy.Tests.CRUD.Positive_TestCases;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.greaterThan;

//Get All Booking IDs and Get a Single Booking ID from all booking ids
public class Flow1 extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Flow1.class);

    @Test(priority = 0)
    @Description( "TC#1 :- Check That All the Bookings Get Successfully fetch ")
    @Owner("Uvesh  Ranghar")
    public void test_Get_ALl_IDS(ITestContext context){
        logger.info("Test Case Starting ");
        requestSpecification.basePath(API_Constants.CREATE_GET_ALL_Booking);

        response = RestAssured.given(requestSpecification).when().log().all().get();
        System.out.println(response.asString());
        logger.info("Assertion Starting ");
        validatableResponse = response.then().statusCode(200);
        validatableResponse.contentType(ContentType.JSON)                      // JSON response
                .body("size()", greaterThan(0));
        validatableResponse.body("bookingid",everyItem(notNullValue()));
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        int  bookingid = bookingIds.get(1);
        System.out.println("bookingid --> "+ bookingid);
        context.setAttribute("ID",bookingid);
    }
    @Test(priority = 3)
    @Description( "TC#4 :- Check That Single  Bookings Get Successfully  ")
    @Owner("Uvesh  Ranghar")
    public void test_Get_Single_ID(ITestContext context){
        logger.info("Test Case Starting ");
        int bookingId = (int) context.getAttribute("ID");
        requestSpecification.basePath(API_Constants.Get_S_Booking + bookingId);

        response = RestAssured.given(requestSpecification).when().log().all().get();
        System.out.println(response.asString());
        logger.info("Assertion Starting ");
        validatableResponse = response.then().log().all();
        validatableResponse.contentType(ContentType.JSON)                      // JSON response
                .body("size()", greaterThan(0));
        Booking booking =  Payload_Manager.getSingleBooking(response.asString());
        assertActions.verifyStatusCode(response,200);
    }

}
