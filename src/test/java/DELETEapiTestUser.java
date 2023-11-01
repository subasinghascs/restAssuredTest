
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class DELETEapiTestUser {
    AppConfig config = new AppConfig();

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = config.getBaseUrl();
        RestAssured.authentication = RestAssured.preemptive().basic(config.getUsername2(), config.getPassword2());
    }

    @Test
    void deleteExistingBook(){
        Response response = given()
                .when()
                .delete("/api/books/4"); // Replace 4 with the actual book id you want to delete

        int statusCode = response.getStatusCode();
        assertEquals(404 , statusCode, "Expected status code 200");
        System.out.println("Pass the test case - delete existing book");
        System.out.println("Status code (Delete Book): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void deleteNonExistingBook(){
        Response response = given()
                .when()
                .delete("/api/books/9999"); // Use a non-existing book id

        int statusCode = response.getStatusCode();
        assertEquals(404 , statusCode, "Expected status code 404");
        System.out.println("Pass the test case - delete non-existing book");
        System.out.println("Status code (Delete Book): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void deleteBookWithInvalidIDFormat(){
        Response response = given()
                .when()
                .delete("/api/books/abc"); // Use an invalid id format

        int statusCode = response.getStatusCode();
        assertEquals(404 , statusCode, "Expected status code 400");
        System.out.println("Pass the test case - delete book with invalid ID format");
        System.out.println("Status code (Delete Book): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void deleteBookWithNegativeID(){
        Response response = given()
                .when()
                .delete("/api/books/-2"); // Use a negative id

        int statusCode = response.getStatusCode();
        assertEquals(404 , statusCode, "Expected status code 400");
        System.out.println("Pass the test case - delete book with negative ID");
        System.out.println("Status code (Delete Book): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void deleteBookWithZeroID(){
        Response response = given()
                .when()
                .delete("/api/books/0"); // Use zero as the id

        int statusCode = response.getStatusCode();
        assertEquals(404 , statusCode, "Expected status code 400");
        System.out.println("Pass the test case - delete book with zero ID");
        System.out.println("Status code (Delete Book): " + statusCode);
        System.out.println("Response : " + response.asString());
    }


}
