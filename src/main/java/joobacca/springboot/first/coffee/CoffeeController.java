package joobacca.springboot.first.coffee;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class DataLoader {
  private final CoffeeRepository coffeeRepository;

  public DataLoader(CoffeeRepository coffeeRepository) {
    this.coffeeRepository = coffeeRepository;
  }

  @PostConstruct
  private void loadData() {
    coffeeRepository.saveAll(List.of(
        new Coffee("Café Cereza"),
        new Coffee("Café Ganador"),
        new Coffee("Café Lareño"),
        new Coffee("Café Três Pontas")));
  }

}

@RestController
@RequestMapping("/coffees")
public class CoffeeController {
  private final CoffeeRepository coffeeRepository;

  public CoffeeController(CoffeeRepository coffeeRepository) {
    this.coffeeRepository = coffeeRepository;
  }

  // Short annotation for:
  // @RequestMapping(value = "/coffees", method = RequestMapping.GET)#
  @GetMapping
  public Iterable<Coffee> getCoffees() {
    return coffeeRepository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<Coffee> getCoffeeById(@PathVariable String id) {
    return coffeeRepository.findById(id);
  }

  @PostMapping
  Coffee postCoffee(@RequestBody Coffee coffee) {
    // Coffee newCoffee = new Coffee();
    return coffeeRepository.save(coffee);
  }

  @PutMapping("/{id}")
  ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
    return coffeeRepository.existsById(id) ? new ResponseEntity<>(postCoffee(coffee), HttpStatus.OK)
        : new ResponseEntity<>(postCoffee(coffee), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  void deleteCoffee(@PathVariable String id) {
    coffeeRepository.deleteById(id);
  }
}
