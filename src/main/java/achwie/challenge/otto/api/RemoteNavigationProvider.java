package achwie.challenge.otto.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import achwie.challenge.otto.core.NavParser;
import achwie.challenge.otto.core.in.NavNode;

/**
 * 
 * @author 26.07.2019, Achim Wiedemann
 *
 */
@Component
@Profile("!integrationtest")
public class RemoteNavigationProvider implements NavigationProvider {
  private static final String HEADER_FIELD_API_KEY = "x-api-key";
  private final URI navigationUri;
  private final String navigationApiKey;

  private final RestTemplate restTemplate;

  public RemoteNavigationProvider(RestTemplate restTemplate, @Value("${navigation.remote.url}") String navUrl,
      @Value("${navigation.remote.apikey}") String navApiKey)
      throws URISyntaxException {
    this.restTemplate = restTemplate;
    this.navigationUri = new URI(navUrl);
    this.navigationApiKey = navApiKey;
  }

  // TODO: Cache method invocation
  public NavNode getNavigation() {
    final String json = restTemplate.exchange(RequestEntity.get(navigationUri).header(HEADER_FIELD_API_KEY, navigationApiKey).build(), String.class).getBody();

    try {
      final var parser = new NavParser();
      return parser.parseNavigation(json);
    } catch (IOException e) {
      throw new RuntimeException("Couldn't get navigation from FS.", e);
    }
  }
}
