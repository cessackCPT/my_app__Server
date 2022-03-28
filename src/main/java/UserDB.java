import java.util.List;

public interface UserDB {
    //getting a single user by id
    User get(Integer id);

    //get all the users in the database
    List<User> all();

    //add a new individual user to the database
    User add(User user);
}
