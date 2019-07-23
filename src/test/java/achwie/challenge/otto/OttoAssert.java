package achwie.challenge.otto;

import static org.junit.Assert.assertEquals;

import java.util.List;

import achwie.challenge.otto.core.out.Link;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class OttoAssert {
  public static void assertLinkEquals(String expectedLabel, String expectedUrl, List<Link> links, int index) {
    assertEquals(expectedLabel, links.get(index).getLabel());
    assertEquals(expectedUrl, links.get(index).getUrl());
  }
}
