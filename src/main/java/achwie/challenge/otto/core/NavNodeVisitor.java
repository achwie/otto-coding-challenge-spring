package achwie.challenge.otto.core;

import java.util.List;

import achwie.challenge.otto.core.in.NavNode;

/**
 * Visitor to visit each {@link NavNode} in a tree.
 * 
 * @author 22.07.2019, Achim Wiedemann
 * @see NavNodeWalker
 *
 */
public interface NavNodeVisitor {
  /**
   * Gets called for each node in the tree.
   * 
   * @param node The current node
   * @param pathToRoot The path from the curent node to the root node with the
   *          first element being the root node.
   */
  public void visit(NavNode node, List<NavNode> pathToRoot);
}
