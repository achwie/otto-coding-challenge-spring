package achwie.challenge.otto.api;

import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import achwie.challenge.otto.core.FieldOrder;
import achwie.challenge.otto.core.SortOrder;

/**
 * 
 * @author 25.07.2019, Achim Wiedemann
 *
 */
public class ParseUtil {
  private static final String PATTERN_FIELDNAME = "[a-zA-Z_][a-zA-Z0-9_]+";
  private static Pattern fieldOrdersPattern = Pattern.compile("(" + PATTERN_FIELDNAME + "(\\:(asc|desc))?)(," + PATTERN_FIELDNAME + "(\\:(asc|desc))?)*");
  private static Pattern fieldOrderPattern = Pattern.compile(PATTERN_FIELDNAME + "(\\:(asc|desc))?");

  public static FieldOrder[] parseFieldOrders(String sortParam) {
    if (sortParam == null || StringUtils.isEmpty(sortParam.trim()))
      return new FieldOrder[0];

    if (!fieldOrdersPattern.matcher(sortParam).matches())
      throw new IllegalArgumentException(
          String.format("Invalid sortParam string '%s' (expected: <fieldName>:[asc|desc][,<fieldName>:[asc|desc][, ...]])!", sortParam));

    final var parts = StringUtils.trimArrayElements(sortParam.split(","));
    final var fieldOrders = new FieldOrder[parts.length];
    for (int i = 0; i < parts.length; i++)
      fieldOrders[i] = parseFieldOrder(parts[i]);

    return fieldOrders;
  }

  public static FieldOrder parseFieldOrder(String sortField) {
    if (!fieldOrderPattern.matcher(sortField).matches())
      throw new IllegalArgumentException(String.format("Invalid field order string '%s' (expected: <fieldName>:[asc|desc])!", sortField));

    final var parts = StringUtils.trimArrayElements(sortField.split(":"));

    String fieldName = null;
    if (parts.length > 0)
      fieldName = parts[0];

    SortOrder sortOrder = null;
    if (parts.length > 1)
      sortOrder = SortOrder.fromString(parts[1]);

    return new FieldOrder(fieldName, sortOrder);
  }
}
