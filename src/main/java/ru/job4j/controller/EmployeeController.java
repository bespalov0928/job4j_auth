package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.Operation;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.service.EmployeeService;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

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
        System.out.println("findAll");
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

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable int id) {

        ResponseEntity<Employee> rsl = null;

        return rsl;

    }

    @PostMapping("/")
    @Validated(Operation.OnCreate.class)
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
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
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Person person) {
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

    @PatchMapping("/")
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Employee> updatePatch(@Valid @RequestBody Employee employee) throws InvocationTargetException, IllegalAccessException {
        System.out.println("updatePatchController");
        ResponseEntity<Employee> rsl = null;

        Employee employeeUpdate = service.updatePatch(employee);
        rsl = ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(employeeUpdate);
        return rsl;
    }
}
