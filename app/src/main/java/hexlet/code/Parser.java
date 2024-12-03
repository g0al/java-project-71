package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Parser {
    public static String parse(Map<String, Object> firstFileMap,
                               Map<String, Object> secondFileMap,
                               String format) throws JsonProcessingException {
        var set1 = new TreeSet<String>(firstFileMap.keySet());
        var set2 = new TreeSet<String>(secondFileMap.keySet());
        set1.addAll(set2);
        var firstFileHashMap = new HashMap<>(firstFileMap);
        var secondFileHashMap = new HashMap<>(secondFileMap);

        MapDifference<String, Object> diff = Maps.difference(firstFileHashMap, secondFileHashMap);
        Map<String, MapDifference.ValueDifference<Object>> entriesDiffering = diff.entriesDiffering();
        Map<String, Object> entriesOnlyOnRight = diff.entriesOnlyOnRight();
        Map<String, Object> entriesOnlyOnLeft = diff.entriesOnlyOnLeft();
        Map<String, Object> entriesInCommon = diff.entriesInCommon();

        var finalDiff = new Diff(entriesDiffering,
                entriesOnlyOnRight,
                entriesOnlyOnLeft,
                entriesInCommon,
                set1);

        return Formatter.chooseFormatter(finalDiff, format);
    }
}
