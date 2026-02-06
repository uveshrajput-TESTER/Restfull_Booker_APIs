package org.thetestingAcademy.Base;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.thetestingAcademy.Endpoints.API_Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.thetestingAcademy.Endpoints.API_Constants;
import org.thetestingAcademy.Manager.Payload_Manager;
import org.thetestingAcademy.Pojos.ResponsePojo.BookingResponse;
import org.thetestingAcademy.Pojos.ResponsePojo.TokenResponse;

import static groovyjarjarantlr4.v4.tool.AttributeDict.DictType.TOKEN;

//In this we Already Import All the things
public class BaseTest extends API_Constants {
    // CommonToAll Testcase
    //   // Base URL, Content Type - json - common
    protected static String TOKEN;
    protected  static Integer id ;
    @BeforeSuite
    public void generateTokenOnce() {

        RequestSpecification req = RestAssured
                .given()
                .baseUri(API_Constants.BASE_URL)
                .contentType(ContentType.JSON)
                .body(Payload_Manager.setAuthPayload());

        Response res = req.post(API_Constants.CREATE_Token);

        TokenResponse tokenResponse =
                Payload_Manager.AuthresponseJava(res.asString());

        TOKEN = tokenResponse.getToken();

        System.out.println("TOKEN generated in @BeforeSuite = " + TOKEN);
    }
    @BeforeSuite
    public void getbookingIDOnce() {

        RequestSpecification req = RestAssured
                .given()
                .baseUri(API_Constants.BASE_URL).basePath(API_Constants.CREATE_GET_ALL_Booking).body(Payload_Manager.Create_Booking())
                .contentType(ContentType.JSON);

        Response res = req.post();

        BookingResponse bookingReasponse = Payload_Manager.getNewBooking(res.asString());
          id =  bookingReasponse.getBookingid();
    }

    public RequestSpecification requestSpecification;
    public org.thetestingAcademy.Asserts.AssertActions assertActions;
    public Payload_Manager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;
    @BeforeMethod
    public void setup() {
        // We need to setup the base URL. We need to setup the header.
        System.out.println("Starting of the Test");
        payloadManager = new Payload_Manager();
        assertActions = new org.thetestingAcademy.Asserts.AssertActions();

        requestSpecification = RestAssured.given().header("Content-Type", "application/json");
        requestSpecification.baseUri(API_Constants.BASE_URL);


    }
//
}
