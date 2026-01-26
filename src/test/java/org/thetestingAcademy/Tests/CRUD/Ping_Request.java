package org.thetestingAcademy.Tests.CRUD;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.thetestingAcademy.Base.BaseTest;
import org.thetestingAcademy.Endpoints.API_Constants;

public class Ping_Request extends BaseTest {

    private static final Logger logger = LogManager.getLogger(Ping_Request.class);


    @Test
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




}
