package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Room;
import ru.job4j.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomRepository rooms;

    public RoomController(RoomRepository rooms) {
        this.rooms = rooms;
    }

    @GetMapping("/")
    public List<Room> findAll() {
        return (List<Room>) this.rooms.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        Optional<Room> room = this.rooms.findById(id);
        HttpStatus httpStatus = room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        ResponseEntity<Room> rsl = new ResponseEntity<Room>(room.orElse(new Room()), httpStatus);
        return rsl;
    }

    @PostMapping("/")
    public ResponseEntity<Room> save(@RequestBody Room room) {
        Room roomNew = this.rooms.save(room);
        ResponseEntity<Room> rsl = new ResponseEntity<Room>(roomNew, HttpStatus.OK);
        return rsl;
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room){
        this.rooms.save(room);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        Room roomDel = new Room();
        roomDel.setId(id);
        this.rooms.delete(roomDel);
        return ResponseEntity.ok().build();
    }

}
