package achwie.challenge.otto.core.node;

import java.util.List;

/**
 * Visitor to visit each node in a {@link Walkable} tree.
 * 
 * @author 22.07.2019, Achim Wiedemann
 * @see TreeWalker
 *
 */
public interface NodeVisitor<T> {
  /**
   * Gets called for each node in the tree.
   * 
   * @param node The current node
   * @param pathToRoot The path from the curent node to the root node with the
   *          first element being the root node.
   */
  public void visit(T node, List<T> pathToRoot);
}
