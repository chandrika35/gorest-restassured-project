package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIT() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);

    }

    //1) Verify that total record = 25
    @Test
    public void test001() {
        response.body("per_page", equalTo(25));

    }
    //2)Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto
    //centum.”

    @Test
    public void test002() {
        response.body("find{it.id == 2730}.title", equalTo("Ad ipsa coruscus ipsam eos demitto centum."));
    }
    //3)Check the single user_id in the Array list (5522)
    @Test
    public void test003(){
        response.body("user_id",hasItem(5522));

    }
    //4)Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(2693, 2684,2681));

    }
    @Test
    public void test005(){}{
        response.body("find{it.user_id == 2205289 }.body",equalTo( "Uter utrimque bos. Ait conforto beneficium. Usitas appositus vicinus. Coniuratio advoco canis. Vel advenio autem. Sed desidero circumvenio. Vomica summa eum. Accendo thorax terebro. Ceno cohaero tripudio. Ubi angelus praesentium. Talus averto adhaero. Impedit suspendo cimentarius. Deduco condico denego."));

    }




}


