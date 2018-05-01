package cinema.Controller;

import cinema.Model.Ticket;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.POST, path = "/api/order")
    public ResponseEntity order(@RequestBody List<Ticket> ticket) {
        boolean isSuccessful = Service.getTicketService().createTicket(ticket);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
