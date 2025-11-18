package org.example;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Data {
    public static final String Base_URL = "https://fakestoreapi.com";
    public static final String username = "johnd";
    public static final String password = "m38rmF$";

    public static RequestSpecification requestSpecification_ForGet = given().baseUri(Base_URL);
    public static RequestSpecification requestSpecification_ForPost = given().baseUri(Base_URL).contentType(ContentType.JSON);

}
