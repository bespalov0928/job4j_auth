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
