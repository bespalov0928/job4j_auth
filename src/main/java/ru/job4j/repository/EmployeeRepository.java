package ru.job4j.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {


}
