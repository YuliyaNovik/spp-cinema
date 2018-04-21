package cinema.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterUtil {
    public String generateQuery(String filter, String table) {
        if (filter.equals("*")) {
            return "SELECT * FROM " + table;
        }
        try {
            List<String> token = getTokens(filter);
            List<String> exp = getExpression(filter);
            StringBuilder query = new StringBuilder("SELECT * FROM " + table + " WHERE ");
            for (int i = 0; i < token.size(); i++) {
                query.append(translateToken(token.get(i)));
                if (exp.size() > i) {
                    query.append(exp.get(i));
                }
            }
            return query.toString();
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    private List<String> getExpression(String filter) {
        List<String> exp = new ArrayList<>();
        for (char ch : filter.toCharArray()) {
            if (ch == '|') {
                exp.add(" OR ");
            } else if (ch == '&') {
                exp.add(" AND ");
            }
        }
        return exp;
    }

    private List<String> getTokens(String filter) {
        return Arrays.asList(filter.split("[&|]"));
    }

    private String translateToken(String token) {
        StringBuilder query = new StringBuilder();
        if (token.indexOf('>') != -1 || token.indexOf('<') != -1) {
            String prop = token.split("[><]")[0];
            String exp = token.indexOf('>') != -1 ? ">" : "<";
            String value = token.split("[><*]")[1];
            String type = token.split("[*]")[1];
            if (type.equals("i")) {
                query.append(String.format("%s %s %s", prop, exp, value));
            } else {
                query.append(String.format("%s %s '%s'", prop, exp, value));
            }
        } else if (token.indexOf('=') != -1) {
            String prop = token.split("=")[0];
            String[] values = token.split("[=*]")[1].split(",");
            String type = token.split("[*]")[1];

            for (String value : values) {
                if (type.equals("i")) {
                    query.append(String.format("%s = %s", prop, value));
                } else {
                    query.append(String.format("%s = '%s'", prop, value));
                }
                if (values[values.length - 1] != value) {
                    query.append(" OR ");
                }
            }
        }

        return "(" + query.toString() + ")";
    }
}
