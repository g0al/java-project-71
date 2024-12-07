package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import hexlet.code.formatters.Json;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String chooseFormatter(List<Map<String, Object>> diff, String format) throws JsonProcessingException {
        return switch (format) {
            case "plain" -> Plain.plain(diff);
            case "stylish" -> Stylish.stylish(diff);
            case "json" -> Json.json(diff);
            default -> "Wrong format.";
        };
    }
}
