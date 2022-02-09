package ru.job4j.domain;

import ru.job4j.Operation;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull(message = "Name must be non null", groups = {Operation.OnCreate.class})
    private String name;

    @NotNull(message = "Lastname must be non null", groups = {Operation.OnCreate.class})
    private String lastname;

    @Size(min = 10, max = 12, message = "About Me must be between 10 and 12 characters")
    private String inn;

    private Date datestart;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> persons = new ArrayList<>();


    public Employee(String name, String lastname, String inn, Date datestart) {
        this.name = name;
        this.lastname = lastname;
        this.inn = inn;
        this.datestart = datestart;
    }
    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Employee() {

    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(Person person) {
        this.persons.add(person);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getInn() {
        return inn;
    }

    public Date getDatestart() {
        return datestart;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }
}
