package cinema.DAL;

public class DAL {
    private static UserDAL userDAL = null;
    private static MovieDAL movieDAL = null;
    private static CinemaRoomDAL cinemaRoomDAL = null;
    private static SessionDAL sessionDAL = null;
    private static ShowingDAL showingDAL = null;
    private static TicketDAL ticketDAL = null;

    public static CinemaRoomDAL getCinemaRoomDAL() {
        if (cinemaRoomDAL == null) {
            cinemaRoomDAL = new CinemaRoomDAL();
        }
        return cinemaRoomDAL;
    }

    public static SessionDAL getSessionDAL() {
        if (sessionDAL == null) {
            sessionDAL = new SessionDAL();
        }
        return sessionDAL;
    }

    public static ShowingDAL getShowingDAL() {
        if (showingDAL == null) {
            showingDAL = new ShowingDAL();
        }
        return showingDAL;
    }

    public static TicketDAL getTicketDAL() {
        if (ticketDAL == null) {
            ticketDAL = new TicketDAL();
        }
        return ticketDAL;
    }

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
