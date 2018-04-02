package cinema.Service;

import cinema.DAL.DAL;
import cinema.Util.CSVUtil;
import cinema.Util.FilterUtil;

public class ExportService {
    private static final String MOVIE_NAME = "movie";

    public String exportToCSV(String table, String filter) {
        FilterUtil filterUtil = new FilterUtil();
        String query = filterUtil.generateQuery(filter, table);
        if (query == null) {
            return null;
        }

        Object[] items = null;
        if (table.equals(MOVIE_NAME)) {
            items = DAL.getMovieDAL().getByQuery(query).toArray();
        }
        CSVUtil csvService = new CSVUtil();
        return csvService.generate(items);
    }

    public String exportToPDF() {
        return "";
    }

    public String exportToXLSX() {
        return "";
    }
}
