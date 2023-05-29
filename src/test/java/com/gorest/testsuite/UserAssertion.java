package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class UserAssertion {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);

    }

    // 1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("total.size", equalTo(20));

    }
    @Test
    public void test002(){
        response.body("[0].name", equalTo("Sukanya Dubashi"));

    }

    @Test
    public void test003() {
        response.body("name", hasItem("Msgr. Harinarayan Kaur"));
    }
    @Test
    public void test005() {
        response.body("find{it.id == 2178500}.gender",equalTo("male"));
    }
    @Test
    public void test006() {
        response.body("find{it.name == 'Shashi Khatri'}.status",equalTo("active"));
    }
    @Test
    public void test007(){
        response.body("find{it.id == 2191531}.gender",equalTo("female"));

    }
}