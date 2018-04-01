package cinema.DAL;

import cinema.Helper.SQLQuery;
import cinema.Model.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAL extends BaseDAL {
    private static final int ID_INDEX = 1;
    private static final int NAME_INDEX = 2;
    private static final int YEA_INDEX = 3;
    private static final int DURATION_INDEX = 4;
    private static final int START_DATE_INDEX = 5;
    private static final int END_DATE_INDEX = 6;
    private static final int DIRECTOR_INDEX = 7;
    private static final int AGE_LIMIT_INDEX = 8;

    public Movie getById(int id) {
        String query = String.format(SQLQuery.getMovieById, id);
        List<Movie> movies = get(query);
        return movies.size() > 0 ? movies.get(0) : null;
    }

    public List<Movie> getAll() {
        return get(SQLQuery.getAllMovie);
    }

    public List<Movie> getByQuery(String query) {
        return get(query);
    }

    public boolean deleteById(int id) {
        String query = String.format(SQLQuery.deleteMovie, id);
        boolean isDeleted = openUpdateConnection(query);
        closeConnection(null);
        return isDeleted;
    }

    public boolean update(Movie movie) {
        String query = String.format(
                SQLQuery.updateMovie,
                movie.name,
                movie.year,
                movie.duration,
                movie.startDate,
                movie.endDate,
                movie.director,
                movie.ageLimit,
                movie.id
        );
        boolean isUpdated = openUpdateConnection(query);
        closeConnection(null);
        return isUpdated;
    }

    public boolean insert(Movie movie) {
        String query = String.format(
                SQLQuery.insertMovie,
                movie.name,
                movie.year,
                movie.duration,
                movie.startDate,
                movie.endDate,
                movie.director,
                movie.ageLimit
        );

        boolean isCreated = openUpdateConnection(query);
        closeConnection(null);
        return isCreated;
    }

    private List<Movie> get(String query) {
        ResultSet result = openConnection(query);

        List<Movie> movies = new ArrayList<>();
        try {
            while (result != null && result.next()) {
                Movie movie = new Movie();
                movie.id = result.getInt(MovieDAL.ID_INDEX);
                movie.name = result.getString(MovieDAL.NAME_INDEX);
                movie.year = result.getInt(MovieDAL.YEA_INDEX);
                movie.duration = result.getInt(MovieDAL.DURATION_INDEX);
                movie.startDate = result.getString(MovieDAL.START_DATE_INDEX);
                movie.endDate = result.getString(MovieDAL.END_DATE_INDEX);
                movie.director = result.getString(MovieDAL.DIRECTOR_INDEX);
                movie.ageLimit = result.getString(MovieDAL.AGE_LIMIT_INDEX);

                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(result);
        return movies;
    }
}
