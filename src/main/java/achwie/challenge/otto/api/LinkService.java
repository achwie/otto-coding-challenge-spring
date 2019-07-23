package achwie.challenge.otto.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import achwie.challenge.otto.core.FieldOrder;
import achwie.challenge.otto.core.NavParser;
import achwie.challenge.otto.core.NavQuery;
import achwie.challenge.otto.core.in.NavNode;
import achwie.challenge.otto.core.out.Link;

/**
 * 
 * @author 23.07.2019, Achim Wiedemann
 *
 */
@Service
public class LinkService {

  public List<Link> getLinks(String parentFilter, FieldOrder... fieldOrders) {
    final var navRoot = getNavigation();
    return new NavQuery(navRoot).query(parentFilter, fieldOrders);
  }

  private NavNode getNavigation() {
    try {
      return parseNavigationFromFile(FILE_SAMPLE_RESPONSE);
    } catch (IOException e) {
      throw new RuntimeException("Couldn't get navigation from FS.", e);
    }
  }

  private static final String FILE_SAMPLE_RESPONSE = "src/test/resources/sample-response.json";

  private String readFile(String fileName) throws IOException {
    return Files.readAllLines(Paths.get(fileName)).stream().collect(Collectors.joining());
  }

  private NavNode parseNavigationFromFile(String fileName) throws IOException {
    final var parser = new NavParser();
    final var json = readFile(fileName);

    return parser.parseNavigation(json);
  }
}
