package hexlet.code;

import com.google.common.collect.MapDifference;

import java.util.Map;
import java.util.TreeSet;

public class Diff {
    private final Map<String, MapDifference.ValueDifference<Object>> entriesDiffering;
    private final Map<String, Object> entriesOnlyOnRight;
    private final Map<String, Object> entriesOnlyOnLeft;
    private final Map<String, Object> entriesInCommon;
    private final TreeSet<String> uniqueKeys;

    public Diff(Map<String, MapDifference.ValueDifference<Object>> entriesDiffering,
                Map<String, Object> entriesOnlyOnRight,
                Map<String, Object> entriesOnlyOnLeft,
                Map<String, Object> entriesInCommon,
                TreeSet<String> set1) {
        this.entriesDiffering = entriesDiffering;
        this.entriesOnlyOnRight = entriesOnlyOnRight;
        this.entriesOnlyOnLeft = entriesOnlyOnLeft;
        this.entriesInCommon = entriesInCommon;
        this.uniqueKeys = set1;
    }

    /**
     * Javadoc.
     *
     * @return entries
     *
     */
    public Map<String, MapDifference.ValueDifference<Object>> getEntriesDiffering() {
        return entriesDiffering;
    }

    /**
     * Javadoc.
     *
     * @return entries
     *
     */
    public Map<String, Object> getEntriesOnlyOnRight() {
        return entriesOnlyOnRight;
    }

    /**
     * Javadoc.
     *
     * @return entries
     *
     */
    public Map<String, Object> getEntriesOnlyOnLeft() {
        return entriesOnlyOnLeft;
    }

    /**
     * Javadoc.
     *
     * @return entries
     *
     */
    public Map<String, Object> getEntriesInCommon() {
        return entriesInCommon;
    }

    /**
     * Javadoc.
     *
     * @return keys
     *
     */
    public TreeSet<String> getUniqueKeys() {
        return uniqueKeys;
    }
}
