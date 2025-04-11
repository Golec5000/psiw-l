package com.piisw.jpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private LocalDateTime subscriptionDate;

    @ManyToMany(mappedBy = "followers")
    private List<Event> events;
}