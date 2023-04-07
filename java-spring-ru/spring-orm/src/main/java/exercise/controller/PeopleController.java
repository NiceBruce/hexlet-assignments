package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {

    // Автоматически заполняем значение поля
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return this.personRepository.findById(id);
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, String> person) {
        System.out.println(person.get("firstName"));
        Person pers = new Person(person.get("firstName").toString(), person.get("lastName").toString());
        this.personRepository.save(pers);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        this.personRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public void updatePerson(@PathVariable Long id, @RequestBody Map<String, Object> person) {
        Person p = this.personRepository.findById(id).get();
        p.setFirstName(person.get("firstName").toString());
        p.setLastName(person.get("lastName").toString());
        this.personRepository.save(p);
    }
    // END
}
