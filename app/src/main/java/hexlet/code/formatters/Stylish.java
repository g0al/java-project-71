package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(List<Map<String, Object>> diff) {
        var result = new StringBuilder();
        result.append("{").append(System.lineSeparator());
        for (var el : diff) {
            if (el.get("status").equals("changed")) {
                result.append("  - ")
                        .append(el.get("key"))
                        .append(": ")
                        .append(el.get("firstValue"))
                        .append(System.lineSeparator());
                result.append("  + ")
                        .append(el.get("key"))
                        .append(": ")
                        .append(el.get("secondValue"))
                        .append(System.lineSeparator());
            } else if (el.get("status").equals("added")) {
                result.append("  + ")
                        .append(el.get("key"))
                        .append(": ")
                        .append(el.get("secondValue"))
                        .append(System.lineSeparator());
            } else if (el.get("status").equals("removed")) {
                result.append("  - ")
                        .append(el.get("key"))
                        .append(": ")
                        .append(el.get("firstValue"))
                        .append(System.lineSeparator());
            } else if (el.get("status").equals("unchanged")) {
                result.append("    ")
                        .append(el.get("key"))
                        .append(": ")
                        .append(el.get("firstValue"))
                        .append(System.lineSeparator());
            }
        }
        result.append("}");
        return result.toString();
    }
}
