package ru.job4j.domain;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private int empid;

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


}
