import org.example.Data;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CheckServer {
    @Test
    private void ping() {
        given()
                .spec(Data.requestSpecification_ForGet)
        .when()
                .get()
        .then()
                .assertThat().statusCode(200);
    }
}
