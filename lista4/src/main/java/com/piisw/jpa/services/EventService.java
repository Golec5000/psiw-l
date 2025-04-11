package com.piisw.jpa.services;

import com.piisw.jpa.entities.Event;
import com.piisw.jpa.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;

    public List<Event> getEventsByFollower(Long followerId) {
        return repository.findAllEventsByFollower(followerId);
    }

}
