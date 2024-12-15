package hexlet.code;

import org.apache.commons.io.FilenameUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Differ {

    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        final String[] extensions = {"json", "yaml", "yml"};

        var ext1 = FilenameUtils.getExtension(filepath1);
        var ext2 = FilenameUtils.getExtension(filepath2);

        var path1 = getPath(filepath1);
        var path2 = getPath(filepath2);

        if (Files.exists(path1) && Files.exists(path2)) {
            if (!ext1.equals(ext2)) {
                throw new Exception("Extensions of files does not match.");
            } else if (Arrays.asList(extensions).contains(ext1)) {
                String content1 = new String(Files.readAllBytes(path1));
                String content2 = new String(Files.readAllBytes(path2));
                var parsedData = Parser.parse(content1, content2, ext1);
                var diffData = Diff.diff(parsedData);
                return Formatter.chooseFormatter(diffData, format);
            } else {
                throw new Exception("One of files does not exist");
            }
        }
        return "";
    }

    public static String generate(String filepath1,
                                  String filepath2) throws Exception {
        String defaultStyle = "stylish";
        return Differ.generate(filepath1, filepath2, defaultStyle);
    }

    public static Path getPath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }
}
