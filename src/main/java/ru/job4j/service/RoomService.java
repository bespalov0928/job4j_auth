package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Room;
import ru.job4j.repository.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll(){
        return StreamSupport.stream(
        this.roomRepository.findAll().spliterator(), false
                ).collect(Collectors.toList());
    }

    public Optional<Room> findById(int id){
        return this.roomRepository.findById(id);
    }

    public Room save(Room room){
        return roomRepository.save(room);
    }

    public void delete(Room room){
        this.roomRepository.delete(room);
    }

}
