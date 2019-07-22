package achwie.challenge.otto.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import achwie.challenge.otto.core.in.NavNode;
import achwie.challenge.otto.core.out.Link;

/**
 * <p>
 * This is a <em>stateful</em> {@link NavNodeVisitor} which collects nav nodes
 * of a certain kind and transforms them to {@link Link}s. Don't use twice for
 * iteration over a {@link NavNode} tree.
 * </p>
 * <p>
 * <strong>Thread-safety:</strong> This class is not thread-safe!
 * </p>
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class NavNodeToLinkVisitor implements NavNodeVisitor {
  private final List<Link> collectedLinks = new ArrayList<>();
  private String parentFilter;

  public NavNodeToLinkVisitor() {
  }

  public NavNodeToLinkVisitor(String parentFilter) {
    this.parentFilter = parentFilter;
  }

  @Override
  public void visit(NavNode node, List<NavNode> pathToRoot) {
    if (!isLink(node))
      return;

    if (parentFilter != null && !matchesParentFilter(pathToRoot))
      return;

    collectedLinks.add(nodeToLink(node, pathToRoot));
  }

  public List<Link> getLinks() {
    return collectedLinks;
  }

  private boolean isLink(NavNode node) {
    return node.isType(NavNode.TYPE_LINK);
  }

  private boolean matchesParentFilter(List<NavNode> pathToRoot) {
    return pathToRoot.stream().anyMatch(n -> parentFilter.equals(n.getLabel()));
  }

  private Link nodeToLink(NavNode node, List<NavNode> pathToRoot) {
    final var labelWithPath = createLabelWithPath(pathToRoot);

    return new Link(labelWithPath, node.getUrl());
  }

  private String createLabelWithPath(List<NavNode> pathToRoot) {
    return pathToRoot.stream().filter(n -> n.getLabel() != null).map(n -> n.getLabel()).collect(Collectors.joining(" - "));
  }

  public String getParentFilter() {
    return parentFilter;
  }

  public void setParentFilter(String parentFilter) {
    this.parentFilter = parentFilter;
  }
}
