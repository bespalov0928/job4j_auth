package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.Operation;
import ru.job4j.domain.Person;
import ru.job4j.service.PersonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService persons;
    private final ObjectMapper objectMapper;

    public PersonController(PersonService persons, ObjectMapper objectMapper) {
        this.persons = persons;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/")
    public List<Person> findAll() {
        System.out.println("findAll");
        return persons.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        System.out.println("findById");
        Optional<Person> person = this.persons.findById(id);
        ResponseEntity<Person> entity = ResponseEntity.status(person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(person.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person is not found. Please, check ID.")));
        return entity;
    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        if (person.getPassword() == null) {
            throw new NullPointerException("Password mustn't be empty");
        }
        if (person.getUsername() == null) {
            throw new NullPointerException("Login amustn't be empty");
        }
        if (person.getPassword().length() < 6) {
            throw new IllegalArgumentException("Invalid password. Password length must be more than 5 characters.");
        }

        Person personNew = this.persons.save(person);
        ResponseEntity<Person> entity = ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(personNew);
        return entity;
    }

    @PutMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Valid  @RequestBody Person person) {

        if (person.getPassword() == null) {
            throw new NullPointerException("Password and password mustn't be empty");
        }
        if (person.getUsername() == null) {
            throw new NullPointerException("Login and password mustn't be empty");
        }
        if (person.getPassword().length() < 6) {
            throw new IllegalArgumentException("Invalid password. Password length must be more than 5 characters.");
        }

        this.persons.save(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        this.persons.delete(person);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {{
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
    }

    @PatchMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Person> updatePatch(@Valid @RequestBody Person person) throws InvocationTargetException, IllegalAccessException {
        System.out.println("updatePatchController");
        ResponseEntity<Person> rsl = null;

        Person employeeUpdate = persons.updatePatch(person);
        rsl = ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeUpdate);
        return rsl;
    }
}
