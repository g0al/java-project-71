package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "helloWorld 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = "-f", defaultValue = "stylish",
            paramLabel = "format", description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private String filepath1;
    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    /**
     *Javadoc
     *
     * @author g0al
     *
     * @version 1.0
     */
    @Override
    public void run() {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
