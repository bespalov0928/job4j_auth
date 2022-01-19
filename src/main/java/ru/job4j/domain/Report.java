package ru.job4j.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Timestamp created;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


    public Report() {
    }

    public Report(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Report of(int id, String name, Person person) {
        Report r = new Report();
        r.id = id;
        r.name = name;
        r.person = person;
        r.created = new Timestamp(System.currentTimeMillis());
        return r;
    }
    // getters setters
}