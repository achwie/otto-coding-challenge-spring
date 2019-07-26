package achwie.challenge.otto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import achwie.challenge.otto.api.NavigationProvider;
import achwie.challenge.otto.core.NavParser;
import achwie.challenge.otto.core.in.NavNode;

/**
 * 
 * @author 26.07.2019, Achim Wiedemann
 *
 */
@Component
@Profile("integrationtest")
public class TestNavigationProvider implements NavigationProvider {
  private static final String FILE_SAMPLE_RESPONSE = "src/test/resources/sample-response.json";

  public NavNode getNavigation() {
    try {
      return parseNavigationFromFile(FILE_SAMPLE_RESPONSE);
    } catch (IOException e) {
      throw new RuntimeException("Couldn't get navigation from FS.", e);
    }
  }

  private String readFile(String fileName) throws IOException {
    return Files.readAllLines(Paths.get(fileName)).stream().collect(Collectors.joining());
  }

  private NavNode parseNavigationFromFile(String fileName) throws IOException {
    final var parser = new NavParser();
    final var json = readFile(fileName);

    return parser.parseNavigation(json);
  }
}
