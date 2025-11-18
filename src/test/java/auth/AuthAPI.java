package auth;

import io.restassured.response.Response;
import org.example.model.AuthModel;
import org.example.Data;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthAPI {
    @Test
    private void getToken() {
        AuthModel authModel = new AuthModel(Data.username, Data.password);
        Response res = given()
                .spec(Data.requestSpecification_ForPost)
                .body(authModel)
        .when()
                .post("/auth/login")
        .then()
                .assertThat().statusCode(201)
                .assertThat().body("token",not(empty()))
                .extract().response();

        String token = res.getBody().path("token").toString();

        System.out.println("Token is: "+ token);

    }
}
