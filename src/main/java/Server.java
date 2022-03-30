import io.javalin.Javalin;

public class Server {
    private final Javalin server;

    public Server(){
        this.server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
        });
        //server mostly tells you which http requests you can make that the api will allow
        this.server.get("/users", context -> UserApiHandler.getAll(context));
        this.server.get("/user/{id}", context -> UserApiHandler.getOne(context));
        this.server.post("/user_add", context -> UserApiHandler.create(context));

    }

    public Javalin start(int port){
        return this.server.start(port);
    }

    public Javalin stop(){
        return this.server.stop();
    }

    public static void main(String[] args) {
        Server api = new Server();
        api.start(5000);
    }

}
