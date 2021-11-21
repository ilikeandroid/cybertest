import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class RestApiTest {

    private static String validCreateBody = "{\n" +
            "  \"name\": \"tester1\",\n" +
            "  \"job\": \"scenior tester\" \n}";

    private static String invalidCreateBody = "{\n" +
            "  \"name-wrong\": \"tester1\",\n" +
            "  \"job\": \"scenior tester\" \n}";

    private static String validUpdateBody = "{\n" +
            "  \"name\": \"tester1\",\n" +
            "  \"job\": \"junior tester\" \n}";

    private static String invalidUpdateBody = "{\n" +
            "  \"name\": \"tester1\",\n" +
            "  \"job-wrong\": \"junior tester\" \n}";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    public void GetSingleUserTest() {
        when().
                get("/api/users/{id}", 2).
                then().
                statusCode(200).
                body("data.email", equalTo("janet.weaver@reqres.in"));
    }

    @Test
    public void CreateUserTest() {

        given()
                .header("Content-type", "application/json")
                .and()
                .body(validCreateBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("tester1")).body("job", equalTo("scenior tester"));

    }

    @Test
    public void CreateUserTestWithInvalidParameter() {

        given()
                .header("Content-type", "application/json")
                .and()
                .body(invalidCreateBody)
                .when()
                .post("/api/users")
                .then()
                .statusCode(400);
    }

    @Test
    public void UpdateUserTest() {

        given()
                .header("Content-type", "application/json")
                .and()
                .body(validUpdateBody)
                .when()
                .put("/api/users")
                .then()
                .statusCode(200)
                .body("name", equalTo("tester1")).body("job", equalTo("junior tester"));
    }

    @Test
    public void UpdateUserTestWithInvalidParameter() {

        given()
                .header("Content-type", "application/json")
                .and()
                .body(invalidUpdateBody)
                .when()
                .put("/api/users")
                .then()
                .statusCode(400);
    }

    @Test
    public void DeleteUserTest() {
        when().
                delete("/api/users/{id}", 7).
                then().
                statusCode(204);
    }

}
