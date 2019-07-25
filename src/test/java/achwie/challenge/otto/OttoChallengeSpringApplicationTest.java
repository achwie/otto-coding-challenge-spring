package achwie.challenge.otto;

import static achwie.challenge.otto.OttoAssert.assertLinkEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import achwie.challenge.otto.core.out.Link;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OttoChallengeSpringApplicationTest {
  @Autowired
  private TestRestTemplate restTemplate;

  @Bean
  public TestRestTemplate restTemplate(@LocalServerPort int serverPort) {
    return new TestRestTemplate(new RestTemplateBuilder().rootUri("http://localhost:" + serverPort));
  }

  @Test
  public void links() {
    final var response = restTemplate.getForEntity("/links", Link[].class);
    final List<Link> links = Arrays.asList(response.getBody());

    assertEquals(5, links.size());

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 0-6 Monate", "http://www.mytoys.de/0-6-months/", links, 0);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 7-12 Monate", "http://www.mytoys.de/7-12-months/", links, 1);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 13-24 Monate", "http://www.mytoys.de/13-24-months/", links, 2);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 2-3 Jahre", "http://www.mytoys.de/24-47-months/", links, 3);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 4-5 Jahre", "http://www.mytoys.de/48-71-months/", links, 4);
  }

  @Test
  public void links_withSort() {
    final var urlParams = new HashMap<String, Object>();
    urlParams.put("sort", "url:asc");

    final var response = restTemplate.getForEntity("/links?sort={sort}", Link[].class, urlParams);
    final List<Link> links = Arrays.asList(response.getBody());

    assertEquals(5, links.size());

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 0-6 Monate", "http://www.mytoys.de/0-6-months/", links, 0);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 13-24 Monate", "http://www.mytoys.de/13-24-months/", links, 1);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 2-3 Jahre", "http://www.mytoys.de/24-47-months/", links, 2);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 4-5 Jahre", "http://www.mytoys.de/48-71-months/", links, 3);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 7-12 Monate", "http://www.mytoys.de/7-12-months/", links, 4);
  }

  @Test
  public void links_withNonFilteringParent() {
    final var response = restTemplate.getForEntity("/links", Link[].class);
    final List<Link> links = Arrays.asList(response.getBody());

    assertEquals(5, links.size());

    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 0-6 Monate", "http://www.mytoys.de/0-6-months/", links, 0);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 7-12 Monate", "http://www.mytoys.de/7-12-months/", links, 1);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 13-24 Monate", "http://www.mytoys.de/13-24-months/", links, 2);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 2-3 Jahre", "http://www.mytoys.de/24-47-months/", links, 3);
    assertLinkEquals("Sortiment - Alter - Kindergarten - 4-5 Jahre", "http://www.mytoys.de/48-71-months/", links, 4);
  }

  @Test
  public void links_withFilteringParent() {
    final var urlParams = new HashMap<String, Object>();
    urlParams.put("parent", "Baby & Kleinkind");

    final var response = restTemplate.getForEntity("/links?parent={parent}", Link[].class, urlParams);
    final List<Link> links = Arrays.asList(response.getBody());

    assertEquals(3, links.size());

    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 0-6 Monate", "http://www.mytoys.de/0-6-months/", links, 0);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 7-12 Monate", "http://www.mytoys.de/7-12-months/", links, 1);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 13-24 Monate", "http://www.mytoys.de/13-24-months/", links, 2);
  }

  @Test
  public void links_withFilteringParentAndSort() {
    final var urlParams = new HashMap<String, Object>();
    urlParams.put("parent", "Baby & Kleinkind");
    urlParams.put("sort", "label:desc,url:asc");

    final var response = restTemplate.getForEntity("/links?parent={parent}&sort={sort}", Link[].class, urlParams);
    final List<Link> links = Arrays.asList(response.getBody());

    assertEquals(3, links.size());

    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 7-12 Monate", "http://www.mytoys.de/7-12-months/", links, 0);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 13-24 Monate", "http://www.mytoys.de/13-24-months/", links, 1);
    assertLinkEquals("Sortiment - Alter - Baby & Kleinkind - 0-6 Monate", "http://www.mytoys.de/0-6-months/", links, 2);
  }

  @Test
  public void links_withInvalidSort() {
    final var urlParams = new HashMap<String, Object>();
    urlParams.put("sort", "lkjasd:asdasd:asd,label:desc,:;::asd,,,adssad");

    final var responseEntity = restTemplate.getForEntity("/links?sort={sort}", Object.class, urlParams);
    final HttpStatus actualStatus = responseEntity.getStatusCode();

    assertEquals(HttpStatus.BAD_REQUEST, actualStatus);
  }
}
