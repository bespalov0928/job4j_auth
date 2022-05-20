package ru.job4j.domain;

import ru.job4j.Operation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Login must be non null", groups = {Operation.OnCreate.class})
    private String username;

    @NotNull(message = "Password must be non null", groups = {Operation.OnCreate.class})
    private String password;

    private int empid;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Role> roles = new HashSet<Role>();


    public Person(int id, String username, String password, int empid) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.empid = empid;
    }


    public Person(String username, String password) {
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(username, person.username) && Objects.equals(password, person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
