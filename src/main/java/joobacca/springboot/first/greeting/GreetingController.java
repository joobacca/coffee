package joobacca.springboot.first.greeting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/greeting")
public class GreetingController {
  @Value("${greeting-name: Mirage}")
  private String name;

  @Value("${greeting-coffee: ${name} is drinking some fancy shit yo}")
  private String coffee;

  @GetMapping
  String getGreeting() {
    return name;
  }

  @GetMapping("/coffee")
  String getNameAndCoffee() {
    return coffee;
  }
}
