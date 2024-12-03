package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Diff;

public class Json {
    public static String json(Diff diff) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var diff1 = diff.getEntriesOnlyOnRight();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff1);
    }
}
