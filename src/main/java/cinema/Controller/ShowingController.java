package cinema.Controller;

import cinema.Model.Showing;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowingController {
    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.GET, path = "/api/showing")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        Showing showing = Service.getShowingService().getShowing(id);
        return new ResponseEntity<>(showing, showing == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.GET, path = "/api/showing/all")
    public ResponseEntity all() {
        List<Showing> showings = Service.getShowingService().getAllShowing();
        return new ResponseEntity<>(showings, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.GET, path = "/api/showing/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        List<Showing> showings = Service.getShowingService().getShowingByFilter(filter);
        return new ResponseEntity<>(showings, showings == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.POST, path = "/api/showing")
    public ResponseEntity create(@RequestBody Showing showing) {
        boolean isSuccessful = Service.getShowingService().createShowing(showing);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.PUT, path = "/api/showing")
    public ResponseEntity update(@RequestBody Showing showing) {
        boolean isSuccessful = Service.getShowingService().updateShowing(showing);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.DELETE, path = "/api/showing")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        boolean isSuccessful = Service.getShowingService().deleteShowing(id);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
