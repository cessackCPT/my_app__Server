import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActualDatabase implements UserDB {
    /**
     * This is where we interact with the Sqlite database
     */
    public Connection connectDB(){
        Connection connection = null;
        Statement statement = null;
        System.out.println("Successfully connected to UserDB");

        try{
            Class.forName("org.sqlite.JDBC");
            //this is where you make the connection for the database
            connection = DriverManager.getConnection("jdbc:sqlite:users");
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " +e.getMessage());
        }
        return connection;
    }

    @Override
    public User get(Integer id){
        return null;
    }

    @Override
    public List<User> all() throws SQLException{
        User user = new User();
        User temporary;
        Statement statement;

        List<User>UserList = new ArrayList<>();

        String sql1 = "SELECT id,\n" +
                "       username,\n" +
                "       password \n" +
                "       FROM UserList;\n";

        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:users")){
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql1);
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                temporary = user;
                UserList.add(temporary);
                user = new User();
            }
        }
//        catch(SQLException e){
//
//        }
        return UserList;
    }

    //this is where the values go into the table
    @Override
    public User add(User user) throws SQLException {
        String sql_to_execute = "INSERT INTO UserList (\n" +
                "                           username,\n" +
                "                           password\n" +
                "                           )\n" +
                "                           VALUES (\n" +
                "                           ?,\n" +
                "                           ?\n" +
                "                           );\n";



        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:users")){
//            Class.forName("org.sqlite.JDBC");
             PreparedStatement prepedStmt = conn.prepareStatement(sql_to_execute);
            prepedStmt.setString(1, user.getUsername());
            prepedStmt.setString(2, user.getPassword());
            prepedStmt.execute();
            System.out.println("Quote and author name has been inserted into table");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        return user;
    }

}

