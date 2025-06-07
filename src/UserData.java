import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static List<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }
    public static List<User> getUsers() {
        return users;
    }
    public static User getUserName(String name) {
        for (User x : users){
            if(x.getName().equals(name)){
                return x;
            }
        }
        return null;
    }
}
