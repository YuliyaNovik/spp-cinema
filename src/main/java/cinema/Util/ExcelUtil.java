package cinema.Util;

import com.coreoz.windmill.Windmill;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ExcelUtil {
    public byte[] generate(Object[] items) {
        List<String[]> objects = getExcelItems(items);
        if (objects.isEmpty()) {
            return null;
        }
        return Windmill.export(objects)
                .withNoHeaderMapping(exportHeaderMapping(objects.get(0).length))
                .asExcel()
                .toByteArray();
    }

    private List<String[]> getExcelItems(Object[] items) {
        List<String[]> objects = new ArrayList<>();
        for (Object item : items) {
            objects.add(item.toString().split("[|]"));
        }
        return objects;
    }

    private List<Function<String[], ?>> exportHeaderMapping(int length) {
        List<Function<String[], ?>> mapping = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            int currentI = i;
            mapping.add(item -> item[currentI]);
        }
        return mapping;
    }
}
