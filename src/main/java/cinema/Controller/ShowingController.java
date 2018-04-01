package cinema.Controller;

import cinema.Model.ShowingMovie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShowingController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/showing")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/showing/all")
    public ResponseEntity all() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/showing/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/showing")
    public ResponseEntity create(@RequestBody ShowingMovie showingMovie) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/showing")
    public ResponseEntity update(@RequestBody ShowingMovie showingMovie) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/showing")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
