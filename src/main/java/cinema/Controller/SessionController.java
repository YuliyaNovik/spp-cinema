package cinema.Controller;

import cinema.Model.Session;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/session")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        Session session = Service.getSessionService().getSession(id);
        return new ResponseEntity<>(session, session == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/session/all")
    public ResponseEntity all() {
        List<Session> sessions = Service.getSessionService().getAllSession();
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/session/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        List<Session> sessions = Service.getSessionService().getSessionByFilter(filter);
        return new ResponseEntity<>(sessions, sessions == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/session")
    public ResponseEntity create(@RequestBody Session session) {
        boolean isSuccessful = Service.getSessionService().createSession(session);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/session")
    public ResponseEntity update(@RequestBody Session session) {
        boolean isSuccessful = Service.getSessionService().updateSession(session);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/session")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        boolean isSuccessful = Service.getSessionService().deleteSession(id);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
