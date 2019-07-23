package achwie.challenge.otto.api;

import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import achwie.challenge.otto.core.FieldOrder;
import achwie.challenge.otto.core.SortOrder;
import achwie.challenge.otto.core.out.Link;

/**
 * 
 * @author 23.07.2019, Achim Wiedemann
 *
 */
@RestController
public class LinkController {
  private final LinkService linkService;

  public LinkController(LinkService linkService) {
    this.linkService = linkService;
  }

  @GetMapping("links")
  public List<Link> links(@RequestParam(value = "parent", required = false) String parentFilterParam,
      @RequestParam(value = "sort", required = false) String sortParam) {

    final var fieldOrders = parseFieldOrders(sortParam);

    return linkService.getLinks(parentFilterParam, fieldOrders);
  }

  private FieldOrder[] parseFieldOrders(String sortParam) {
    if (sortParam == null || StringUtils.isEmpty(sortParam.trim()))
      return new FieldOrder[0];

    final var parts = StringUtils.trimArrayElements(sortParam.split(","));
    final var fieldOrders = new FieldOrder[parts.length];
    for (int i = 0; i < parts.length; i++)
      fieldOrders[i] = parseFieldOrder(parts[i]);

    return fieldOrders;
  }

  private FieldOrder parseFieldOrder(String sortField) {
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
