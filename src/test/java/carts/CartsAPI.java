package carts;

import io.restassured.response.Response;
import org.example.Data;
import org.example.model.cartModel;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CartsAPI {
    private final String cardEndPoint = "/carts/";
    private int id = 1;
    cartModel cart;

    public CartsAPI() {
        cart = new cartModel(0, 0);
        cart.addProduct(5, 15);
        cart.addProduct(8, 2);
    }

    @Test
    private void getAllCarts() {
        Response res = given().spec(Data.requestSpecification_ForGet)
                .when()
                .get(cardEndPoint)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("", instanceOf(List.class))
                .extract().response();

        res.prettyPrint();
    }

    @Test
    private void getCart() {
        Response res = given().spec(Data.requestSpecification_ForGet)
                .when()
                .get(cardEndPoint + id)
                .then()
                .assertThat().statusCode(200)
                .assertThat().body(matchesJsonSchemaInClasspath("cart.json"))
                .extract().response();

        res.prettyPrint();
    }

    @Test
    private void addNewCart() {
        Response res = given().spec(Data.requestSpecification_ForPost)
                .body(cart)
                .when()
                .post(cardEndPoint)
                .then()
                .assertThat().statusCode(201)
                .assertThat().body("userId", equalTo(cart.getUserId()),
                        "products[0].productId", equalTo(cart.getProducts().get(0).getProductId()),
                        "products[0].quantity", equalTo(cart.getProducts().get(0).getQuantity()),
                        "products[1].productId", equalTo(cart.getProducts().get(1).getProductId()),
                        "products[1].quantity", equalTo(cart.getProducts().get(1).getQuantity())
                )
                .extract().response();
        cart.setId(res.path("id"));

        res.prettyPrint();
    }

    @Test
    private void updateCart() {

        cart.addProduct(11, 15);
        List<cartModel.userProduct> list = cart.getProducts();
        list.get(0).setProductId(5);
        list.get(1).setQuantity(17);

        Response res = given().spec(Data.requestSpecification_ForPost)
                .body(cart)
                .when()
                .put(cardEndPoint + cart.getId())
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("userId", equalTo(cart.getUserId()),
                        "products[0].productId", equalTo(cart.getProducts().get(0).getProductId()),
                        "products[0].quantity", equalTo(cart.getProducts().get(0).getQuantity()),
                        "products[1].productId", equalTo(cart.getProducts().get(1).getProductId()),
                        "products[1].quantity", equalTo(cart.getProducts().get(1).getQuantity()),
                        "products[2].productId", equalTo(cart.getProducts().get(2).getProductId()),
                        "products[2].quantity", equalTo(cart.getProducts().get(2).getQuantity())
                )
                .extract().response();

        res.prettyPrint();
    }

    @Test
    private void deleteCart() {
        Response res = given().spec(Data.requestSpecification_ForGet)
                .when()
                .delete(cardEndPoint + "1")
                .then()
                .assertThat().statusCode(200)
                .extract().response();

        res.prettyPrint();
    }

}










