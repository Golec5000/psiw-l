package com.piisw.jpa.repositories;

import com.piisw.jpa.entities.Event;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Modifying
    @Query("DELETE FROM Event e WHERE e.time < :time")
    int bulkDeleteEventsBefore(@Param("time") LocalDateTime time);

    @Modifying
    @Query("UPDATE Event e SET e.analysisRequired = true "
            + "WHERE TYPE(e) = :clazz AND e.duration > :duration")
    int bulkUpdateAnalysisRequiredForSubclass(
            @Param("clazz") Class<? extends Event> clazz,
            @Param("duration") int minDuration
    );

    Page<Event> findEventByTimeBetweenAndAnalysisRequired(
            LocalDateTime start, LocalDateTime end, boolean analysisRequired, Pageable pageable
    );

    @Modifying(clearAutomatically = true)
    @Query("SELECT new com.piisw.jpa.repositories.ServerStatistic(s, COUNT(e)) "
            + "FROM Event e JOIN e.server s GROUP BY s.id")
    List<ServerStatistic> countEventsPerServer();

    @EntityGraph(value = "Event.comments.followers", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT e FROM Event e JOIN e.followers f WHERE f.userId = :followerId")
    List<Event> findAllEventsByFollower(@Param("followerId") Long followerId);
}