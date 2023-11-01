import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class POSTapi {

    AppConfig config = new AppConfig();
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = config.getBaseUrl();
        RestAssured.authentication = RestAssured.preemptive().basic(config.getUsername1(), config.getPassword1());
    }


    @Test
    void addNewBook(){
        String requestBody = "{\n" +
                "    \"id\": 6,\n" +
                "    \"title\": \"heensaraya\",\n" +
                "    \"author\": \" k.munidasa\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/books");
        int statusCode =response.getStatusCode();
        assertEquals(201, statusCode, "Expected status code 201");
        System.out.println("Pass the test case - add book with special characters in title");
        System.out.println("Status code (Add Book with Special Characters in Title): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void addexistingBook(){
        String requestBody = "{\n" +
                "    \"id\": 6,\n" +
                "    \"title\": \"jhjjhjh\",\n" +
                "    \"author\": \"Darmaseena Himi\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/books");
        int statusCode =response.getStatusCode();
        assertEquals(208, statusCode, "Expected status code 201");
        System.out.println("Pass the test case - add book with special characters in title");
        System.out.println("Status code (Add Book with Special Characters in Title): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

    @Test
    void testAddBookWithEmptyTitle(){
        String requestBody = "{\n" +
                "    \"id\": 8,\n" +
                "    \"author\": \"p.b awis\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/books");
        int statusCode = response.getStatusCode();
        assertEquals(400, statusCode, "Expected status code 400 (Bad Request)");
        System.out.println("Pass the test case - add book with empty title");
        System.out.println("Status code (Add Book with Empty Title): " + statusCode);
        System.out.println("Response : " + response.asString());
    }


    @Test
    void testAddBookWithEmptyAuthor(){
        String requestBody = "{\n" +
                "    \"id\": 9,\n" +
                "    \"title\": \"hath pana\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/books");
        int statusCode = response.getStatusCode();
        assertEquals(400, statusCode, "Expected status code 400 (Bad Request)");
        System.out.println("Pass the test case - add book with empty author");
        System.out.println("Status code (Add Book with Empty Author): " + statusCode);
        System.out.println("Response : " + response.asString());
    }


    @Test
    void testAddBookWithSpecialCharactersInTitle(){
        String requestBody = "{\n" +
                "    \"id\": 10,\n" +
                "    \"title\": \"@#$$%%^$^^%#%$#\",\n" +
                "    \"author\": \"$#$@$#@#$@\"\n" +
                "}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/books");
        int statusCode = response.getStatusCode();
        assertEquals(400, statusCode, "Expected status code 201");
        System.out.println("Pass the test case - add book with special characters in title");
        System.out.println("Status code (Add Book with Special Characters in Title): " + statusCode);
        System.out.println("Response : " + response.asString());
    }
    @Test
    void testAddBookWithUnicodeCharactersInAuthor(){
        String requestBody = "{\n" +
                "    \"id\": 11,\n" +
                "    \"title\": \"unicode\",\n" +
                "    \"author\": \"සාගර පලන්සූරිය\"\n" + //සාගර පලන්සූරිය ධර්මසේන හිමි
                "}";

        Response response = given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/books");
        int statusCode = response.getStatusCode();
        assertEquals(201, statusCode, "Expected status code 201");
        System.out.println("Pass the test case - add book with unicode characters in author");
        System.out.println("Status code (Add Book with Unicode Characters in Author): " + statusCode);
        System.out.println("Response : " + response.asString());
    }

}
