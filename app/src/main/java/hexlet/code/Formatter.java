package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Json;

public class Formatter {
    public static String chooseFormatter(Diff diff, String format) throws JsonProcessingException {
        return switch (format) {
            case "plain" -> Plain.plain(diff);
            case "stylish" -> Stylish.stylish(diff);
            case "json" -> Json.json(diff);
            default -> "";
        };
    }
}
