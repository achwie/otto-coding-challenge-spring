package achwie.challenge.otto.core;

import java.util.ArrayList;
import java.util.List;

import achwie.challenge.otto.core.in.NavNode;

/**
 * Walks a tree of {@link NavNode}s.
 * 
 * @author 22.07.2019, Achim Wiedemann
 */
public class NavNodeWalker {

  public <T extends NavNodeVisitor> T walk(NavNode node, T visitor) {
    final List<NavNode> pathToRoot = new ArrayList<>();

    walkNodes(node, visitor, pathToRoot);

    return visitor;
  }

  private <T extends NavNodeVisitor> void walkNodes(NavNode node, T visitor, List<NavNode> pathToRoot) {
    if (node == null)
      return;

    pathToRoot.add(node);

    visitor.visit(node, pathToRoot);

    for (NavNode child : node.getChildren())
      walkNodes(child, visitor, pathToRoot);

    pathToRoot.remove(pathToRoot.size() - 1);
  }
}
