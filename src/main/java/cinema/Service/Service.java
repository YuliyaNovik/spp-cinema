package cinema.Service;

public class Service {
    private static UserService userService = null;

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
}
