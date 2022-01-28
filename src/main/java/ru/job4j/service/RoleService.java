package ru.job4j.service;

import ch.qos.logback.core.pattern.parser.OptionTokenizer;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Role;
import ru.job4j.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoleService {
    private final RoleRepository roles;

    public RoleService(RoleRepository roles) {
        this.roles = roles;
    }

    public List<Role> findAll() {
        List<Role> rsl = StreamSupport.stream(
                this.roles.findAll().spliterator(), false
        ).collect(Collectors.toList());
        return rsl;

    }

    public Optional<Role> findById(int id) {
        return roles.findById(id);
    }


    public Role save(Role role) {
        Role rsl = roles.save(role);
        return rsl;
    }

    public void delete(Role role) {
        roles.delete(role);
    }

}
