import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.fail;


public class AdminTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:7081";
        RestAssured.authentication = RestAssured.preemptive().basic("admin", "password");
    }

    @Test
    void addNewBook(){
        String requestBody = "{\n" +
                "    \"id\": 6,\n" +
                "    \"title\": \"puujawaliya\",\n" +
                "    \"author\": \"Darmaseena Himi\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/books");
        int statusCode =response.getStatusCode();
        if (statusCode == 208) {
            System.out.println("Status code (Book Already Exists): " + response.getStatusCode());
            System.out.println("Response : " + response.asString());
        }else if(statusCode == 201){
            System.out.println("Status code (Add New Book): " + statusCode);
            System.out.println("Response : " + response.asString());
        }else {
            fail("Failed to add book. " + statusCode);
        }
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
                .put("/api/books/3"); // Replace 3 with the actual book id you want to update

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            System.out.println("Status code (Update the Book): " + statusCode);
            System.out.println("Response : " + response.asString());
        } else if(statusCode == 404){
            System.out.println("Status code (Book not found): " + statusCode);
            System.out.println("Response : " + response.asString());
        }else{
            fail("Failed to update book :Status code: " + statusCode);
        }


    }


    @Test
    void deleteBook(){
        Response response = given()
                .when()
                .delete("/api/books/4"); // Replace 3 with the actual book id you want to delete

        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            System.out.println("Status code (deleted the Book): " + statusCode);
            System.out.println("Response : " + response.asString());
        }else if(statusCode == 404){
            System.out.println("Status code (Book not found): " + statusCode);
            System.out.println("Response : " + response.asString());
        } else {
            fail("Failed to delete book : Status code: " + statusCode);
        }
    }

    @Test
    void get_all_books(){
        Response response = RestAssured.get("/api/books");
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Status code (Get All Books): " + response.getStatusCode());
            System.out.println("Response : " + response.asString());
        } else {
            fail("Fail to get all books : Status code: " + statusCode);
        }
    }

    @Test
    void get_books_by_ID(){
        Response response = RestAssured.get("/api/books/2"); // Replace 2 with the actual book id you want to retrieve
        int statusCode = response.getStatusCode();
        if (statusCode == 200) {
            System.out.println("Status code (Get Book by ID): " + response.getStatusCode());
            System.out.println("Response : " + response.asString());
        } else if(statusCode == 404){
            System.out.println("Status code (Book not found): " + statusCode);
            System.out.println("Response : " + response.asString());
        }else {
            fail("Fail to get book by ID : Status code: " + statusCode);
        }
    }
}
