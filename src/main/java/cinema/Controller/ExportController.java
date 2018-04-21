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

@RestController
public class ExportController {
    @RequestMapping(method = RequestMethod.GET, path = "/api/export/pdf")
    public void pdf(HttpServletResponse response, @RequestParam(value = "table") String table, @RequestParam(value = "filter") String filter) throws IOException {
        byte[] pdf = Service.getExportService().exportToPDF(table, filter);
        if (pdf == null) {
            return;
        }
        InputStream stream = new ByteArrayInputStream(pdf);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.pdf\"; charset=utf-8");
        IOUtils.copy(stream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/export/excel")
    public void excel(HttpServletResponse response, @RequestParam(value = "table") String table, @RequestParam(value = "filter") String filter) throws IOException {
        byte[] xlsx = Service.getExportService().exportToXLSX(table, filter);
        if (xlsx == null) {
            return;
        }
        InputStream stream = new ByteArrayInputStream(xlsx);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.xlsx\"; charset=utf-8");
        IOUtils.copy(stream, response.getOutputStream());
        response.flushBuffer();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/api/export/csv")
    public void csv(HttpServletResponse response, @RequestParam(value = "table") String table, @RequestParam(value = "filter") String filter) throws IOException {
        byte[] csv = Service.getExportService().exportToCSV(table, filter);
        if (csv == null) {
            return;
        }
        InputStream stream = new ByteArrayInputStream(csv);
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.csv\"; charset=utf-8");
        IOUtils.copy(stream, response.getOutputStream());
        response.flushBuffer();
    }
}
