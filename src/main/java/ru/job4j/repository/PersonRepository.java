package ru.job4j.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.domain.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("from Person p where p.username=:username")
    public Person findByUsername(@Param("username") String username);




}
