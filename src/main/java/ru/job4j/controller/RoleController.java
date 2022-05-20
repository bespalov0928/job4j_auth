package ru.job4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.Message;
import ru.job4j.domain.Role;
import ru.job4j.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<Role> findAll() {
        List<Role> rsl = roleService.findAll();
        return rsl;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable int id) {
        Optional<Role> role = this.roleService.findById(id);
        HttpStatus httpStatus = role.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        ResponseEntity<Role> rsl = new ResponseEntity<Role>(
                role.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "role is not found. Please, check ID.")),
                httpStatus);
        return rsl;
    }

    @PostMapping("/")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        System.out.println("create");
        if (role.getAuthority() == null) {
            throw new NullPointerException("User mustn't be empty");
        }

        System.out.println("PostMapping");
        ResponseEntity<Role> rsl = new ResponseEntity<Role>(
                this.roleService.save(role),
                HttpStatus.CREATED
        );
        return rsl;
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Role role) {
        System.out.println("PutMapping");
        this.roleService.save(role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        System.out.println("DeleteMapping");
        Role role = new Role();
        role.setId(id);
        this.roleService.delete(role);
        return ResponseEntity.ok().build();
    }

}
