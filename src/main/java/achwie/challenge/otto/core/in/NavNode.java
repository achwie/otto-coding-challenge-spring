package achwie.challenge.otto.core.in;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import achwie.challenge.otto.core.node.Walkable;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class NavNode implements Walkable<NavNode> {
  public static final String TYPE_SECTION = "section";
  public static final String TYPE_NODE = "node";
  public static final String TYPE_LINK = "link";
  private final String type;
  private final String label;
  private final List<NavNode> children;
  private final String url;

  public NavNode(String type, String label, List<NavNode> children) {
    this(type, label, children, null);
  }

  public NavNode(String type, String label, String url) {
    this(type, label, null, url);
  }

  @JsonCreator
  public NavNode(@JsonProperty("type") String type, @JsonProperty("label") String label, @JsonProperty("children") List<NavNode> children,
      @JsonProperty("url") String url) {
    this.type = type;
    this.label = label;
    this.children = (children != null) ? children : Collections.emptyList();
    this.url = url;
  }

  public String getType() {
    return type;
  }

  public String getLabel() {
    return label;
  }

  public List<NavNode> getChildren() {
    return Collections.unmodifiableList(children);
  }

  public NavNode getChild(int index) {
    return (children != null && index < children.size()) ? children.get(index) : null;
  }

  public String getUrl() {
    return url;
  }

  public boolean isType(String type) {
    return Objects.equals(this.type, type);
  }

  @Override
  public String toString() {
    return String.format("NavNode [type=%s, label=%s, children=%s, url=%s]", type, label, children, url);
  }
}
