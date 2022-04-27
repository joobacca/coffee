package joobacca.springboot.first.coffee;

import org.springframework.data.repository.CrudRepository;

interface CoffeeRepository extends CrudRepository<Coffee, String> {

}
