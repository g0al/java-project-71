package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class Diff {
    public static List<Map<String, Object>> diff(Map<String, Object> map1,
                                                 Map<String, Object> map2) {
        var keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        List<Map<String, Object>> result = new ArrayList<>();

        for (var key : keys) {
            Map<String, Object> diffMap = new LinkedHashMap<>();
            diffMap.put("key", key);
            if (!map1.containsKey(key)) {
                diffMap.put("status", "added");
                diffMap.put("value", map2.get(key));
            } else if (!map2.containsKey(key)) {
                diffMap.put("status", "removed");
                diffMap.put("value", map1.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                diffMap.put("status", "unchanged");
                diffMap.put("value", map1.get(key));
            } else {
                diffMap.put("status", "changed");
                diffMap.put("value1", map1.get(key));
                diffMap.put("value2", map2.get(key));
            }
            result.add(diffMap);
        }
        return result;
    }
}
