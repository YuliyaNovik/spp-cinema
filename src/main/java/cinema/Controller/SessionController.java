package cinema.Controller;

import cinema.Model.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SessionController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/session")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/session/all")
    public ResponseEntity all() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/session/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/session")
    public ResponseEntity create(@RequestBody Session session) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/session")
    public ResponseEntity update(@RequestBody Session session) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/session")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
