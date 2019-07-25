package achwie.challenge.otto.api;

import static achwie.challenge.otto.api.ParseUtil.parseFieldOrders;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
      @RequestParam(value = "sort", required = false) String sortParam) throws IOException {

    try {
      final var fieldOrders = parseFieldOrders(sortParam);

      return linkService.getLinks(parentFilterParam, fieldOrders);
    } catch (IllegalArgumentException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not return links: " + e.getMessage());
    }
  }
}
