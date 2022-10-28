import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class HelloWorldTest {

    @Test
    public void testHelloWorld() throws InterruptedException {

        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String token = response.get("token");
        int time = response.get("seconds");

        //System.out.println(time);
        System.out.println("====================================================");
        //System.out.println(token);
        //response.prettyPrint();

        response = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        String status = response.get("status");

        if(status.equals("Job is NOT ready")){
            System.out.println("Status is: \"Job is NOT ready\"");
        }

        System.out.println("====================================================");

        Thread.sleep(time*1000);
        response = RestAssured
                .given()
                .queryParam("token", token)
                .get("https://playground.learnqa.ru/ajax/api/longtime_job")
                .jsonPath();

        status = response.get("status");

        if(status.equals("Job is ready")){
            System.out.println("Status is: \"Job is ready\"");
        }
        System.out.println("====================================================");
        //response.prettyPrint();
    }
}
