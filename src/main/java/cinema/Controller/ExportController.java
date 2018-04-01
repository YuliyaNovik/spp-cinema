package cinema.Controller;

import cinema.Service.Service;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RestController
public class ExportController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/export/pdf")
    public ResponseEntity pdf(@RequestParam(value = "table") String table, @RequestParam(value = "filter") String filter) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/export/excel")
    public ResponseEntity excel(@RequestParam(value = "table") String table, @RequestParam(value = "filter") String filter) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/export/csv")
    public void csv(HttpServletResponse response, @RequestParam(value = "table") String table, @RequestParam(value = "filter") String filter) throws IOException {
        String csv = Service.getExportService().exportToCSV(table, filter);
        if (csv == null) {
            return;
        }
        InputStream stream = new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8));
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.csv\"; charset=utf-8");
        IOUtils.copy(stream, response.getOutputStream());
        response.flushBuffer();
    }
}
