package achwie.challenge.otto.core.node;

import java.util.List;

/**
 * Interface for a walkable node in a tree.
 * 
 * @author 22.07.2019, Achim Wiedemann
 * @see TreeWalker
 */
public interface Walkable<T> {
  public List<T> getChildren();
}
