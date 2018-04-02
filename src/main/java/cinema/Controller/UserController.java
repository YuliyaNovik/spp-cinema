package cinema.Controller;

import cinema.Model.User;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/user")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        User user = Service.getUserService().getUser(id);
        return new ResponseEntity<>(user, user == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/user/all")
    public ResponseEntity all() {
        List<User> users = Service.getUserService().getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/user/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        List<User> users = Service.getUserService().getUserByFilter(filter);
        return new ResponseEntity<>(users, users == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/user")
    public ResponseEntity update(@RequestBody User user) {
        boolean isSuccessful = Service.getUserService().updateUser(user);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/user")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        boolean isSuccessful = Service.getUserService().deleteUser(id);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
