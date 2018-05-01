package cinema.Controller;

import cinema.Model.Seat;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeatController {
    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.GET, path = "/api/seat")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        Seat seat = Service.getSeatService().getSeat(id);
        return new ResponseEntity<>(seat, seat == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.GET, path = "/api/seat/all")
    public ResponseEntity all() {
        List<Seat> seats = Service.getSeatService().getAllSeat();
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.GET, path = "/api/seat/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        List<Seat> seats = Service.getSeatService().getSeatByFilter(filter);
        return new ResponseEntity<>(seats, seats == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.POST, path = "/api/seat")
    public ResponseEntity create(@RequestBody Seat seat) {
        boolean isSuccessful = Service.getSeatService().createSeat(seat);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.PUT, path = "/api/seat")
    public ResponseEntity update(@RequestBody Seat seat) {
        boolean isSuccessful = Service.getSeatService().updateSeat(seat);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.DELETE, path = "/api/seat")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        boolean isSuccessful = Service.getSeatService().deleteSeat(id);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
