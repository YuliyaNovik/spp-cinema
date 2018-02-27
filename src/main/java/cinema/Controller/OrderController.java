package cinema.Controller;

import cinema.Model.Status;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @RequestMapping(method = RequestMethod.GET, path = "/order")
    public Status order(@RequestParam(value="name", defaultValue="World") String name) {

        return Status.OK;
    }
}
