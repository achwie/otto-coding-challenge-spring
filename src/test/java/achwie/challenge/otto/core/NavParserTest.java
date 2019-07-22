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
public class NavParserTest {
  private final NavParser navParser = new NavParser();
  private final String sampleResponse = TestTools.readFile(TestTools.FILE_SAMPLE_RESPONSE);

  public NavParserTest() throws IOException {
  }

  @Test
  public void parseNavigation_structureIsValid() throws IOException {
    final var navRoot = navParser.parseNavigation(sampleResponse);

    final var sortiment = navRoot.getChild(0);
    final var alter = sortiment.getChild(0);

    assertEquals("Sortiment", sortiment.getLabel());
    assertEquals("Alter", alter.getLabel());
    assertEquals(2, alter.getChildren().size());
    assertEquals("Baby & Kleinkind", alter.getChild(0).getLabel());
    assertEquals("Kindergarten", alter.getChild(1).getLabel());
  }

  @Test
  public void parseNavigation_sectionPopulatedCorrectly() throws IOException {
    final var navRoot = navParser.parseNavigation(sampleResponse);
    final var section = navRoot.getChild(0);

    assertEquals(NavNode.TYPE_SECTION, section.getType());
    assertEquals("Sortiment", section.getLabel());
    assertEquals(null, section.getUrl());
    assertEquals(1, section.getChildren().size());
  }

  @Test
  public void parseNavigation_nodePopulatedCorrectly() throws IOException {
    final var navRoot = navParser.parseNavigation(sampleResponse);
    final var node = navRoot.getChild(0).getChild(0);

    assertEquals(NavNode.TYPE_NODE, node.getType());
    assertEquals("Alter", node.getLabel());
    assertEquals(null, node.getUrl());
    assertEquals(2, node.getChildren().size());
  }

  @Test
  public void parseNavigation_linkPopulatedCorrectly() throws IOException {
    final var navRoot = navParser.parseNavigation(sampleResponse);
    final var link = navRoot.getChild(0).getChild(0).getChild(0).getChild(0);

    assertEquals(NavNode.TYPE_LINK, link.getType());
    assertEquals("0-6 Monate", link.getLabel());
    assertEquals("http://www.mytoys.de/0-6-months/", link.getUrl());
    assertEquals(0, link.getChildren().size());
  }
}
