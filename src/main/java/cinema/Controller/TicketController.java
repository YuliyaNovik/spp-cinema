package cinema.Controller;

import cinema.Model.Ticket;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/ticket")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        Ticket ticket = Service.getTicketService().getTicket(id);
        return new ResponseEntity<>(ticket, ticket == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/ticket/all")
    public ResponseEntity all() {
        List<Ticket> tickets = Service.getTicketService().getAllTicket();
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/ticket/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        List<Ticket> tickets = Service.getTicketService().getTicketByFilter(filter);
        return new ResponseEntity<>(tickets, tickets == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/api/ticket")
    public ResponseEntity update(@RequestBody Ticket ticket) {
        boolean isSuccessful = Service.getTicketService().updateTicket(ticket);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/api/ticket")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        boolean isSuccessful = Service.getTicketService().deleteTicket(id);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
