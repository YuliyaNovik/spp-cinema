package cinema.Helper;

public class SQLQuery {
    //USER
    public static String getUserByName = "SELECT * FROM user WHERE user_name='%s'";
    public static String getUserByEmail = "SELECT * FROM user WHERE email='%s'";
    public static String insertUser = "INSERT INTO user (user_name, email, password, role) VALUES ('%s', '%s', '%s', '%s')";
    //MOVIE
    public static String getMovieById  = "SELECT * FROM movie WHERE id = %d";
    public static String getAllMovie = "SELECT * FROM movie";
    public static String deleteMovie = "DELETE FROM movie WHERE id = %d";
    public static String insertMovie = "INSERT INTO movie (name, year, duration, start_date, end_date, director, age_limit) VALUES ('%s', %d, %d, '%s', '%s', '%s', '%s')";
    public static String updateMovie = "UPDATE movie SET name = '%s', year = %d, duration = %d, start_date = '%s', end_date = '%s', director = '%s', age_limit = '%s' WHERE movie.id = %d";
}
