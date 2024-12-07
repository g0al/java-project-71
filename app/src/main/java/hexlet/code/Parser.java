package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Parser {
    public static String parse(Map<String, Object> firstFileMap,
                               Map<String, Object> secondFileMap,
                               String format) throws JsonProcessingException {
        var firstFileSet = new TreeSet<>(firstFileMap.keySet());
        var secondFileSet = new TreeSet<>(secondFileMap.keySet());
        firstFileSet.addAll(secondFileSet);

        MapDifference<String, Object> diff = Maps.difference(firstFileMap, secondFileMap);
        Map<String, MapDifference.ValueDifference<Object>> entriesDiffering = diff.entriesDiffering();
        Map<String, Object> entriesOnlyOnRight = diff.entriesOnlyOnRight();
        Map<String, Object> entriesOnlyOnLeft = diff.entriesOnlyOnLeft();
        Map<String, Object> entriesInCommon = diff.entriesInCommon();

        List<Map<String, Object>> result = new ArrayList<>();
        for (var key : firstFileSet) {
            if (entriesDiffering.containsKey(key)) {
                var left = entriesDiffering.get(key).leftValue();
                var right = entriesDiffering.get(key).rightValue();
                Map<String, Object> diffMap = new HashMap<>(Map.of(
                        "key", key,
                        "status", "changed"));
                diffMap.put("firstValue", left);
                diffMap.put("secondValue", right);
                result.add(diffMap);
            } else if (entriesOnlyOnRight.containsKey(key)) {
                var right = entriesOnlyOnRight.get(key);
                Map<String, Object> diffMap = new HashMap<>(Map.of(
                        "key", key,
                        "status", "added"));
                diffMap.put("secondValue", right);
                result.add(diffMap);
            } else if (entriesOnlyOnLeft.containsKey(key)) {
                var left = entriesOnlyOnLeft.get(key);
                Map<String, Object> diffMap = Map.of(
                        "key", key,
                        "status", "removed",
                        "firstValue", left);
                result.add(diffMap);
            } else if (entriesInCommon.containsKey(key)) {
                var common = entriesInCommon.get(key);
                Map<String, Object> diffMap = Map.of(
                        "key", key,
                        "status", "unchanged",
                        "firstValue", common);
                result.add(diffMap);
            }
        }
        return Formatter.chooseFormatter(result, format);
    }
}
