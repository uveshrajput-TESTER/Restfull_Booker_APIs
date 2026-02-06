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
import org.thetestingAcademy.Pojos.ResponsePojo.BookingResponse;
import org.thetestingAcademy.Pojos.ResponsePojo.TokenResponse;

public class Negative_TestCases extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Negative_TestCases.class);


    @Test(priority = 0)
    @Description( "TC#1 :- Run Ping Request Check Server is live or not")
    @Owner("Uvesh Rajput ")
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
    @Description( "TC#2 :- Try to Create a Token with wrong Credentials ")
    @Owner("Uvesh  Ranghar")
    public void test_Generate_token_with_WrongCredentials(ITestContext context){
        logger.info("Test Case Starting ");
        requestSpecification.basePath(API_Constants.CREATE_Token);
        response = RestAssured.given(requestSpecification).body(Payload_Manager.setWrongAuthPayload()).when().log().all().post();
        System.out.println(response.asString());
        TokenResponse tokenResponse = Payload_Manager.AuthresponseJava(response.asString());
        logger.info("Assertion Starting ");
        assertActions.verifyStatusCode(response ,200);
        assertActions.verifyStringKeyNotNull(tokenResponse.getReason());
        assertActions.verifyStringKeyNotBlank(tokenResponse.getReason());
        assertActions.verifyResponseBody(tokenResponse.getReason(),"Bad credentials","Error message mismatch");
    }
    @Test(priority = 2)
    @Description( "TC#3 :- Try to Create a Token Without any payload")
    @Owner("Uvesh  Ranghar")
    public void test_Generate_token_with_emty_Body(ITestContext context){
        logger.info("Test Case Starting ");
        requestSpecification.basePath(API_Constants.CREATE_Token);
        response = RestAssured.given(requestSpecification).when().log().all().post();
        System.out.println(response.asString());
        TokenResponse tokenResponse = Payload_Manager.AuthresponseJava(response.asString());
        logger.info("Assertion Starting ");
        assertActions.verifyStatusCode(response ,200);
        assertActions.verifyStringKeyNotNull(tokenResponse.getReason());
        assertActions.verifyStringKeyNotBlank(tokenResponse.getReason());
        assertActions.verifyResponseBody(tokenResponse.getReason(),"Bad credentials","Error message mismatch");
    }
    @Test(priority = 3)
    @Description( "TC#4 :-  Try to Create Booking ID with Wrong payload ")
    @Owner("Uvesh  Ranghar")
    public void test_Create_new_Booking(){
        requestSpecification.basePath(API_Constants.CREATE_GET_ALL_Booking).body(Payload_Manager.Create_Booking_with_Wrong_payload());
        response = requestSpecification.when().log().all().post();
        System.out.println(response.asString());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);
        assertActions.verifyStringKeyNotNull(response.asString());
        assertActions.verifyResponseBody(response.asString(),"Internal Server Error","Internal server Error");
    }
    @Test(priority = 4 )
    @Description( "TC#5 :- Full Update Non Existing Booking ID  ")
    @Owner("Uvesh  Ranghar")
    public void test_Full_Update_NonExisting_BookingIDs(@NonNull ITestContext context){
        requestSpecification.basePath(API_Constants.FULL_Update_Booking + 12432545 ).body(Payload_Manager.FUll_Update_Booking());
        requestSpecification.cookie("token",BaseTest.TOKEN);
        response = requestSpecification.when().log().all().put();
        System.out.println(response.asString());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(405);
        assertActions.verifyResponseBody(response.asString(),"Method Not Allowed","Id should not be Updated ");
    }
    @Test(priority = 5 )
    @Description( "TC#6 :- Full Update  Existing Booking ID  with Wrong payload ")
    @Owner("Uvesh  Ranghar")
    public void test_Full_Update_Existing_BookingID_with_WrongPayload(@NonNull ITestContext context){
        requestSpecification.basePath(API_Constants.FULL_Update_Booking + BaseTest.id  ).body(Payload_Manager.FUll_Update_Booking_Wrong_Payload());
        requestSpecification.cookie("token",BaseTest.TOKEN);
        response = requestSpecification.when().log().all().put();
        System.out.println(response.asString());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(400);
        assertActions.verifyResponseBody(response.asString(),"Bad Request","Id should not be Updated ");
    }
    @Test(priority = 6 )
    @Description( "TC#7 :- Full Update  Existing Booking ID  without Authentication ")
    @Owner("Uvesh  Ranghar")
    public void test_Full_Update_Existing_BookingID_withoutAuthentication(@NonNull ITestContext context){
        requestSpecification.basePath(API_Constants.FULL_Update_Booking + BaseTest.id  ).body(Payload_Manager.FUll_Update_Booking_Wrong_Payload());
        response = requestSpecification.when().log().all().put();
        System.out.println(response.asString());
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(403);
        assertActions.verifyResponseBody(response.asString(),"Forbidden","Id should not be Updated ");
    }
    @Test(priority = 7 )
    @Description( "TC#8 :- Partially Update Booking ID which is not exist  ")
    @Owner("Uvesh  Ranghar")
    public void test_Partially_Update_NonExisting_Bookingid(ITestContext context){
        requestSpecification.basePath(API_Constants.FULL_Update_Booking + 5666767).body(Payload_Manager.Partially_Update_Booking()).contentType(ContentType.JSON);
        String token = BaseTest.TOKEN;
        requestSpecification.cookie("token",token);
        response = requestSpecification.when().log().all().patch();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(405);
        assertActions.verifyResponseBody(response.asString(),"Method Not Allowed","should not be Updated ");
    }
    @Test(priority = 8 )
    @Description( "TC#9 :- Partially Update Booking ID with Wrong Payload  ")
    @Owner("Uvesh  Ranghar")
    public void test_Partially_Update_Booking_IDs(ITestContext context){
        requestSpecification.basePath(API_Constants.FULL_Update_Booking + BaseTest.id).body(Payload_Manager.Partially_Update_Booking_with_wrongpayload()).contentType(ContentType.JSON);
        String token = BaseTest.TOKEN;
        requestSpecification.cookie("token",token);
        response = requestSpecification.when().log().all().put();
        validatableResponse = response.then().log().all();
        System.out.println(response.asString());
        validatableResponse.statusCode(500);
        assertActions.verifyResponseBody(response.asString(),"Internal Server Error","should not be Updated ");
    }
    @Test(priority = 9 )
    @Description( "TC#10 :- Delete Non Existing Booking ID   ")
    @Owner("Uvesh  Ranghar")
    public void test_Delete_NonExisting_Booking_ID(ITestContext context){
        requestSpecification.basePath(API_Constants.DELETE_Booking + 43346565 ).contentType(ContentType.TEXT);
        String token = BaseTest.TOKEN;
        requestSpecification.cookie("token",token);
        response = requestSpecification.when().log().all().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(405);
        assertActions.verifyResponseBody(response.asString(),"Method Not Allowed","Id should not be Delete ");
    }
    @Test(priority = 10 )
    @Description( "TC#11 :- Delete  Existing Booking ID without any Credentials    ")
    @Owner("Uvesh  Ranghar")
    public void test_Delete_Existing_Booking_ID_WithoutCredentials(ITestContext context){
        requestSpecification.basePath(API_Constants.DELETE_Booking + BaseTest.id ).contentType(ContentType.TEXT);
        response = requestSpecification.when().log().all().delete();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(403);
        assertActions.verifyResponseBody(response.asString(),"Forbidden","Id should not be Delete ");
    }

}
