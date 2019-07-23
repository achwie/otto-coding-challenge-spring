package achwie.challenge.otto.core;

import static achwie.challenge.otto.core.SortOrder.ASC;
import static achwie.challenge.otto.core.SortOrder.DESC;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

/**
 * 
 * @author 23.07.2019, Achim Wiedemann
 *
 */
public class PropertyComparatorTest {
  @Test
  public void compare_withComparableProperty() {
    final var list = Arrays.asList(
        new MyComparable("A", 2, new Object()),
        new MyComparable("C", 0, new Object()),
        new MyComparable("D", 1, new Object()),
        new MyComparable("B", 1, new Object()));

    Collections.sort(list, new PropertyComparator<MyComparable>(MyComparable.class, new FieldOrder("uno", ASC)));

    assertEquals("A", list.get(0).getUno());
    assertEquals("B", list.get(1).getUno());
    assertEquals("C", list.get(2).getUno());
    assertEquals("D", list.get(3).getUno());
  }

  @Test
  public void compare_withNonComparableProperty() {
    final var list = Arrays.asList(
        new MyComparable("A", 2, new Object()),
        new MyComparable("C", 0, new Object()),
        new MyComparable("D", 1, new Object()),
        new MyComparable("B", 1, new Object()));

    Collections.sort(list, new PropertyComparator<MyComparable>(MyComparable.class, new FieldOrder("tres", null)));

    assertEquals("A", list.get(0).getUno());
    assertEquals("C", list.get(1).getUno());
    assertEquals("D", list.get(2).getUno());
    assertEquals("B", list.get(3).getUno());
  }

  @Test
  public void compare_withOneSortFieldInvalid() {
    final var list = Arrays.asList(
        new MyComparable("A", 2, new Object()),
        new MyComparable("C", 0, new Object()),
        new MyComparable("D", 1, new Object()),
        new MyComparable("B", 1, new Object()));

    Collections.sort(list, new PropertyComparator<MyComparable>(MyComparable.class, new FieldOrder("quatro", null), new FieldOrder("dos", DESC)));

    assertEquals("A", list.get(0).getUno());
    assertEquals("D", list.get(1).getUno());
    assertEquals("B", list.get(2).getUno());
    assertEquals("C", list.get(3).getUno());
  }

  @Test
  public void compare_withAllSortFieldsInvalid() {
    final var list = Arrays.asList(
        new MyComparable("A", 2, new Object()),
        new MyComparable("C", 0, new Object()),
        new MyComparable("D", 1, new Object()),
        new MyComparable("B", 1, new Object()));

    Collections.sort(list, new PropertyComparator<MyComparable>(MyComparable.class, new FieldOrder("quatro", ASC), new FieldOrder("cinco", DESC)));

    assertEquals("A", list.get(0).getUno());
    assertEquals("C", list.get(1).getUno());
    assertEquals("D", list.get(2).getUno());
    assertEquals("B", list.get(3).getUno());
  }

  // -- End of Tests -----------------------------------------------------------
  public static class MyComparable {
    private final String uno;
    private final int dos;
    private final Object tres;

    public MyComparable(String uno, int dos, Object tres) {
      this.uno = uno;
      this.dos = dos;
      this.tres = tres;
    }

    public String getUno() {
      return uno;
    }

    public int getDos() {
      return dos;
    }

    public Object getTres() {
      return tres;
    }
  }
}
