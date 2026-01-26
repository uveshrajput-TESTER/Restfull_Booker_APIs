package org.thetestingAcademy.Base;
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

//In this we Already Import All the things
public class BaseTest {
    // CommonToAll Testcase
    //   // Base URL, Content Type - json - common

    public RequestSpecification requestSpecification;
    public org.thetestingAcademy.Asserts.AssertActions assertActions;
    public Payload_Manager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;
    @BeforeTest
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
