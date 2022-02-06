package ru.job4j.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.Employee;
import ru.job4j.repository.EmployeeRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        return StreamSupport.stream(
                repository.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    public Optional<Employee> findById(int id) {
        return repository.findById(id);
    }

    public Employee updatePatch(Employee employee) throws InvocationTargetException, IllegalAccessException {
        System.out.println("updatePatchService");
        //Employee rsl = null;
        Optional<Employee> current = repository.findById(employee.getId());
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
                var newValue = getMethod.invoke(employee);
                if (newValue != null && !(newValue instanceof ArrayList<?>)) {
                    setMethod.invoke(current.get(), newValue);
                }
            }
        }
        repository.save(current.get());
        return current.get();

    }


}
