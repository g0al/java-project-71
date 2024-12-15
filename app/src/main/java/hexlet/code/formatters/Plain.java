package hexlet.code.formatters;

import com.google.common.primitives.Primitives;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String plain(List<Map<String, Object>> diff) {
        var result = new StringBuilder();

        for (var el : diff) {
            if (el.get("status").equals("changed")) {
                var value1 = el.get("value1");
                var value2 = el.get("value2");
                result.append("Property '")
                        .append(el.get("key"))
                        .append("' was updated. From ")
                        .append(getValue(value1))
                        .append(" to ")
                        .append(getValue(value2))
                        .append(System.lineSeparator());
            } else if (el.get("status").equals("added")) {
                var value = el.get("value");
                result.append("Property '")
                        .append(el.get("key"))
                        .append("' was added with value: ")
                        .append(getValue(value))
                        .append(System.lineSeparator());
            } else if (el.get("status").equals("removed")) {
                result.append("Property '")
                        .append(el.get("key"))
                        .append("' was removed")
                        .append(System.lineSeparator());
            }
        }
        return result.toString().trim();
    }

    public static Object getValue(Object obj) {
        if (obj != null) {
            if (obj instanceof String) {
                return "'" + obj + "'";
            } else if (!Primitives.isWrapperType(obj.getClass())) {
                return "[complex value]";
            }
            return obj;
        }
        return null;
    }
}
