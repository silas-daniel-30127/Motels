package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public void savePerson(Person person) {
        personRepo.save(person);
    }

    public List<Person> savePeople(List<Person> people) {
        return (List<Person>) personRepo.saveAll(people);
    }

    public List<Person> getPeople() {
        return (List<Person>) personRepo.findAll();
    }

    public Person getPersonById(int id) {
        return personRepo.findById(id).orElse(null);
    }

    public Person getPersonByName(String name) {
        return personRepo.findByName(name);
    }

    public String deletePersonById(int id) {
        personRepo.deleteById(id);
        return "Person " + id + " removed!!!";
    }

    public Person updatePersonById(Person person) {
        Person existingPerson = personRepo.findById(person.getId()).orElse(null);
        assert existingPerson != null;
        existingPerson.setName(person.getName());
        return personRepo.save(existingPerson);
    }
}
