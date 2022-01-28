package ru.job4j.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Role> roles = new ArrayList<>();


    public Person(int id, String login, String password, int empid) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.empid = empid;
    }


    public Person(String login, String password, int empid) {
        this.login = login;
        this.password = password;
        this.empid = empid;
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
        return roles;
    }

    public void setRoles(Role role) {
        this.roles.add(role);
    }
}
