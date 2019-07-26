package achwie.challenge.otto.api;

import java.util.List;

import org.springframework.stereotype.Service;

import achwie.challenge.otto.core.FieldOrder;
import achwie.challenge.otto.core.NavQuery;
import achwie.challenge.otto.core.out.Link;

/**
 * 
 * @author 23.07.2019, Achim Wiedemann
 *
 */
@Service
public class LinkService {
  private final NavigationProvider navigationProvider;

  public LinkService(NavigationProvider navigationProvider) {
    this.navigationProvider = navigationProvider;
  }

  public List<Link> getLinks(String parentFilter, FieldOrder... fieldOrders) {
    final var navRoot = navigationProvider.getNavigation();
    return new NavQuery(navRoot).query(parentFilter, fieldOrders);
  }
}
