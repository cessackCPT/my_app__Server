import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class UserApiHandler {
    private static final UserDB database = new TestDatabase();

    //get all the users
    public static void getAll(Context context){
        context.json(database.all());
    }

    //get one user
    public static void getOne(Context context){
        Integer id = context.pathParamAsClass("id", Integer.class).get();
        User user = database.get(id);
        context.json(user);
    }

    //create a new user
    public static void create(Context context){
        User user = context.bodyAsClass(User.class);
        User newUser = database.add(user);
        context.header("Location", "/user/" + newUser.getId());
        context.status(HttpCode.CREATED);
        context.json(newUser);
    }
}
