package ru.job4j.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
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

    public Person updatePatch(Person person) throws InvocationTargetException, IllegalAccessException {
        System.out.println("updatePatchService");
        //Employee rsl = null;
        Optional<Person> current = persons.findById(person.getId());

        if (current == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var methods = current.get().getClass().getDeclaredMethods();
        var namePerMethod = new HashMap<String, Method>();
        for (var method : methods) {
            var name = method.getName();
            if (name.startsWith("get") || name.startsWith("set")) {
                namePerMethod.put(name, method);
            }
        }

        for (var name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                var getMethod = namePerMethod.get(name);
                var setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid properties mapping");
                }
                var newValue = getMethod.invoke(person);
                if (newValue != null && !(newValue instanceof ArrayList<?>)) {
                    setMethod.invoke(current.get(), newValue);
                }
            }
        }
        persons.save(current.get());
        return current.get();

    }


}
