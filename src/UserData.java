import java.util.ArrayList;

public class UserData {
    private static ArrayList<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    public static ArrayList<User> getUsers() {
        users.get(2).addLevel(); users.get(2).addLevel(); users.get(2).addLevel();
        return users;
    }
}
