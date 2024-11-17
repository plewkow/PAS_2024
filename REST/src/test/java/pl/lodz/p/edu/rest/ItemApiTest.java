//package pl.lodz.p.edu.rest;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.bson.types.ObjectId;
//
//
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//
//public class ItemApiTest {
//    private static final String BASE_URL = "http://localhost:8080";
//
//    @BeforeAll
//    static void setUp() {
//        RestAssured.baseURI = BASE_URL;
//    }
//
//    @Test
//    void addMusicTest() {
//        String musicJson = "{ \"itemName\": \"Album\", \"basePrice\": 100, \"genre\": \"Metal\", \"available\": true }";
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(musicJson)
//                .when()
//                .post("/manager/music")
//                .then()
//                .statusCode(201)
//                .body("itemName", equalTo("Album"))
//                .body("basePrice", equalTo(100))
//                .body("genre", equalTo("Metal"))
//                .body("available", equalTo(true));
//    }
//}
