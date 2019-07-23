package achwie.challenge.otto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import achwie.challenge.otto.core.NavParser;
import achwie.challenge.otto.core.in.NavNode;

public class TestTools {
  public static final String FILE_SAMPLE_RESPONSE = "src/test/resources/sample-response.json";
  public static final String FILE_SAMPLE_SORTING = "src/test/resources/sample-sorting.json";

  public static String readFile(String fileName) throws IOException {
    return Files.readAllLines(Paths.get(fileName)).stream().collect(Collectors.joining());
  }

  public static NavNode parseNavigationFromFile(String fileName) throws IOException {
    final var parser = new NavParser();
    final var json = readFile(fileName);

    return parser.parseNavigation(json);
  }
}
