package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();
        for (var el : diff) {
            String str = "";
            if (el.get("status").equals("changed")) {
                str = String.format("  - %s: %s\n  + %s: %s\n",
                        el.get("key"),
                        el.get("value1"),
                        el.get("key"),
                        el.get("value2"));
            } else if (el.get("status").equals("added")) {
                str = String.format("  + %s: %s\n",
                        el.get("key"),
                        el.get("value"));
            } else if (el.get("status").equals("removed")) {
                str = String.format("  - %s: %s\n",
                        el.get("key"),
                        el.get("value"));
            } else if (el.get("status").equals("unchanged")) {
                str = String.format("    %s: %s\n",
                        el.get("key"),
                        el.get("value"));
            }
            result.append(str);
        }
        return String.format("{\n%s}", result);
    }
}
