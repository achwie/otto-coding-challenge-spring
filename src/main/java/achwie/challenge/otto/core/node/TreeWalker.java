package achwie.challenge.otto.core.node;

import java.util.ArrayList;
import java.util.List;

/**
 * Walks a tree of {@link Walkable}s.
 * 
 * @author 22.07.2019, Achim Wiedemann
 */
public class TreeWalker {

  public <T extends Walkable<T>, V extends NodeVisitor<T>> V walk(T node, V visitor) {
    final List<T> pathToRoot = new ArrayList<>();

    walkNodes(node, visitor, pathToRoot);

    return visitor;
  }

  private <T extends Walkable<T>> void walkNodes(T node, NodeVisitor<T> visitor, List<T> pathToRoot) {
    if (node == null)
      return;

    pathToRoot.add(node);

    visitor.visit(node, pathToRoot);

    for (T child : node.getChildren())
      walkNodes(child, visitor, pathToRoot);

    pathToRoot.remove(pathToRoot.size() - 1);
  }
}
