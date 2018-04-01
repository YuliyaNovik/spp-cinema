package cinema.Service;

public class Service {
    private static UserService userService = null;
    private static MovieService movieService = null;
    private static ExportService exportService = null;

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
