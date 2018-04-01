package cinema.DAL;

public class DAL {
    private static UserDAL userDAL = null;
    private static MovieDAL movieDAL = null;

    public static MovieDAL getMovieDAL() {
        if (movieDAL == null) {
            movieDAL = new MovieDAL();
        }
        return movieDAL;
    }

    public static UserDAL getUserDAL() {
        if (userDAL == null) {
            userDAL = new UserDAL();
        }

        return userDAL;
    }
}
