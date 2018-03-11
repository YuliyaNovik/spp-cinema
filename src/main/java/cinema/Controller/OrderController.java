package cinema.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @RequestMapping(method = RequestMethod.GET, path = "/order")
    public ResponseEntity order(@RequestParam(value="name", defaultValue="World") String name) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
