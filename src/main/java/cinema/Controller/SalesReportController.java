package cinema.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalesReportController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/sales")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/sales/all")
    public ResponseEntity all() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/sales/filter")
    public ResponseEntity filter(@RequestParam(value = "filter") String filter) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
