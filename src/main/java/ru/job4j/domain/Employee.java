package ru.job4j.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String name;
    private String lastname;
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
}
