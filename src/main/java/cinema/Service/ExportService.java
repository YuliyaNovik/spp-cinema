package cinema.Service;

import cinema.DAL.DAL;
import cinema.Util.CSVUtil;

public class ExportService {
    private static final String MOVIE_NAME = "movie";

    public String exportToCSV(String table, String filter) {
        FilterService filterService = new FilterService();
        String query = filterService.generateQuery(filter, table);
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
