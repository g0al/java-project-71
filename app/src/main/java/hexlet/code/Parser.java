package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static List<Map<String, Object>> parse(String content1,
                                                  String content2,
                                                  String extension)
            throws JsonProcessingException {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        if (extension.equals("json")) {
            ObjectMapper mapper = new ObjectMapper();
            map1 = mapper.readValue(content1, new TypeReference<>() { });
            map2 = mapper.readValue(content2, new TypeReference<>() { });
        } else if (extension.equals("yaml") || extension.equals("yml")) {
            Yaml yaml = new Yaml();
            map1 = yaml.load(content1);
            map2 = yaml.load(content2);
        }
        return List.of(map1, map2);
    }
}
