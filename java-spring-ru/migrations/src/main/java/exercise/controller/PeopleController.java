package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
//@RequestMapping(value = "/people", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN

    @GetMapping("")
    public List<Map<String, Object>> getPersons() {
        String query = "SELECT * FROM person";
        return jdbc.queryForList(query);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getPerson(@PathVariable Long id) {
        String query = "SELECT * FROM person WHERE id=%s";
        return jdbc.queryForMap(String.format(query, id));
    }
    // END
}
