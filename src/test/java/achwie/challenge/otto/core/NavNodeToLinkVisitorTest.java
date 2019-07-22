package achwie.challenge.otto.core;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import achwie.challenge.otto.TestTools;
import achwie.challenge.otto.core.in.NavNode;
import achwie.challenge.otto.core.node.TreeWalker;
import achwie.challenge.otto.core.out.Link;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class NavNodeToLinkVisitorTest {
  private final NavNode navRoot = TestTools.parseNavigationFromFile(TestTools.FILE_SAMPLE_RESPONSE);
  private final TreeWalker treeWalker = new TreeWalker();

  public NavNodeToLinkVisitorTest() throws IOException {
  }

  @Test
  public void getLinks() {
    final var links = treeWalker.walk(navRoot, new NavNodeToLinkVisitor()).getLinks();

    assertEquals(5, links.size());

    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 0-6 Monate", "http://www.mytoys.de/0-6-months/", links, 0);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 7-12 Monate", "http://www.mytoys.de/7-12-months/", links, 1);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 13-24 Monate", "http://www.mytoys.de/13-24-months/", links, 2);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 2-3 Jahre", "http://www.mytoys.de/24-47-months/", links, 3);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 4-5 Jahre", "http://www.mytoys.de/48-71-months/", links, 4);
  }

  @Test
  public void getLinks_withNonFilteringParent() {
    final var links = treeWalker.walk(navRoot, new NavNodeToLinkVisitor("Alter")).getLinks();

    assertEquals(5, links.size());

    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 0-6 Monate", "http://www.mytoys.de/0-6-months/", links, 0);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 7-12 Monate", "http://www.mytoys.de/7-12-months/", links, 1);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 13-24 Monate", "http://www.mytoys.de/13-24-months/", links, 2);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 2-3 Jahre", "http://www.mytoys.de/24-47-months/", links, 3);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 4-5 Jahre", "http://www.mytoys.de/48-71-months/", links, 4);
  }

  @Test
  public void getLinks_withFilteringParent() {
    final var links = treeWalker.walk(navRoot, new NavNodeToLinkVisitor("Baby & Kleinkind")).getLinks();

    assertEquals(3, links.size());

    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 0-6 Monate", "http://www.mytoys.de/0-6-months/", links, 0);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 7-12 Monate", "http://www.mytoys.de/7-12-months/", links, 1);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 13-24 Monate", "http://www.mytoys.de/13-24-months/", links, 2);
  }

  // -- End of Tests -----------------------------------------------------------
  private void assertLinkEquals(String expectedLabel, String expectedUrl, List<Link> links, int index) {
    assertEquals(expectedLabel, links.get(index).getLabel());
    assertEquals(expectedUrl, links.get(index).getUrl());
  }
}
