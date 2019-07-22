package achwie.challenge.otto.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import achwie.challenge.otto.core.in.NavNode;
import achwie.challenge.otto.core.out.Link;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class NavQuery {
  private final NavNode navRoot;

  public NavQuery(NavNode navRoot) {
    this.navRoot = navRoot;
  }

  public List<Link> getLinks() {
    final List<Link> links = new ArrayList<Link>();
    final List<NavNode> currentPath = new ArrayList<>();

    collectLinks(navRoot, currentPath, links);

    return links;
  }

  private void collectLinks(NavNode node, List<NavNode> currentPath, List<Link> collectedLinks) {
    if (node == null)
      return;

    currentPath.add(node);

    if (node.isType(NavNode.TYPE_LINK)) {
      final var labelWithPath = currentPath.stream().filter(n -> n.getLabel() != null).map(n -> n.getLabel()).collect(Collectors.joining(" - "));
      collectedLinks.add(new Link(labelWithPath, node.getUrl()));
    }

    for (NavNode child : node.getChildren())
      collectLinks(child, currentPath, collectedLinks);

    currentPath.remove(currentPath.size() - 1);
  }
}
