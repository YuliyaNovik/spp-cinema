package cinema.Helper;

public class SQLQuery {
    //USER
    public static String getUserByName = "SELECT * FROM user WHERE user_name='%s'";
    public static String getUserByEmail = "SELECT * FROM user WHERE email='%s'";
    public static String insertUser = "INSERT INTO user (user_name, email, password, role) VALUES ('%s', '%s', '%s', '%s')";
    public static String getUserById = "SELECT * FROM user WHERE id = %d";
    public static String getAllUser = "SELECT * FROM user";
    public static String deleteUser = "DELETE FROM user WHERE id = %d";
    public static String updateUser = "UPDATE user SET user_name = '%s', email = '%s', password = '%s', role = '%s'  WHERE movie.id = %d";
    //MOVIE
    public static String getMovieById = "SELECT * FROM movie WHERE id = %d";
    public static String getAllMovie = "SELECT * FROM movie";
    public static String deleteMovie = "DELETE FROM movie WHERE id = %d";
    public static String insertMovie = "INSERT INTO movie (name, year, duration, start_date, end_date, director, age_limit) VALUES ('%s', %d, %d, '%s', '%s', '%s', '%s')";
    public static String updateMovie = "UPDATE movie SET name = '%s', year = %d, duration = %d, start_date = '%s', end_date = '%s', director = '%s', age_limit = '%s' WHERE movie.id = %d";
    //CINEMA_ROOM
    public static String getCinemaRoomById = "SELECT * FROM cinema_room WHERE id = %d";
    public static String getAllCinemaRoom = "SELECT * FROM cinema_room";
    public static String deleteCinemaRoom = "DELETE FROM cinema_room WHERE id = %d";
    public static String insertCinemaRoom = "INSERT INTO cinema_room (cinema_id, name, number_of_seats) VALUES (%d, '%s', %d)";
    public static String updateCinemaRoom = "UPDATE cinema_room SET cinema_id = %d, name = '%s', number_of_seats = %d WHERE cinema_room.id = %d";
    //SESSION
    public static String getSessionById = "SELECT * FROM session WHERE id = %d";
    public static String getAllSession = "SELECT * FROM session";
    public static String deleteSession = "DELETE FROM session WHERE id = %d";
    public static String insertSession = "INSERT INTO session (showing_id, cinema_room_id, date, time, coefficient, number_of_sold_tickets) VALUES (%d, %d, '%s', '%s', %f, %d)";
    public static String updateSession = "UPDATE session SET showing_id = %d, cinema_room_id = %d, date = '%s', time = '%s', coefficient = %f, number_of_sold_tickets = %d WHERE movie.id = %d";
    //SHOWING
    public static String getShowingById = "SELECT * FROM showing WHERE id = %d";
    public static String getAllShowing = "SELECT * FROM showing";
    public static String deleteShowing = "DELETE FROM showing WHERE id = %d";
    public static String insertShowing = "INSERT INTO showing (cinema_id, movie_id, start_showing_date, end_showing_date, estimated_cost) VALUES (%d, %d, '%s', '%s', %f)";
    public static String updateShowing = "UPDATE showing SET cinema_id = %d, movie_id = %d, start_showing_date = '%s', end_showing_date = '%s', estimated_cost = %f WHERE movie.id = %d";
    //TICKET
    public static String getTicketById = "SELECT * FROM ticket WHERE id = %d";
    public static String getAllTicket = "SELECT * FROM ticket";
    public static String deleteTicket = "DELETE FROM ticket WHERE id = %d";
    public static String insertTicket = "INSERT INTO ticket (session_id, seat_id, cost) VALUES (%d, %d, %f)";
    public static String updateTicket = "UPDATE ticket SET session_id = %d, seat_id = %d, cost = %f WHERE movie.id = %d";
}
