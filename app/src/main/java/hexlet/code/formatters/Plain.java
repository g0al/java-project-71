package hexlet.code.formatters;

import com.google.common.primitives.Primitives;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String plain(List<Map<String, Object>> diff) {
        var result = new StringBuilder();

        for (var el : diff) {
            if (el.get("status").equals("changed")) {
                var left = el.get("firstValue");
                var right = el.get("secondValue");
                result.append("Property '")
                        .append(el.get("key"))
                        .append("' was updated. From ")
                        .append(isStringValue(left) ? "'" : "")
                        .append(isPrimitiveValue(left) ? left : "[complex value]")
                        .append(isStringValue(left) ? "'" : "")
                        .append(" to ")
                        .append(isStringValue(right) ? "'" : "")
                        .append(isPrimitiveValue(right) ? right : "[complex value]")
                        .append(isStringValue(right) ? "'" : "")
                        .append(System.lineSeparator());
            } else if (el.get("status").equals("added")) {
                var secondValue = el.get("secondValue");
                result.append("Property '")
                        .append(el.get("key"))
                        .append("' was added with value: ")
                        .append(isStringValue(secondValue) ? "'" : "")
                        .append(isPrimitiveValue(secondValue) ? secondValue : "[complex value]")
                        .append(isStringValue(secondValue) ? "'" : "")
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

    public static <T> boolean isPrimitiveValue(T str) {
        if (str != null) {
            return (Primitives.isWrapperType(str.getClass()) || str instanceof String);
        } else {
            return true;
        }
    }

    public static <T> boolean isStringValue(T str) {
        if (str != null) {
            return str instanceof String;
        }
        return false;
    }
}
