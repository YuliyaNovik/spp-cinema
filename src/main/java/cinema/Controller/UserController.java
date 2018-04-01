package cinema.Controller;

import cinema.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/user")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/user/all")
    public ResponseEntity all() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/user/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/user")
    public ResponseEntity create(@RequestBody User userMovie) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/user")
    public ResponseEntity update(@RequestBody User userMovie) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/user")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
