package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Message;
import ru.job4j.service.MessageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageService messages;

    public MessageController(MessageService messages) {
        this.messages = messages;
    }

    @GetMapping("/")
    public List<Message> findAll() {
        return this.messages.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        Optional<Message> message = this.messages.findById(id);
        HttpStatus httpStatus = message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        ResponseEntity<Message> rsl = new ResponseEntity<Message>(message.orElse(new Message()), httpStatus);
        return rsl;
    }

    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestBody Message message) {
        return new ResponseEntity<Message>(this.messages.save(message), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        this.messages.save(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> delete(@PathVariable int id){
        Message message = new Message();
        message.setId(id);
        this.messages.delete(message);
        return ResponseEntity.ok().build();
    }

}
