package cinema.Controller;

import cinema.Model.Movie;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/movie")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        Movie movie = Service.getMovieService().getMovie(id);
        return new ResponseEntity<>(movie, movie == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/movie/all")
    public ResponseEntity all() {
        List<Movie> movies = Service.getMovieService().getAllMovie();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/movie/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        List<Movie> movies = Service.getMovieService().getMovieByFilter(filter);
        return new ResponseEntity<>(movies, movies == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/movie")
    public ResponseEntity create(@RequestBody Movie movie) {
        boolean isSuccessful = Service.getMovieService().createMovie(movie);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/movie")
    public ResponseEntity update(@RequestBody Movie movie) {
        boolean isSuccessful = Service.getMovieService().updateMovie(movie);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/movie")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        boolean isSuccessful = Service.getMovieService().deleteMovie(id);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
