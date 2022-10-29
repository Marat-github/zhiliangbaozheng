import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HelloWorldTest {
    @Test
    public void testCookie() {
        Response response = RestAssured
                .get("https://playground.learnqa.ru/api/homework_cookie")
                .andReturn();

        Map<String, String> cookies = response.getCookies();
        System.out.println(cookies);

        assertEquals("hw_value", response.getCookie("HomeWork"), "Unexpected cookie");

    }
}
