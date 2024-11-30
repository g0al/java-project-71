package hexlet.code;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.common.collect.Maps;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "helloWorld 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = "-f", paramLabel = "format", description = "output format [default: stylish]")
    private String format = "stylish";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filepath2;

    @Override
    public void run() {
        try {
            fileToMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void fileToMap() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        if (!Files.exists(path1) || !Files.exists(path2)) {
            throw new Exception("One of files does not exist");
        }

        Map<String, Object> firstFileMap = mapper.readValue(Files.readString(path1),
                new TypeReference<>() { });
        Map<String, Object> secondFileMap = mapper.readValue(Files.readString(path2),
                new TypeReference<>() { });
        var firstFileHashMap = new HashMap<>(firstFileMap);
        var secondFileHashMap = new HashMap<>(secondFileMap);

        MapDifference<String, Object> diff = Maps.difference(firstFileHashMap, secondFileHashMap);
        Map<String, ValueDifference<Object>> entriesDiffering = diff.entriesDiffering();
        Map<String, Object> entriesOnlyOnRight = diff.entriesOnlyOnRight();
        Map<String, Object> entriesOnlyOnLeft = diff.entriesOnlyOnLeft();
        Map<String, Object> entriesInCommon = diff.entriesInCommon();

        List<String> entries = new ArrayList<>();
        entriesDiffering.keySet().forEach(key -> {
            entries.add("- " + key + ": " + entriesDiffering.get(key).leftValue());
            entries.add("+ " + key + ": " + entriesDiffering.get(key).rightValue());
        });
        entriesInCommon.keySet().stream().map(key -> key + ": "
                + entriesInCommon.get(key)).forEach(entries::add);
        entriesOnlyOnLeft.keySet().stream().map(key -> "- " + key + ": "
                + entriesOnlyOnLeft.get(key)).forEach(entries::add);
        entriesOnlyOnRight.keySet().stream().map(key -> "+ " + key + ": "
                + entriesOnlyOnRight.get(key)).forEach(entries::add);

        var sortedEntries = entries.stream()
                .sorted(Comparator.comparing((String e) -> e.split(" ")[1]))
                .collect(Collectors.toCollection(ArrayList::new));

        var result = new StringBuilder();
        result.append("{");
        result.append(System.lineSeparator());
        for (var el : sortedEntries) {
            result.append("  ").append(el);
            result.append(System.lineSeparator());
        }
        result.append("}");
        System.out.println(result);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
