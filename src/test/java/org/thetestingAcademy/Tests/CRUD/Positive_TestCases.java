package org.thetestingAcademy.Tests.CRUD;

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
import org.thetestingAcademy.Pojos.ResponsePojo.TokenResponse;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.greaterThan;

public class Positive_TestCases extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Positive_TestCases.class);


    @Test(priority = 0)
    @Description( "TC#1 :- Run Ping Request Check Server is live or not")
    @Owner("Uvesh ")
    public void test_Check_Health(){
        logger.info("TC#1 :- Run Ping Request Check Server is live or not");
        logger.info("Starting of Test Case ");
        requestSpecification.basePath(API_Constants.Ping);
        response = RestAssured.given(requestSpecification).when().log().all().get();
        System.out.println(response);
        validatableResponse = response.then().log().all();
        assertActions.verifyResponseBody(response.asString(),"Created","value Must Be Match" );
    }
    @Test(priority = 1)
    @Description( "TC#2 :- Check That with Valid Username And Password Token generate Successfully ")
    @Owner("Uvesh  Ranghar")
    public void test_Generate_token(ITestContext context){
        logger.info("Test Case Starting ");
        requestSpecification.basePath(API_Constants.CREATE_Token);

        response = RestAssured.given(requestSpecification).body(Payload_Manager.setAuthPayload()).when().log().all().post();
        System.out.println(response.asString());
        TokenResponse tokenResponse = Payload_Manager.AuthresponseJava(response.asString());
        context.setAttribute("token",tokenResponse.getToken());
        logger.info("Assertion Starting ");
        assertActions.verifyStatusCode(response ,200);
        assertActions.verifyStringKeyNotNull(tokenResponse.getToken());
        assertActions.verifyStringKeyNotBlank(tokenResponse.getToken());

    }
    @Test(priority = 2)
    @Description( "TC#3 :- Check That All the Bookings Get Successfully  Successfully ")
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
    @Test(priority = 4)
    @Description( "TC#5 :- Check That New  Bookings Get Successfully  Created  ")
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
    @Test(priority = 5,dependsOnMethods ="test_Get_ALl_IDS" )
    @Description( "TC#5 :- Full Update Existing Booking ID  ")
    @Owner("Uvesh  Ranghar")
    public void test_Full_Update_Existing_BookingIDs(@NonNull ITestContext context){
         requestSpecification.basePath(API_Constants.FULL_Update_Booking + context.getAttribute("ID") ).body(Payload_Manager.FUll_Update_Booking());
         requestSpecification.cookie("token",BaseTest.TOKEN);
         response = requestSpecification.when().log().all().put();
        System.out.println(response.asString());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
    }
    @Test(priority = 6,dependsOnMethods ="test_Get_ALl_IDS" )
    @Description( "TC#5 :- Partially Update Existing Booking ID  ")
    @Owner("Uvesh  Ranghar")
    public void test_Partially_Update_Booking_IDs(ITestContext context){
        requestSpecification.basePath(API_Constants.FULL_Update_Booking + context.getAttribute("ID") ).body(Payload_Manager.Partially_Update_Booking()).contentType(ContentType.JSON);
        String token = BaseTest.TOKEN;
        requestSpecification.cookie("token",token);
        response = requestSpecification.when().log().all().patch();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
    }
    @Test(priority = 7,dependsOnMethods ="test_Get_ALl_IDS" )
    @Description( "TC#8 :- Delete Existing Booking ID   ")
    @Owner("Uvesh  Ranghar")
    public void test_Delete_Existing_Booking_ID(ITestContext context){
        requestSpecification.basePath(API_Constants.DELETE_Booking + context.getAttribute("ID") ).contentType(ContentType.TEXT);
        String token = BaseTest.TOKEN;
        requestSpecification.cookie("token",token);
        response = requestSpecification.when().log().all().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
    }



}
