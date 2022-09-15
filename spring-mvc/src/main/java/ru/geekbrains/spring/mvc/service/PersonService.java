package ru.geekbrains.spring.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.mvc.entity.Person;
import ru.geekbrains.spring.mvc.repository.PersonRepository;

@Service
public class PersonService {

    PersonRepository personRepository;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(int id) {
        return personRepository.findById(id);
    }
}
