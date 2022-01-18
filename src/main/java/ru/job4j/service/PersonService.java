package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PersonService {
    private final PersonRepository persons;

    public PersonService(PersonRepository persons) {
        this.persons = persons;
    }

    public List<Person> findAll(){
        return StreamSupport.stream(
                this.persons.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }
    public Optional<Person> findById(int id){
        return persons.findById(id);
    }

   public Person save(Person person){
        return persons.save(person);
    }

   public void delete(Person person){
       persons.delete(person);
   }



}
