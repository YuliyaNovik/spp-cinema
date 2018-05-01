package cinema.Service;

import cinema.DAL.DAL;
import cinema.Util.CSVUtil;
import cinema.Util.ExcelUtil;
import cinema.Util.FilterUtil;
import cinema.Util.PdfUtil;

public class ExportService {
    private static final String MOVIE_NAME = "movie";
    private static final String SESSION_NAME = "session";
    private static final String SHOWING_NAME = "showing_movie";
    private static final String TICKET_NAME = "ticket";
    private static final String REPORT_NAME = "report";
    private static final String USER_NAME = "user";
    private static FilterUtil filterUtil = new FilterUtil();

    public byte[] exportToCSV(String table, String filter) {
        Object[] items = getItems(table, filter);
        if (items == null) {
            return null;
        }
        CSVUtil csvUtil = new CSVUtil();
        return csvUtil.generate(items);
    }

    public byte[] exportToPDF(String table, String filter) {
        Object[] items = getItems(table, filter);
        if (items == null) {
            return null;
        }
        PdfUtil pdfUtil = new PdfUtil();
        return pdfUtil.generate(items);
    }

    public byte[] exportToXLSX(String table, String filter) {
        Object[] items = getItems(table, filter);
        if (items == null) {
            return null;
        }
        ExcelUtil excelUtil = new ExcelUtil();
        return excelUtil.generate(items);
    }


    private Object[] getItems(String table, String filter) {
        String query = filterUtil.generateQuery(filter, table);
        if (query == null && !table.equals(REPORT_NAME)) {
            return null;
        }

        switch (table) {
            case MOVIE_NAME:
                return DAL.getMovieDAL().getByQuery(query).toArray();
            case SESSION_NAME:
                return Service.getSessionService().toDocument(DAL.getSessionDAL().getByQuery(query));
            case SHOWING_NAME:
                return Service.getShowingService().toDocument(DAL.getShowingDAL().getByQuery(query));
            case TICKET_NAME:
                return Service.getTicketService().toDocument(DAL.getTicketDAL().getByQuery(query));
            case USER_NAME:
                return DAL.getUserDAL().getByQuery(query).toArray();
            case REPORT_NAME:
                return Service.getSalesReportService().toDocument();
            default:
                return null;
        }
    }
}
