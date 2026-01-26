package org.thetestingAcademy.Asserts;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;
import static org.testng.Assert.assertTrue;
public class AssertActions {

    public void verifyResponseBody(String actual, String expected, String description){
        assertEquals(actual,expected,description);
    }
    public void verifyResponseBody(int actual, int expected, String description) {
        assertEquals(actual, expected, description);
    }

    public void verifyStatusCode(Response response, int expected) {

        assertEquals(response.getStatusCode(),expected);
    }

    public void verifyStringKey_NotnullblankisequalTo(String keyExpect, String keyActual){
        // AssertJ
        assertThat(keyExpect).isNotNull();
        assertThat(keyExpect).isNotBlank();
        assertThat(keyExpect).isEqualTo(keyActual);

    }

    public void verifyIntegerKeyNotNull(Integer keyExpect){
        // AssertJ
        assertThat(keyExpect).isNotNull();
    }
    public void verifyStringKeyNotNull(String keyExpect){
        // AssertJ
        assertThat(keyExpect).isNotNull();
    }
    public void verifyStringKeyNotBlank(String keyExpect){
        // AssertJ
        assertThat(keyExpect).isNotBlank();
    }
    public void verifyTrue(boolean keyExpect){
        // AssertJ
        assertTrue(keyExpect);
    }

}
