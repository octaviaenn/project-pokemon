import java.util.ArrayList;

public class UserData {
    private static ArrayList<User> users = new ArrayList<>();

    public static void addUser(User user) {
        users.add(user);
    }

    public static ArrayList<User> getUsers() {
        users.add(new User("pleyer"));
        users.get(0).addLevel(); users.get(0).addLevel();
        users.add(new User("weeb"));
        users.add(new User("epic"));
        users.get(2).addLevel(); users.get(2).addLevel(); users.get(2).addLevel();
        return users;
    }
}
