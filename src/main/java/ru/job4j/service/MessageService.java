package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Message;
import ru.job4j.domain.Room;
import ru.job4j.repository.MessageRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> findAll(){
        Iterable messageIterable = this.messageRepository.findAll();
        Stream stream = StreamSupport.stream(messageIterable.spliterator(), false);
        List<Message> rsl = (List<Message>) stream.collect(Collectors.toList());
        return rsl;
    }

    public Optional<Message> findById(int id){
        Optional<Message> rsl = this.messageRepository.findById(id);
        return rsl;
    }

    public Message save(Message message){
        return this.messageRepository.save(message);
    }

    public void delete(Message message){
        this.messageRepository.delete(message);
    }
}
