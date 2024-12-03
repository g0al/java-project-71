package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FilenameUtils;
import org.yaml.snakeyaml.Yaml;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var ext1 = FilenameUtils.getExtension(filepath1);
        var ext2 = FilenameUtils.getExtension(filepath2);

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();

        if (Files.exists(path1) && Files.exists(path2)) {
            if (!ext1.equals(ext2)) {
                System.out.println("Файлы имеют различные расширения.");
            } else if (ext1.equals("json")) {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> firstFileMap = mapper.readValue(Files.readString(path1),
                        new TypeReference<>() {
                        });
                Map<String, Object> secondFileMap = mapper.readValue(Files.readString(path2),
                        new TypeReference<>() {
                        });
                return Parser.parse(firstFileMap, secondFileMap, format);
            } else if (ext1.equals("yaml") || ext1.equals("yml")) {
                Yaml yaml = new Yaml();
                Map<String, Object> firstFileMap = yaml.load(Files.readString(path1));
                Map<String, Object> secondFileMap = yaml.load(Files.readString(path2));
                return Parser.parse(firstFileMap, secondFileMap, format);
            }
        } else {
            throw new Exception("One of files does not exist");
        }
        return "";
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        String defaultStyle = "stylish";
        return Differ.generate(filepath1, filepath2, defaultStyle);
    }
}
