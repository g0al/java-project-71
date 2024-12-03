package hexlet.code.formatters;

import hexlet.code.Diff;
import com.google.common.primitives.Primitives;

public class Plain {
    public static String plain(Diff diff) {
        var result = new StringBuilder();
        for (var el : diff.getUniqueKeys()) {
            if (diff.getEntriesDiffering().containsKey(el)) {
                var left = diff.getEntriesDiffering().get(el).leftValue();
                var right = diff.getEntriesDiffering().get(el).rightValue();
                result.append("Property '")
                        .append(el)
                        .append("' was updated. From ")
                        .append(isStringValue(left) ? "'" : "")
                        .append(isPrimitiveValue(left) ? left : "[complex value]")
                        .append(isStringValue(left) ? "'" : "")
                        .append(" to ")
                        .append(isStringValue(right) ? "'" : "")
                        .append(isPrimitiveValue(right) ? right : "[complex value]")
                        .append(isStringValue(right) ? "'" : "")
                        .append(System.lineSeparator());
            } else if (diff.getEntriesOnlyOnRight().containsKey(el)) {
                var only = diff.getEntriesOnlyOnRight().get(el);
                result.append("Property '")
                        .append(el)
                        .append("' was added with value: ")
                        .append(isStringValue(only) ? "'" : "")
                        .append(isPrimitiveValue(only) ? only : "[complex value]")
                        .append(isStringValue(only) ? "'" : "")
                        .append(System.lineSeparator());
            } else if (diff.getEntriesOnlyOnLeft().containsKey(el)) {
                result.append("Property '")
                        .append(el)
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
