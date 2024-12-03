package hexlet.code.formatters;

import hexlet.code.Diff;

public class Stylish {
    public static String stylish(Diff diff) {
        var result = new StringBuilder();
        result.append("{").append(System.lineSeparator());
        for (var el : diff.getUniqueKeys()) {
            if (diff.getEntriesDiffering().containsKey(el)) {
                result.append("  - ")
                        .append(el)
                        .append(": ")
                        .append(diff.getEntriesDiffering().get(el).leftValue())
                        .append(System.lineSeparator());
                result.append("  + ")
                        .append(el)
                        .append(": ")
                        .append(diff.getEntriesDiffering().get(el).rightValue())
                        .append(System.lineSeparator());
            } else if (diff.getEntriesOnlyOnRight().containsKey(el)) {
                result.append("  + ")
                        .append(el)
                        .append(": ")
                        .append(diff.getEntriesOnlyOnRight().get(el))
                        .append(System.lineSeparator());
            } else if (diff.getEntriesOnlyOnLeft().containsKey(el)) {
                result.append("  - ")
                        .append(el)
                        .append(": ")
                        .append(diff.getEntriesOnlyOnLeft().get(el))
                        .append(System.lineSeparator());
            } else if (diff.getEntriesInCommon().containsKey(el)) {
                result.append("    ")
                        .append(el)
                        .append(": ")
                        .append(diff.getEntriesInCommon().get(el))
                        .append(System.lineSeparator());
            }
        }
        result.append("}");
        return result.toString();
    }
}
