package achwie.challenge.otto.core;

import java.util.Collections;
import java.util.List;

import achwie.challenge.otto.core.in.NavNode;
import achwie.challenge.otto.core.node.TreeWalker;
import achwie.challenge.otto.core.out.Link;

/**
 * 
 * @author 23.07.2019, Achim Wiedemann
 *
 */
public class NavQuery {
  private final NavNode root;

  public NavQuery(NavNode root) {
    this.root = root;
  }

  public List<Link> query(String parentFilter, FieldOrder... fieldOrders) {
    final var links = new TreeWalker().walk(root, new NavNodeToLinkVisitor(parentFilter)).getLinks();

    if (fieldOrders.length > 0)
      Collections.sort(links, new PropertyComparator<Link>(Link.class, fieldOrders));

    return links;
  }
}
