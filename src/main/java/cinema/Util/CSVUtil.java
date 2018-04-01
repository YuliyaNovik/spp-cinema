package cinema.Util;

public class CSVUtil {
    public String generate(Object[] items) {
        try {
            StringBuilder csv = new StringBuilder();
            for (Object item : items) {
                csv.append(stringToCSVString(item.toString()));
            }
            return csv.toString();
        } catch (Exception ex) {
            return null;
        }
    }

    private String stringToCSVString(String string) {
        return String.join(",", string.split("[|]")) + "\n";
    }
}
