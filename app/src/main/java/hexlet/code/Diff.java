package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class Diff {
    public static List<Map<String, Object>> diff(List<Map<String, Object>> data) {
        var map1 = data.get(0);
        var map2 = data.get(1);

        var set1 = new TreeSet<>(map1.keySet());
        var set2 = new TreeSet<>(map2.keySet());
        set1.addAll(set2);

        List<Map<String, Object>> result = new ArrayList<>();

        for (var el : set1) {
            if (!map1.containsKey(el)) {
                Map<String, Object> diffMap = Map.of(
                        "key", el,
                        "status", "added",
                        "value", map2.get(el));
                SortedMap<String, Object> sortedMap = new TreeMap<>(diffMap);
                result.add(sortedMap);
            } else if (!map2.containsKey(el)) {
                Map<String, Object> diffMap = Map.of(
                        "key", el,
                        "status", "removed",
                        "value", map1.get(el));
                SortedMap<String, Object> sortedMap = new TreeMap<>(diffMap);
                result.add(sortedMap);
            } else if (map1.containsKey(el) && map2.containsKey(el)) {
                if (Objects.equals(map1.get(el), map2.get(el))) {
                    Map<String, Object> diffMap = Map.of(
                            "key", el,
                            "status", "unchanged",
                            "value", map1.get(el));
                    SortedMap<String, Object> sortedMap = new TreeMap<>(diffMap);
                    result.add(sortedMap);
                } else {
                    Map<String, Object> diffMap = new HashMap<>(Map.of(
                            "key", el,
                            "status", "changed"));
                    diffMap.put("value1", map1.get(el));
                    diffMap.put("value2", map2.get(el));
                    SortedMap<String, Object> sortedMap = new TreeMap<>(diffMap);
                    result.add(sortedMap);
                }
            }
        }
        return result;
    }
}
