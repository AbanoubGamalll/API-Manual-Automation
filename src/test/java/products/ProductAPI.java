package products;

import io.restassured.response.Response;
import org.example.Data;
import org.example.model.productModel;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class ProductAPI {
    private final String ProductEndPoint = "/products/";
    private productModel product = new productModel(0, "My title is tht best title",
            109.95f, "My description is tht best description 2",
            "men's clothing", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png");

    @Test
    private void getALlProducts() {
        Response res = given()
                .spec(Data.requestSpecification_ForGet)
                .when()
                .get(ProductEndPoint)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("", instanceOf(List.class))
                .extract().response();
        System.out.println(res.path("").toString());
        res.prettyPrint();
    }

    @Test
    private void getProduct() {
        int id = 1;
        Response res = given()
                .spec(Data.requestSpecification_ForGet)
                .when()
                .get(ProductEndPoint + id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("id", equalTo(id))
                .assertThat().body(
                        matchesJsonSchemaInClasspath("product.json"))
                .extract().response();

        res.prettyPrint();
    }

    @Test
    private void addProduct() {
        Response res = given()
                .spec(Data.requestSpecification_ForPost)
                .body(product)
                .when()
                .post(ProductEndPoint)
                .then()
                .assertThat().statusCode(201)
                .assertThat().body(
                        "title", equalTo(product.getTitle())
                        , "description", equalTo(product.getDescription())
                        , "price", equalTo(product.getPrice())
                        , "image", equalTo(product.getImage())
                        , "category", equalTo(product.getCategory()))
                .extract().response();

        product.setId(res.path("id"));

        res.prettyPrint();
    }

    @Test
    private void updateProduct() {
        product.setDescription("Updated Description");
        product.setPrice(155.123f);

        Response res = given()
                .spec(Data.requestSpecification_ForPost)
                .body(product)
                .when()
                .put(ProductEndPoint + product.getId())
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(
                        "id", equalTo(product.getId())
                        , "title", equalTo(product.getTitle())
                        , "description", equalTo(product.getDescription())
                        , "price", equalTo(product.getPrice())
                        , "image", equalTo(product.getImage())
                        , "category", equalTo(product.getCategory()))
                .extract().response();

        res.prettyPrint();
    }

    @Test
    private void deleteProduct() {
        Response res = given()
                .spec(Data.requestSpecification_ForPost)
                .body(product)
                .when()
                .delete(ProductEndPoint + product.getId())
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(equalTo(""))
                .extract().response();

        res.prettyPrint();
    }
}
