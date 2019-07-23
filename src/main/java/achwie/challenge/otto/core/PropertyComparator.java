package achwie.challenge.otto.core;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.PropertyAccessorFactory;

/**
 * <p>
 * Allows for comparation of objects of a certain types using a flexible
 * property-name based mechanism. It supports sort orders and object comparation
 * by multiple fields.
 * </p>
 * <p>
 * Note that this class uses reflection on the target type being compared and
 * therefore comes with a startup penalty. Because of this it makes sense to
 * cache and reuse comparators of this type.
 * </p>
 * <p>
 * <strong>Thread safety: This class is thread safe.</strong>
 * </p>
 * 
 * @author 23.07.2019, Achim Wiedemann
 *
 * @param <T> The type being compared by this comparator
 */
public class PropertyComparator<T> implements Comparator<T> {
  public static final SortOrder SORT_ORDER_DEFAULT = SortOrder.ASC;
  private final Class<?> targetType;
  private final FieldOrder[] fieldOrders;

  public PropertyComparator(Class<T> targetType, FieldOrder... fieldOrders) {
    this.targetType = Objects.requireNonNull(targetType, "Given target-type must not be null!");
    // Do only once on startup to avoid performance penalty on large lists
    this.fieldOrders = keepComparableFields(fieldOrders);
  }

  @SuppressWarnings("unchecked")
  @Override
  public int compare(T o1, T o2) {
    final var o1Wrapped = PropertyAccessorFactory.forBeanPropertyAccess(o1);
    final var o2Wrapped = PropertyAccessorFactory.forBeanPropertyAccess(o2);

    for (FieldOrder o : fieldOrders) {
      final var orderBy = o.getFieldName();
      final var orderDir = Optional.ofNullable(o.getSortOrder()).orElse(SORT_ORDER_DEFAULT);

      // Already ensured fields are comparable in constructor
      final var c1 = (Comparable<Object>) o1Wrapped.getPropertyValue(orderBy);
      final var c2 = (Comparable<Object>) o2Wrapped.getPropertyValue(orderBy);

      final int orderMultiplier = (orderDir == SortOrder.ASC) ? 1 : -1;

      final int comparationResult = c1.compareTo(c2) * orderMultiplier;

      // Only if they are equal we need to consider further field orders
      if (comparationResult != 0)
        return comparationResult;
    }

    return 0;
  }

  private FieldOrder[] keepComparableFields(FieldOrder[] fieldOrders) {
    final List<FieldOrder> keepOrders = new ArrayList<>();
    try {
      final var propertyTypes = getPropertyTypes(targetType);

      for (var fieldOrder : fieldOrders) {
        final var propertyType = propertyTypes.get(fieldOrder.getFieldName());
        if (isComparable(propertyType)) {
          keepOrders.add(fieldOrder);
        }
      }

      return keepOrders.toArray(new FieldOrder[keepOrders.size()]);
    } catch (IntrospectionException e) {
      // Class can't be introspected
      throw new RuntimeException("Could not introspect target class " + targetType.getCanonicalName() + " for generic comparator!", e);
    }
  }

  private boolean isComparable(Class<?> propertyType) {
    if (propertyType == int.class || propertyType == long.class || propertyType == float.class || propertyType == double.class)
      return true;

    return propertyType != null ? Comparable.class.isAssignableFrom(propertyType) : false;
  }

  private Map<String, Class<?>> getPropertyTypes(Class<?> clazz) throws IntrospectionException {
    final var propertyTypes = new HashMap<String, Class<?>>();

    final var linkBeanInfo = Introspector.getBeanInfo(clazz);
    for (var pd : linkBeanInfo.getPropertyDescriptors()) {
      propertyTypes.put(pd.getName(), pd.getPropertyType());
    }

    return propertyTypes;
  }

}