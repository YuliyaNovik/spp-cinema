package cinema.Controller;

import cinema.Model.CinemaRoom;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CinemaRoomController {
    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.GET, path = "/api/cinemaRoom")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        CinemaRoom cinemaRoom = Service.getCinemaRoomService().getCinemaRoom(id);
        return new ResponseEntity<>(cinemaRoom, cinemaRoom == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.GET, path = "/api/cinemaRoom/all")
    public ResponseEntity all() {
        List<CinemaRoom> cinemaRooms = Service.getCinemaRoomService().getAllCinemaRoom();
        return new ResponseEntity<>(cinemaRooms, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.GET, path = "/api/cinemaRoom/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        List<CinemaRoom> cinemaRooms = Service.getCinemaRoomService().getCinemaRoomByFilter(filter);
        return new ResponseEntity<>(cinemaRooms, cinemaRooms == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.POST, path = "/api/cinemaRoom")
    public ResponseEntity create(@RequestBody CinemaRoom cinemaRoom) {
        boolean isSuccessful = Service.getCinemaRoomService().createCinemaRoom(cinemaRoom);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.PUT, path = "/api/cinemaRoom")
    public ResponseEntity update(@RequestBody CinemaRoom cinemaRoom) {
        boolean isSuccessful = Service.getCinemaRoomService().updateCinemaRoom(cinemaRoom);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin("*")
    @RequestMapping(method = RequestMethod.DELETE, path = "/api/cinemaRoom")
    public ResponseEntity delete(@RequestParam(value = "id") int id) {
        boolean isSuccessful = Service.getCinemaRoomService().deleteCinemaRoom(id);
        return new ResponseEntity(isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
