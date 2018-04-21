package cinema.Service;

import cinema.DAL.DAL;
import cinema.Util.CSVUtil;
import cinema.Util.ExcelUtil;
import cinema.Util.FilterUtil;
import cinema.Util.PdfUtil;

import java.nio.charset.StandardCharsets;

public class ExportService {
    private static final String MOVIE_NAME = "movie";
    private static FilterUtil filterUtil = new FilterUtil();

    public byte[] exportToCSV(String table, String filter) {
        String query = filterUtil.generateQuery(filter, table);
        if (query == null) {
            return null;
        }

        Object[] items = null;
        if (table.equals(MOVIE_NAME)) {
            items = DAL.getMovieDAL().getByQuery(query).toArray();
        }
        CSVUtil csvUtil = new CSVUtil();
        return csvUtil.generate(items);
    }

    public byte[] exportToPDF(String table, String filter) {
        String query = filterUtil.generateQuery(filter, table);
        if (query == null) {
            return null;
        }
        Object[] items = null;
        if (table.equals(MOVIE_NAME)) {
            items = DAL.getMovieDAL().getByQuery(query).toArray();
        }
        PdfUtil excelUtil = new PdfUtil();
        return excelUtil.generate(items);
    }

    public byte[] exportToXLSX(String table, String filter) {
        String query = filterUtil.generateQuery(filter, table);
        if (query == null) {
            return null;
        }
        Object[] items = null;
        if (table.equals(MOVIE_NAME)) {
            items = DAL.getMovieDAL().getByQuery(query).toArray();
        }
        ExcelUtil excelUtil = new ExcelUtil();
        return excelUtil.generate(items);
    }
}
