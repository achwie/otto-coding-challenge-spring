package achwie.challenge.otto.core;

import static achwie.challenge.otto.OttoAssert.assertLinkEquals;
import static achwie.challenge.otto.core.FieldOrder.orderBy;
import static achwie.challenge.otto.core.SortOrder.ASC;
import static achwie.challenge.otto.core.SortOrder.DESC;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import achwie.challenge.otto.TestTools;
import achwie.challenge.otto.core.in.NavNode;

public class NavQueryTest {
  private static final String PARENT_FILTER = "Test Links";
  private final NavNode navRoot = TestTools.parseNavigationFromFile(TestTools.FILE_SAMPLE_SORTING);

  public NavQueryTest() throws IOException {
  }

  @Test
  public void run_noSort() {
    final var links = new NavQuery(navRoot).query(PARENT_FILTER);

    assertEquals(4, links.size());

    assertLinkEquals("Test Links - 1st link", "https://c.example.org/", links, 0);
    assertLinkEquals("Test Links - 3rd link", "https://a.example.org/", links, 1);
    assertLinkEquals("Test Links - 3rd link", "https://d.example.org/", links, 2);
    assertLinkEquals("Test Links - 2nd link", "https://b.example.org/", links, 3);
  }

  @Test
  public void run_sortByLabelDefault() {
    final var links = new NavQuery(navRoot).query(PARENT_FILTER, orderBy("label"));

    assertEquals(4, links.size());

    assertLinkEquals("Test Links - 1st link", "https://c.example.org/", links, 0);
    assertLinkEquals("Test Links - 2nd link", "https://b.example.org/", links, 1);
    assertLinkEquals("Test Links - 3rd link", "https://a.example.org/", links, 2);
    assertLinkEquals("Test Links - 3rd link", "https://d.example.org/", links, 3);
  }

  @Test
  public void run_sortByLabelAsc() {
    final var links = new NavQuery(navRoot).query(PARENT_FILTER, orderBy("label", ASC));

    assertEquals(4, links.size());

    assertLinkEquals("Test Links - 1st link", "https://c.example.org/", links, 0);
    assertLinkEquals("Test Links - 2nd link", "https://b.example.org/", links, 1);
    assertLinkEquals("Test Links - 3rd link", "https://a.example.org/", links, 2);
    assertLinkEquals("Test Links - 3rd link", "https://d.example.org/", links, 3);
  }

  @Test
  public void run_sortByLabelDesc() {
    final var links = new NavQuery(navRoot).query(PARENT_FILTER, orderBy("label", DESC));

    assertEquals(4, links.size());

    assertLinkEquals("Test Links - 3rd link", "https://a.example.org/", links, 0);
    assertLinkEquals("Test Links - 3rd link", "https://d.example.org/", links, 1);
    assertLinkEquals("Test Links - 2nd link", "https://b.example.org/", links, 2);
    assertLinkEquals("Test Links - 1st link", "https://c.example.org/", links, 3);
  }

  @Test
  public void run_sortByUrlDesc() {
    final var links = new NavQuery(navRoot).query(PARENT_FILTER, orderBy("url", DESC));

    assertEquals(4, links.size());

    assertLinkEquals("Test Links - 3rd link", "https://d.example.org/", links, 0);
    assertLinkEquals("Test Links - 1st link", "https://c.example.org/", links, 1);
    assertLinkEquals("Test Links - 2nd link", "https://b.example.org/", links, 2);
    assertLinkEquals("Test Links - 3rd link", "https://a.example.org/", links, 3);
  }

  @Test
  public void run_sortByLabelDescUrlDesc() {
    final var links = new NavQuery(navRoot).query(PARENT_FILTER, orderBy("label", DESC), orderBy("url", DESC));

    assertEquals(4, links.size());

    assertLinkEquals("Test Links - 3rd link", "https://d.example.org/", links, 0);
    assertLinkEquals("Test Links - 3rd link", "https://a.example.org/", links, 1);
    assertLinkEquals("Test Links - 2nd link", "https://b.example.org/", links, 2);
    assertLinkEquals("Test Links - 1st link", "https://c.example.org/", links, 3);
  }

  @Test
  public void run_sortByLabelDescUrlAsc() {
    final var links = new NavQuery(navRoot).query(PARENT_FILTER, orderBy("label", DESC), orderBy("url", ASC));

    assertEquals(4, links.size());

    assertLinkEquals("Test Links - 3rd link", "https://a.example.org/", links, 0);
    assertLinkEquals("Test Links - 3rd link", "https://d.example.org/", links, 1);
    assertLinkEquals("Test Links - 2nd link", "https://b.example.org/", links, 2);
    assertLinkEquals("Test Links - 1st link", "https://c.example.org/", links, 3);
  }
}
