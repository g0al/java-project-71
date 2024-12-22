package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class DifferTest {
    private static String resultWithoutFormat;
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
        resultWithoutFormat = readFixture("result_without_format.txt");
        resultStylishFormatter = readFixture("result_stylish_formatter.txt");
        resultPlainFormatter = readFixture("result_plain_formatter.txt");
        resultJsonFormatter = readFixture("result_json_formatter.txt");
    }

    @ParameterizedTest
    @ValueSource(strings = { "json", "yaml" })
    public void testDiff(String ext) throws Exception {
        String filepath1 = String.format("src/test/resources/file3.%s", ext);
        String filepath2 = String.format("src/test/resources/file4.%s", ext);
        String diff1 = Differ.generate(filepath1, filepath2, "plain");
        String diff2 = Differ.generate(filepath1, filepath2, "stylish");
        String diff3 = Differ.generate(filepath1, filepath2, "json");
        String diff4 = Differ.generate(filepath1, filepath2);
        assertEquals(resultPlainFormatter, diff1);
        assertEquals(resultStylishFormatter, diff2);
        assertEquals(resultJsonFormatter, diff3);
        assertEquals(resultWithoutFormat, diff4);
    }
}
