package com.example.demo.repositories;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends CrudRepository<Person, Integer> {
    Person findByName(String name);

    @Query(value = "SELECT * from People where id = :personId", nativeQuery = true)
    Person findPersonById(@Param("personId") int personId);

    @Query(value = "delete from People where id  = :personId", nativeQuery = true)
    void deletePersonById(@Param("personId") int personId);
}