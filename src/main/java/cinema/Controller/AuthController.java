package cinema.Controller;

import cinema.Model.User;
import cinema.Service.Service;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/login")
    public ResponseEntity<User> login(
        @RequestParam(value="userName") String userName,
        @RequestParam(value="password") String password
    ) {
        User user = Service.getUserService().getUser(userName, password);
        HttpStatus status = user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(user, status);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/registration")
    public ResponseEntity registration(@RequestBody User user) {
        boolean isCreated = Service.getUserService().createUser(user);

        HttpStatus status = isCreated ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity(status);
    }
}
