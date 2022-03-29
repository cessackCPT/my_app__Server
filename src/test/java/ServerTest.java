import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
    @Test
    @DisplayName("GET /hello")
    public void shouldGetHelloUser(){
        Server api = new Server();
        api.start(7000);
        HttpResponse<String> response
                = Unirest.get("http://localhost:7000/hello").asString();
        assertEquals(200, response.getStatus());
        assertEquals("Hello, user!", response.getBody());
        api.stop();
    }
}
