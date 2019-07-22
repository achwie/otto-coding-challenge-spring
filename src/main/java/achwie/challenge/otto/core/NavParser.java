package achwie.challenge.otto.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import achwie.challenge.otto.core.in.NavRoot;

/**
 * Simple parser to deserialize JSON to Objects.
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
public class NavParser {
  private final ObjectMapper objectMapper = new ObjectMapper();

  public NavRoot parseNavigation(String json) throws JsonParseException, JsonMappingException, IOException {
    return objectMapper.readValue(json, NavRoot.class);
  }
}
