package ru.job4j.url;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Person;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserStore users;
    private BCryptPasswordEncoder encoder;

    public UserController(UserStore users,
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