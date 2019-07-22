package achwie.challenge.otto.core.in;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class NavRoot extends NavNode {

  @JsonCreator
  public NavRoot(@JsonProperty("navigationEntries") List<NavNode> navigationEntries) {
    super(null, null, navigationEntries, null);
  }
}
