package achwie.challenge.otto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author 22.07.2019, Achim Wiedemann
 *
 */
@SpringBootApplication
public class OttoChallengeSpringApplication {
  public static void main(String[] args) {
    SpringApplication.run(OttoChallengeSpringApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
