package cinema.Controller;

import cinema.Model.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TicketController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/ticket")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/ticket/all")
    public ResponseEntity all() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/ticket/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/api/ticket")
    public ResponseEntity create(@RequestBody Ticket ticket) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/ticket")
    public ResponseEntity update(@RequestBody Ticket ticket) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/ticket")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
