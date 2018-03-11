package cinema.DAL;

public class DAL {
    private static UserDAL userDAL = null;

    public static UserDAL getUserDAL() {
        if (userDAL == null) {
            userDAL = new UserDAL();
        }

        return userDAL;
    }
}
