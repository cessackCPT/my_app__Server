import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDatabase implements UserDB {
    private Map<Integer, User> users;

    public TestDatabase(){
        users = new HashMap<>();
        this.add(User.create("Jimmy", "123"));
        this.add(User.create("Mal", "pass1"));
        this.add(User.create("Henry", "word_bro"));
    }

    @Override
    public User get(Integer id){
        return users.get(id);
    }

    @Override
    public List<User> all(){
        return new ArrayList<>(users.values());
    }

    @Override
    public User add(User user){
        Integer index = users.size() + 1;
        user.setId(index);
        users.put(index, user);
        return user;
    }
}
