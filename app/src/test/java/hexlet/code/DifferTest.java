package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DifferTest {
    private static String resultNotFlatJson;
    private static String resultNotFlatYaml;
    private static String resultPlainFormatter;
    private static String resultStylishFormatter;
    private static String resultJsonFormatter;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultNotFlatJson = readFixture("result_json.txt");
        resultNotFlatYaml = readFixture("result_yaml.txt");
        resultStylishFormatter = readFixture("result_stylish_formatter.txt");
        resultPlainFormatter = readFixture("result_plain_formatter.txt");
        resultJsonFormatter = readFixture("result_json_formatter.txt");
    }

    @Test
    public void testNotFlatJson() throws Exception {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String diff = Differ.generate(filepath1, filepath2);
        assertEquals(resultNotFlatJson, diff);
    }

    @Test
    public void testNotFlatYaml() throws Exception {
        String filepath1 = "src/test/resources/file3.yaml";
        String filepath2 = "src/test/resources/file4.yaml";
        String diff = Differ.generate(filepath1, filepath2);
        assertEquals(resultNotFlatYaml, diff);
    }

    @Test
    public void testPlainFormatter() throws Exception {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String diff = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(resultPlainFormatter, diff);
    }

    @Test
    public void testStylishFormatter() throws Exception {
        String filepath1 = "src/test/resources/file3.yaml";
        String filepath2 = "src/test/resources/file4.yaml";
        String diff = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(resultStylishFormatter, diff);
    }

    @Test
    public void testJsonFormatter() throws Exception {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String diff = Differ.generate(filepath1, filepath2, "json");
        assertEquals(resultJsonFormatter, diff);
    }
}
