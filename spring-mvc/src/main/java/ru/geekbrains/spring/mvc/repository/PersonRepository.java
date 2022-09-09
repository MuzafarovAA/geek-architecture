package ru.geekbrains.spring.mvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.mvc.entity.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findById(int id);
}
