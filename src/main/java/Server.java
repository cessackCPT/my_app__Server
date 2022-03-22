import io.javalin.Javalin;

public class Server {
    private final Javalin server;

    public Server(){
        this.server = Javalin.create();
        this.server.get("hello",
                context -> context.result("Hello, user!"));
    }

    public Javalin start(){
        return this.server.start(7000);
    }

    public Javalin stop(){
        return this.server.stop();
    }

    public static void main(String[] args) {
        Server api = new Server();
        api.start();
    }

}
