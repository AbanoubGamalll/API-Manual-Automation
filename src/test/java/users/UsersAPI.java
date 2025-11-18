package users;

import io.restassured.response.Response;
import org.example.Data;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class UsersAPI {
    @Test
    private void getAllUsers() {
        Response res = given().spec(Data.requestSpecification_ForGet)
                .when()
                .get("/users")
                .then()
                .assertThat().statusCode(200)
                .body("", instanceOf(List.class))
                .extract().response();

        res.prettyPrint();
    }
}