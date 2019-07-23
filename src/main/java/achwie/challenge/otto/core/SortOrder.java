package achwie.challenge.otto.core;

/**
 * 
 * @author 23.07.2019, Achim Wiedemann
 *
 */
public enum SortOrder {
  ASC, DESC;

  public static SortOrder fromString(String str) {
    if (str != null)
      for (SortOrder o : values())
        if (o.name().toLowerCase().equals(str.toLowerCase()))
          return o;

    return null;
  }
}
