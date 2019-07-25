package achwie.challenge.otto;

import static achwie.challenge.otto.api.ParseUtil.parseFieldOrder;
import static achwie.challenge.otto.api.ParseUtil.parseFieldOrders;
import static achwie.challenge.otto.core.SortOrder.ASC;
import static achwie.challenge.otto.core.SortOrder.DESC;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author 25.07.2019, Achim Wiedemann
 *
 */
public class ParseUtilTest {
  @Test
  public void parseFieldOrder_validField() {
    final var actual = parseFieldOrder("test");

    assertEquals("test", actual.getFieldName());
    assertEquals(null, actual.getSortOrder());
  }

  @Test
  public void parseFieldOrder_validFieldAndOrder() {
    final var actual = parseFieldOrder("test:asc");

    assertEquals("test", actual.getFieldName());
    assertEquals(ASC, actual.getSortOrder());
  }

  @Test
  public void parseFieldOrder_validFieldAndOrder2() {
    final var actual = parseFieldOrder("test_field:desc");

    assertEquals("test_field", actual.getFieldName());
    assertEquals(DESC, actual.getSortOrder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseFieldOrder_fieldInvalid() {
    parseFieldOrder("test field:asc");
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseFieldOrder_orderInvalid() {
    parseFieldOrder("test:topdown");
  }

  @Test
  public void parseFieldOrders_singleField() {
    final var actual = parseFieldOrders("test");

    assertEquals("test", actual[0].getFieldName());
    assertEquals(null, actual[0].getSortOrder());
  }

  @Test
  public void parseFieldOrders_singleFieldAndOrder() {
    final var actual = parseFieldOrders("test:asc");

    assertEquals("test", actual[0].getFieldName());
    assertEquals(ASC, actual[0].getSortOrder());
  }

  @Test
  public void parseFieldOrders_doubleField() {
    final var actual = parseFieldOrders("test,field:desc");

    assertEquals("test", actual[0].getFieldName());
    assertEquals(null, actual[0].getSortOrder());
    assertEquals("field", actual[1].getFieldName());
    assertEquals(DESC, actual[1].getSortOrder());
  }

  @Test
  public void parseFieldOrders_doubleFieldAndOrder() {
    final var actual = parseFieldOrders("test:desc,field:asc");

    assertEquals("test", actual[0].getFieldName());
    assertEquals(DESC, actual[0].getSortOrder());
    assertEquals("field", actual[1].getFieldName());
    assertEquals(ASC, actual[1].getSortOrder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseFieldOrders_invalidEnumeration() {
    parseFieldOrders("test:desc,");
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseFieldOrders_fieldInvalid() {
    parseFieldOrder("field1,test field:asc");
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseFieldOrdesr_orderInvalid() {
    parseFieldOrder("field1,test:topdown");
  }

  @Test(expected = IllegalArgumentException.class)
  public void parseFieldOrdesr_elementsInvalid() {
    parseFieldOrder("field1,test:asc:more");
  }
}
