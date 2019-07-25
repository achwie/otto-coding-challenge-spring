package achwie.challenge.otto.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import achwie.challenge.otto.core.in.NavNode;
import achwie.challenge.otto.core.node.NodeVisitor;
import achwie.challenge.otto.core.out.Link;

/**
 * <p>
 * This is a <em>stateful</em> throw-away {@link NodeVisitor} which collects nav
 * nodes matching certain criteria and transforms them to {@link Link}s. Don't
 * use twice for iteration over a {@link NavNode} tree.
 * </p>
 * <p>
 * <strong>Thread-safety:</strong> This class is not thread-safe!
 * </p>
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class NavNodeToLinkVisitor implements NodeVisitor<NavNode> {
  private final List<Link> collectedLinks = new ArrayList<>();
  private boolean foundFilterProperty = false;
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

    if (parentFilter != null) {
      final var matchesParentFilter = matchesParentFilter(pathToRoot);
      foundFilterProperty |= matchesParentFilter;
      if (!matchesParentFilter)
        return;
    }

    collectedLinks.add(nodeToLink(node, pathToRoot));
  }

  public List<Link> getLinks() {
    return collectedLinks;
  }

  public boolean wasValidParentFilter() {
    return parentFilter == null || (parentFilter != null && foundFilterProperty);
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
}
