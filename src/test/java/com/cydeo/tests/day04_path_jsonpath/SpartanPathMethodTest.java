package com.cydeo.tests.day04_path_jsonpath;

import com.cydeo.utils.SpartanTestBase;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanPathMethodTest extends SpartanTestBase {
/**
 * Given accept is json
 * And path param id is 13
 * When I send get request to /api/spartans/{id}
 * ----------
 * Then status code is 200
 * And content type is json
 * And id value is 13
 * And name is "Jaimie"
 * And gender is "Female"
 * And phone is "7842554879"
 */
    @DisplayName("GET /spartan/{id} and path()")
    @Test
    public void readSpartanJsonUsingPathTest() {
       Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 13)
                .when().get("/spartans/{id}");

        /**
         {
         "id": 13,
         "name": "Jaimie",
         "gender": "Female",
         "phone": 7842554879
         }
         */
        System.out.println("id = " + response.path("id"));
        System.out.println("name = " + response.path("name"));
        System.out.println("gender = " + response.path("gender"));
        System.out.println("phone = " + response.path("phone"));

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        int spartanId = response.path("id");
        assertEquals(13, spartanId);
        assertEquals("Jaimie", response.path("name"));
        assertEquals("Female",  response.path("gender"));
        assertEquals(7842554879L, (long)response.path("phone"));
        //assertEquals(7842554879L, Long.valueOf(""+response.path("phone"))); tricky

    }

}
