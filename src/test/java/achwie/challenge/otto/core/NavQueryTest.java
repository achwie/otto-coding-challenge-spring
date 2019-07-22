package achwie.challenge.otto.core;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import achwie.challenge.otto.TestTools;
import achwie.challenge.otto.core.in.NavNode;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class NavQueryTest {
  private final NavNode navRoot = TestTools.parseNavigationFromFile(TestTools.FILE_SAMPLE_RESPONSE);
  private final NavQuery navQuery = new NavQuery(navRoot);

  public NavQueryTest() throws IOException {
  }

  @Test
  public void getLinks() {
    final var links = navQuery.getLinks();

    assertEquals(5, links.size());

    // TODO: Consider parameterized test
    assertEquals("Sortiment - Alter - Baby & Kleinkind - 0-6 Monate", links.get(0).getLabel());
    assertEquals("http://www.mytoys.de/0-6-months/", links.get(0).getUrl());

    assertEquals("Sortiment - Alter - Baby & Kleinkind - 7-12 Monate", links.get(1).getLabel());
    assertEquals("http://www.mytoys.de/7-12-months/", links.get(1).getUrl());

    assertEquals("Sortiment - Alter - Baby & Kleinkind - 13-24 Monate", links.get(2).getLabel());
    assertEquals("http://www.mytoys.de/13-24-months/", links.get(2).getUrl());

    assertEquals("Sortiment - Alter - Kindergarten - 2-3 Jahre", links.get(3).getLabel());
    assertEquals("http://www.mytoys.de/24-47-months/", links.get(3).getUrl());

    assertEquals("Sortiment - Alter - Kindergarten - 4-5 Jahre", links.get(4).getLabel());
    assertEquals("http://www.mytoys.de/48-71-months/", links.get(4).getUrl());
  }
}
