package ru.job4j.url;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Person;
import ru.job4j.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private PersonService users;
    private BCryptPasswordEncoder encoder;

    public UserController(PersonService users,
                          BCryptPasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Person person) {
        System.out.println("signUp");
        person.setPassword(encoder.encode(person.getPassword()));
        users.save(person);
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        System.out.println("findAll");
        return users.findAll();
    }
}