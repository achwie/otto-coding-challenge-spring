package achwie.challenge.otto.core.out;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class Link {
  private final String label;
  private final String url;

  public Link(String label, String url) {
    this.label = label;
    this.url = url;
  }

  public String getLabel() {
    return label;
  }

  public String getUrl() {
    return url;
  }
}
