package cinema.Controller;

import cinema.Model.CinemaRoom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaRoomController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/room")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/room/all")
    public ResponseEntity all() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/room/filter")
    public ResponseEntity filter(@RequestParam(value="filter") String filter) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/room")
    public ResponseEntity create(@RequestBody CinemaRoom room) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/room")
    public ResponseEntity update(@RequestBody CinemaRoom room) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/room")
    public ResponseEntity delete(@RequestParam(value="id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
