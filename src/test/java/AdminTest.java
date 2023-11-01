import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;


public class AdminTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:7081";
        RestAssured.authentication = RestAssured.preemptive().basic("admin", "password");
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
    void addNewBook(){
        String requestBody = "{\n" +
                "    \"id\": 6,\n" +
                "    \"title\": \"saddarmaalakaraya\",\n" +
                "    \"author\": \"No \"\n" +
                "}";

        Response response = given()
            .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/books");
        int statusCode =response.getStatusCode();
        if (statusCode == 208 || statusCode == 201) {
            System.out.println("Pass the test case-add book");
        }else {
            fail("Failed to add book. " + statusCode);
            System.out.println("Fail the test case-add book");
        }
        System.out.println("Status code (Add Book): " + response.getStatusCode());
        System.out.println("Response : " + response.asString());

    }


    @Test
    void updateBook(){
        String requestBody = "{\n" +
                "    \"id\": 2,\n" +
                "    \"title\": \"wawluwa\",\n" +
                "    \"author\": \"G.H. Perera\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/api/books/2");
        int statusCode = response.getStatusCode();
        System.out.println("Status code (Update the Book): " + statusCode);
        System.out.println("Response : " + response.asString());
        if (statusCode == 200 || statusCode == 404 || statusCode == 208) {
            System.out.println("Pass the test case-update book");
        }else {
            fail("Failed to Update book. " + statusCode);
            System.out.println("Fail the test case-update book");
        }
    }


    @Test
    void deleteBook(){
        Response response = given()
                .when()
                .delete("/api/books/4"); // Replace 3 with the actual book id you want to delete

        int statusCode = response.getStatusCode();
        System.out.println("Status code (deleted the Book): " + statusCode);
        System.out.println("Response : " + response.asString());
        if (statusCode == 200 || statusCode == 404) {
            System.out.println("Pass the test case-delete book");
        }else {
            System.out.println("Fail the test case-delete book");
            fail("Failed to Update book. " + statusCode);
        }

    }

    @Test
    void get_all_books(){
        Response response = RestAssured.get("/api/books");
        int statusCode = response.getStatusCode();
        System.out.println("Status code (Get All Books): " + response.getStatusCode());
        System.out.println("Response : " + response.asString());
        if (statusCode == 200) {
            System.out.println("Pass the test case-get all books ");
        } else {
            System.out.println("Fail the test case-get all books ");
            fail("Fail to get all books : Status code: " + statusCode);
        }
    }

    @Test
    void get_books_by_ID(){
        Response response = RestAssured.get("/api/books/2"); // Replace 2 with the actual book id you want to retrieve
        int statusCode = response.getStatusCode();
        System.out.println("Status code (Get Book by ID): " + response.getStatusCode());
        System.out.println("Response : " + response.asString());
        if (statusCode == 200 || statusCode == 404) {
            System.out.println("Pass the test case-get book by ID");
        }else {
            System.out.println("Fail the test case-get book by ID");
            fail("Failed to Update book. " + statusCode);
        }
    }
}
