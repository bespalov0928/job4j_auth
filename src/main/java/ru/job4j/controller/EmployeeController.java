package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.service.EmployeeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private RestTemplate rest;
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        List<Employee> rsl = new ArrayList<>();
        List<Employee> employeeList = service.findAll();
        List<Person> personList = rest.exchange(
                API,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Person>>() {
                }).getBody();

        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "1");

        for (Employee emp : employeeList) {
            Employee empNew = new Employee(emp.getName(), emp.getLastname(), emp.getInn(), emp.getDatestart());
            empNew.setId(emp.getId());
            for (Person pers : personList) {
                if (pers.getEmpid() != emp.getId()) {
                    continue;
                }
                empNew.setPersons(pers);
            }
            rsl.add(empNew);
        }
        return rsl;
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        if (person.getPassword() == null) {
            throw new NullPointerException("Password mustn't be empty");
        }
        if (person.getLogin() == null) {
            throw new NullPointerException("Login amustn't be empty");
        }

        Person rsl = rest.postForObject(API, person, Person.class);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        if (person.getPassword() == null) {
            throw new NullPointerException("Password mustn't be empty");
        }
        if (person.getLogin() == null) {
            throw new NullPointerException("Login amustn't be empty");
        }
        rest.put(API, person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }

}
