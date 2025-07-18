package com.piisw.jpa.tasks;

import com.piisw.jpa.entities.Event;
import com.piisw.jpa.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataJpaTest
class Task2 {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void shouldFindOneEntryBetweenDatesThatMustBeAnalyzed() {
        // given
        LocalDateTime start = LocalDateTime.of(2018, 4, 9, 3, 25);
        LocalDateTime end = LocalDateTime.of(2018, 4, 9, 3, 26);
        boolean toBeAnalyzed = false;
        int page = 0;
        int pageSize = 10;
        Sort sort = Sort.unsorted();

        // when
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Event> result = eventRepository.findEventByTimeBetweenAndAnalysisRequired(start, end, toBeAnalyzed, pageable);

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result.getContent(), hasSize(1));
    }

    @Test
    void shouldReturnThirdPageOfEventsSortedByTime() {
        // given
        LocalDateTime start = LocalDateTime.of(2016, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2019, 12, 31, 0, 0);
        boolean toBeAnalyzed = false;
        int page = 3;
        int pageSize = 10;
        Sort sort = Sort.by("time");

        // when
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Event> result = eventRepository.findEventByTimeBetweenAndAnalysisRequired(start, end, toBeAnalyzed, pageable);

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result.getNumber(), is(page));
        assertThat(result.getNumberOfElements(), is(9));
        assertThat(result.getContent().get(8).getTime(), is(LocalDateTime.of(2018,12,9,17,25, 4)));
    }

    @Test
    void shouldReturnEmptyPage() {
        // given
        LocalDateTime start = LocalDateTime.of(2020, 1, 1, 0, 0);
        LocalDateTime end = LocalDateTime.of(2021, 12, 31, 0, 0);
        boolean toBeAnalyzed = false;
        int page = 0;
        int pageSize = 10;
        Sort sort = Sort.by("time");

        // when
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Page<Event> result = eventRepository.findEventByTimeBetweenAndAnalysisRequired(start, end, toBeAnalyzed, pageable);

        // then
        assertThat(result.getTotalElements(), is(0L));
        assertThat(result.getContent(), hasSize(0));
    }
}
