package org.thetestingAcademy.Tests.CRUD;

import groovyjarjarantlr4.v4.codegen.model.chunk.TokenRef;
import org.thetestingAcademy.Base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import org.thetestingAcademy.Endpoints.API_Constants;
import org.thetestingAcademy.Manager.Payload_Manager;
import org.thetestingAcademy.Pojos.ResponsePojo.TokenResponse;

public class Create_Token extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Create_Token.class);

    @Test
    @Description( "TC#1 :- Check That with Valid Username And Password Token generate Successfully ")
    @Owner("Uvesh  Ranghar")
    public void test_Generate_token(){
        logger.info("Test Case Starting ");
        requestSpecification.basePath(API_Constants.CREATE_Token);

        response = RestAssured.given(requestSpecification).body(Payload_Manager.setAuthPayload()).when().log().all().post();
        System.out.println(response.asString());
        TokenResponse tokenResponse = Payload_Manager.AuthresponseJava(response.asString());
        logger.info("Assertion Starting ");
        assertActions.verifyStatusCode(response ,200);
        assertActions.verifyStringKeyNotNull(tokenResponse.getToken());
        assertActions.verifyStringKeyNotBlank(tokenResponse.getToken());

    }

}
