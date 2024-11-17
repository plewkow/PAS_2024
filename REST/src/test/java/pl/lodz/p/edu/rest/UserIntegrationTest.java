package pl.lodz.p.edu.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserIntegrationTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/api/users";
    }

    @Test
    public void testCreateUser() {
        String userJson = """
                {
                    "login": "testowyAdmin",
                    "password": "testoweHaslo",
                    "firstName": "Adminek",
                    "lastName": "Adminowski",
                    "role": "ADMIN"
                }
                """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userJson)
                .when()
                .post("")
                .then()
                .statusCode(200) // TODO: Change statusCode
                .body("login", equalTo("test"));
    }
}
