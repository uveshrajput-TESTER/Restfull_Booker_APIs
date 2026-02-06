package org.thetestingAcademy.Tests.E2E;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.thetestingAcademy.Base.BaseTest;
import org.thetestingAcademy.Endpoints.API_Constants;
import org.thetestingAcademy.Manager.Payload_Manager;
import org.thetestingAcademy.Pojos.RequestPojos.Booking;
import org.thetestingAcademy.Pojos.ResponsePojo.BookingResponse;
import org.thetestingAcademy.Tests.CRUD.Positive_TestCases;

import static org.hamcrest.Matchers.greaterThan;

public class Flow3 extends BaseTest {

//    Get Booking ID,Update it,Check it
private static final Logger logger = LogManager.getLogger(Positive_TestCases.class);
    @Test(priority = 0)
    @Description( "TC#1 :- Check That Single  Bookings Get Successfully  ")
    @Owner("Uvesh  Ranghar")
    public void test_Get_Single_ID1(ITestContext context){
        logger.info("Test Case Starting ");
        int id = BaseTest.id;
        requestSpecification.basePath(API_Constants.Get_S_Booking +id);

        response = RestAssured.given(requestSpecification).when().log().all().get();
        System.out.println(response.asString());
        logger.info("Assertion Starting ");
        validatableResponse = response.then().log().all();
        validatableResponse.contentType(ContentType.JSON)                      // JSON response
                .body("size()", greaterThan(0));
        Booking booking =  Payload_Manager.getSingleBooking(response.asString());
        assertActions.verifyStatusCode(response,200);
    }
    @Test(priority = 1 )
    @Description( "TC#2 :- Full Update  Booking ID  ")
    @Owner("Uvesh  Ranghar")
    public void test_Full_Update_Existing_BookingIDs(@NonNull ITestContext context){
        requestSpecification.basePath(API_Constants.FULL_Update_Booking + BaseTest.id ).body(Payload_Manager.FUll_Update_Booking());
        requestSpecification.cookie("token",BaseTest.TOKEN);
        response = requestSpecification.when().log().all().put();
        System.out.println(response.asString());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
    }

    @Test(priority = 2)
    @Description( "TC#3 :- Check That Single  Bookings Get Successfully  ")
    @Owner("Uvesh  Ranghar")
    public void test_Get_Single_ID(ITestContext context){
        logger.info("Test Case Starting ");

        requestSpecification.basePath(API_Constants.Get_S_Booking + BaseTest.id);

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
