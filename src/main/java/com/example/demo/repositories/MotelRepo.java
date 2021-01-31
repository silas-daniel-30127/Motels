package com.example.demo.repositories;

import com.example.demo.model.Motel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotelRepo extends CrudRepository<Motel,Integer> {

}
