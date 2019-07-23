package achwie.challenge.otto.core;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class FieldOrder {
  private final String fieldName;
  private final SortOrder sortOrder;

  public FieldOrder(String fieldName, SortOrder sortOrder) {
    this.fieldName = fieldName;
    this.sortOrder = sortOrder;
  }

  public String getFieldName() {
    return fieldName;
  }

  public SortOrder getSortOrder() {
    return sortOrder;
  }

  public static FieldOrder orderBy(String fieldName, SortOrder sortOrder) {
    return new FieldOrder(fieldName, sortOrder);
  }

  public static FieldOrder orderBy(String fieldName) {
    return new FieldOrder(fieldName, null);
  }

  @Override
  public String toString() {
    return "FieldOrder [fieldName=" + fieldName + ", sortOrder=" + sortOrder + "]";
  }
}
