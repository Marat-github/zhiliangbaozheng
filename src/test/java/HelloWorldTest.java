import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HelloWorldTest {
    @Test
    public void testLengthFail() {
        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String str = response.getString("answer");
        assertTrue(str.length() > 15, "Unexpected status code!!!");
    }

    @Test
    public void testLengthPass() {
        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/api/hello")
                .jsonPath();

        String str = response.getString("answer");
        assertTrue(str.length() < 15, "Unexpected status code!!!");
    }
}
