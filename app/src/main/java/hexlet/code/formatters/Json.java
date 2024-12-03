package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Diff;

import java.util.HashMap;

public class Json {
    public static String json(Diff diff) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        var diff1 = new HashMap<String, Object>(diff.getEntriesOnlyOnRight());
        var diff2 = diff.getEntriesOnlyOnLeft();
        var diff3 = diff.getEntriesInCommon();
        for (var el : diff.getUniqueKeys()) {
            var diff4 = diff.getEntriesDiffering().get(el);
        }
        diff1.putAll(diff2);
        diff1.putAll(diff3);
        //diff1.putAll();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(diff1);
    }
}
