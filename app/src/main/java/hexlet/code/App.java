package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name="gendiff", mixinStandardHelpOptions = true, version = "helloWorld 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    //@Option(names = "--option", description = "Some option.")
    //String option;

    @Override
    public void run() {
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
