package cinema.Controller;

import cinema.Model.Ticket;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @RequestMapping(method = RequestMethod.POST, path = "/api/order")
    public ResponseEntity order(@RequestBody Ticket ticket) {
        boolean isSuccessful = Service.getTicketService().createTicket(ticket);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
