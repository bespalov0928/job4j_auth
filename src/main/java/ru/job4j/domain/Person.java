package ru.job4j.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private int empid;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> roles = new HashSet<Role>();


    public Person(int id, String login, String password, int empid) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.empid = empid;
    }


    public Person(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empId) {
        this.empid = empid;
    }

    public List<Role> getRoles() {
        return new ArrayList<>(roles);
    }

    public void setRoles(Role role) {
        this.roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(login, person.login) && Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
