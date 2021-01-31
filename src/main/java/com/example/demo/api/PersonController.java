package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/people")
@RestController
public class PersonController {


    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        this.personService.savePerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getPeople();
    }

    @GetMapping(path = "{id}")
    public Person selectPersonById(@PathVariable("id") int id) {
        return personService.getPersonById(id);
    }

    @DeleteMapping(path = "{id}")
    public String deletePersonById(@PathVariable("id") int id) {
        return personService.deletePersonById(id);
    }

    @PutMapping(path = "{id}")
    public Person updatePersonById(@RequestBody Person person) {
        return personService.updatePersonById(person);
    }

    @GetMapping("/name/{name}")
    public Person getPersonByName(@PathVariable("name") String name) {
        return personService.getPersonByName(name);
    }


    @PostMapping("/addAll")
    public List<Person> addPeople(@RequestBody List<Person> people) {
        return personService.savePeople(people);
    }

}
