package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DifferTest {

    private static String resultJson;
    private static String resultJson2;
    private static String resultYaml;
    private static String resultYaml2;
    private static String resultPlainJson;
    private static String resultPlainYaml;

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
        resultJson = readFixture("result_json.txt");
        resultJson2 = readFixture("result_json2.json");
        resultYaml = readFixture("result_yaml.txt");
        resultYaml2 = readFixture("result_yaml2.txt");
        resultPlainJson = readFixture("result_plain_json.txt");
        resultPlainYaml = readFixture("result_plain_yaml.txt");
    }

    @Test
    public void testFlatJson() throws Exception {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String diff = Differ.generate(filepath1, filepath2);
        assertEquals(resultJson, diff);
    }

    @Test
    public void testNotFlatJson() throws Exception {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String diff = Differ.generate(filepath1, filepath2);
        assertEquals(resultJson2, diff);
    }

    @Test
    public void testFlatYaml() throws Exception {
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        String diff = Differ.generate(filepath1, filepath2);
        assertEquals(resultYaml, diff);
    }

    @Test
    public void testNotFlatYaml() throws Exception {
        String filepath1 = "src/test/resources/file3.yaml";
        String filepath2 = "src/test/resources/file4.yaml";
        String diff = Differ.generate(filepath1, filepath2);
        assertEquals(resultYaml2, diff);
    }

    @Test
    public void testNotFlatPlainJson() throws Exception {
        String filepath1 = "src/test/resources/file3.json";
        String filepath2 = "src/test/resources/file4.json";
        String diff = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(resultPlainJson, diff);
    }

    @Test
    public void testNotFlatPlainYaml() throws Exception {
        String filepath1 = "src/test/resources/file3.yaml";
        String filepath2 = "src/test/resources/file4.yaml";
        String diff = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(resultPlainYaml, diff);
    }
}
