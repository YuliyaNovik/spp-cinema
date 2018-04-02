package cinema.Service;

import cinema.DAL.DAL;
import cinema.Model.Movie;
import cinema.Util.FilterUtil;

import java.util.List;

public class MovieService {
    public Movie getMovie(int id) {
        return DAL.getMovieDAL().getById(id);
    }

    public List<Movie> getAllMovie() {
        return DAL.getMovieDAL().getAll();
    }

    public List<Movie> getMovieByFilter(String filter) {
        FilterUtil filterUtil = new FilterUtil();
        String query = filterUtil.generateQuery(filter, "movie");
        if (query == null) {
            return null;
        }
        System.out.println(query);
        return DAL.getMovieDAL().getByQuery(query);
    }

    public boolean createMovie(Movie movie) {
        return DAL.getMovieDAL().insert(movie);
    }

    public boolean updateMovie(Movie movie) {
        return DAL.getMovieDAL().update(movie);
    }

    public boolean deleteMovie(int id) {
        return DAL.getMovieDAL().deleteById(id);
    }
}
