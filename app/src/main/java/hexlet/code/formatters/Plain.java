package hexlet.code.formatters;

import com.google.common.primitives.Primitives;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String plain(List<Map<String, Object>> diff) {
        StringBuilder result = new StringBuilder();
        for (var el : diff) {
            String str = "";
            if (el.get("status").equals("changed")) {
                str = String.format("Property '%s' was updated. From %s to %s\n",
                        el.get("key"),
                        getValue(el.get("value1")),
                        getValue(el.get("value2")));
            } else if (el.get("status").equals("removed")) {
                str = String.format("Property '%s' was removed\n",
                        el.get("key"));
            } else if (el.get("status").equals("added")) {
                str = String.format("Property '%s' was added with value: %s\n",
                        el.get("key"),
                        getValue(el.get("value")));
            }
            result.append(str);
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
