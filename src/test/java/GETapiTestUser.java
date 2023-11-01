import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class GETapiTestUser {
    AppConfig config = new AppConfig();
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = config.getBaseUrl();
        RestAssured.authentication = RestAssured.preemptive().basic(config.getUsername1(), config.getPassword1());
    }

    @Test
    void testGetAllBooks(){
        Response response = RestAssured.get("/api/books");
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, "Expected status code 200");
        System.out.println("Pass the test case - get all books");
        System.out.println("Status code (Get All Books): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void testGetAllBooksResponseIsJson(){
        Response response = RestAssured.get("/api/books");
        String contentTypeHeader = response.getContentType();
        assertTrue(contentTypeHeader.contains("application/json"), "Response is not in JSON format");
        System.out.println("Pass the test case - response is in JSON format");
        System.out.println("Content Type Header: " + contentTypeHeader);
    }

    @Test
    void testGetAllBooksResponseTime(){
        Response response = RestAssured.get("/api/books");
        long responseTime = response.getTime();
        assertTrue(responseTime < 2000, "Response time is not within acceptable range");
        System.out.println("Pass the test case - response time is within acceptable range");
        System.out.println("Response Time: " + responseTime + " milliseconds");
    }
    @Test
    void testGetExistingBookByID(){
        Response response = RestAssured.get("/api/books/2"); // Replace 2 with the actual book id
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode, "Expected status code 200");
        System.out.println("Pass the test case - get existing book by ID");
        System.out.println("Status code (Get Book by ID): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void testGetNonExistingBookByID(){
        Response response = RestAssured.get("/api/books/9999"); // Use a non-existing book id
        int statusCode = response.getStatusCode();
        assertEquals(404, statusCode, "Expected status code 404");
        System.out.println("Pass the test case - get non-existing book by ID");
        System.out.println("Status code (Get Book by ID): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void testGetBookWithInvalidIDFormat(){
        Response response = RestAssured.get("/api/books/abc"); // Use an invalid id format
        int statusCode = response.getStatusCode();
        assertEquals(400, statusCode, "Expected status code 400");
        System.out.println("Pass the test case - get book with invalid ID format");
        System.out.println("Status code (Get Book by ID): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void testGetBookWithNegativeID(){
        Response response = RestAssured.get("/api/books/-2"); // Use a negative id
        int statusCode = response.getStatusCode();
        assertEquals(400, statusCode, "Expected status code 400");
        System.out.println("Pass the test case - get book with negative ID");
        System.out.println("Status code (Get Book by ID): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void testGetBookWithZeroID(){
        Response response = RestAssured.get("/api/books/0"); // Use zero as the id
        int statusCode = response.getStatusCode();
        assertEquals(400, statusCode, "Expected status code 400");
        System.out.println("Pass the test case - get book with zero ID");
        System.out.println("Status code (Get Book by ID): " + statusCode);
        System.out.println("Response : " + response.asString());
    }


}
