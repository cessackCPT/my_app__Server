import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserApiTests {
    private static Server server;

    @BeforeAll
    public static void startServer(){
        server = new Server();
        server.start(5000);
    }

    @AfterAll
    public static void stopServer(){
        server.stop();
    }

    //testing getting a specific user by id
    @Test
    @DisplayName("GET /user/{id}")
    public void getOneUser() throws UnirestException{
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/user/1").asJson();
        assertEquals(200, response.getStatus());

        JSONObject jsonObject = response.getBody().getObject();
        assertEquals("Bob", jsonObject.get("username"));
        assertEquals("pass123", jsonObject.get("password"));
    }

    //testing getting all the users
    @Test
    @DisplayName("GET /user")
    void getAllUsers() throws UnirestException{
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/users").asJson();
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));


        JSONArray jsonArray = response.getBody().getArray();
        assertTrue(jsonArray.length() > 1);
    }

    //testing creating a new user
    @Test
    @DisplayName("POST /user_add")
    void create() throws UnirestException{
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/user_add")
                .header("Content-Type", "application/json")
                .body(User.create("Dave", "capel_game"))
                .asJson();
        assertEquals(201, response.getStatus());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
//        assertEquals("/user/2", response.getHeaders().getFirst("Location"));
        assertEquals("Dave", response.getBody().getObject().get("username"));

        response = Unirest.get("http://localhost:5000/user/2").asJson();
        System.out.println(response.getBody());
        assertEquals(200, response.getStatus());
    }


}
