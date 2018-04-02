package cinema.Service;

public class Service {
    private static UserService userService = null;
    private static MovieService movieService = null;
    private static ExportService exportService = null;
    private static CinemaRoomService cinemaRoomService = null;
    private static SessionService sessionService = null;
    private static ShowingService showingService = null;
    private static TicketService ticketService = null;

    public static CinemaRoomService getCinemaRoomService() {
        if (cinemaRoomService == null) {
            cinemaRoomService = new CinemaRoomService();
        }
        return cinemaRoomService;
    }

    public static SessionService getSessionService() {
        if (sessionService == null) {
            sessionService = new SessionService();
        }
        return sessionService;
    }

    public static ShowingService getShowingService() {
        if (showingService == null) {
            showingService = new ShowingService();
        }
        return showingService;
    }

    public static TicketService getTicketService() {
        if (ticketService == null) {
            ticketService = new TicketService();
        }
        return ticketService;
    }

    public static ExportService getExportService() {
        if (exportService == null) {
            exportService = new ExportService();
        }
        return exportService;
    }

    public static MovieService getMovieService() {
        if (movieService == null) {
            movieService = new MovieService();
        }
        return movieService;
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }
}
