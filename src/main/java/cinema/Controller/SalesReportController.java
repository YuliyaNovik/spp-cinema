package cinema.Controller;

import cinema.Model.Report;
import cinema.Service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalesReportController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/sales")
    public ResponseEntity get(@RequestParam(value="id") int id) {
        Report report = Service.getSalesReportService().getReport(id);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
