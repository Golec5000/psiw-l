package com.piisw.jpa.tasks;

import com.piisw.jpa.entities.Comment;
import com.piisw.jpa.entities.Event;
import com.piisw.jpa.entities.Follower;
import com.piisw.jpa.entities.RequestEvent;
import com.piisw.jpa.repositories.EventRepository;
import com.piisw.jpa.services.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Task6 {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void shouldReturnEventsWithCommentsAndFollowersForGivenFollowerId() {
        // given
        Long followerId = 42L;

        Follower follower = new Follower();
        follower.setUserId(followerId);
        follower.setSubscriptionDate(LocalDateTime.of(2023, 3, 1, 12, 0));

        Event event = getEvent(follower);

        when(eventRepository.findAllEventsByFollower(followerId)).thenReturn(List.of(event));

        // when
        List<Event> result = eventService.getEventsByFollower(followerId);

        // then
        assertNotNull(result);
        assertEquals(1, result.size());

        Event retrieved = result.getFirst();
        assertEquals("Testowy opis", retrieved.getDescription());
        assertTrue(retrieved.isAnalysisRequired());
        assertEquals(2, retrieved.getComments().size());
        assertEquals("Pierwszy komentarz", retrieved.getComments().getFirst().getContent());
        assertEquals(LocalDateTime.of(2023, 3, 1, 12, 0), retrieved.getFollowers().getFirst().getSubscriptionDate());

        verify(eventRepository, times(1)).findAllEventsByFollower(followerId);
    }

    @Test
    void shouldReturnEmptyListWhenNoEventsFound() {
        // given
        Long followerId = 42L;

        when(eventRepository.findAllEventsByFollower(followerId)).thenReturn(Collections.emptyList());

        // when
        List<Event> result = eventService.getEventsByFollower(followerId);

        // then
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(eventRepository, times(1)).findAllEventsByFollower(followerId);
    }

    @Test
    void shouldHandleNullResultFromRepository() {
        // given
        Long followerId = 42L;

        when(eventRepository.findAllEventsByFollower(followerId)).thenReturn(null);

        // when
        List<Event> result = eventService.getEventsByFollower(followerId);

        // then
        assertNull(result);

        verify(eventRepository, times(1)).findAllEventsByFollower(followerId);
    }

    private Event getEvent(Follower follower) {
        Comment comment1 = new Comment();
        comment1.setContent("Pierwszy komentarz");

        Comment comment2 = new Comment();
        comment2.setContent("Drugi komentarz");

        Event event = new RequestEvent(); // używamy podklasy Event, np. RequestEvent
        event.setDescription("Testowy opis");
        event.setTime(LocalDateTime.of(2024, 12, 1, 8, 0));
        event.setAnalysisRequired(true);
        event.setComments(List.of(comment1, comment2));
        event.setFollowers(List.of(follower));

        comment1.setEvent(event);
        comment2.setEvent(event);
        return event;
    }
}