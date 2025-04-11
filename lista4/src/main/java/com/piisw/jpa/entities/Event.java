package com.piisw.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NamedEntityGraph(
        name = "Event.comments.followers",
        attributeNodes = {
                @NamedAttributeNode("description"),
                @NamedAttributeNode("time"),
                @NamedAttributeNode("analysisRequired"),
                @NamedAttributeNode(value = "comments", subgraph = "commentsSubgraph"),
                @NamedAttributeNode(value = "followers", subgraph = "followersSubgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "commentsSubgraph",
                        attributeNodes = {
                                @NamedAttributeNode("content")     // pobieramy content z Comment
                        }
                ),
                @NamedSubgraph(
                        name = "followersSubgraph",
                        attributeNodes = {
                                @NamedAttributeNode("subscriptionDate") // pobieramy subscriptionDate z Follower
                        }
                )
        }
)
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Event {

    @Id
    @SequenceGenerator(name = "EVENT_ID_GENERATOR", sequenceName = "EVENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_ID_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private LocalDateTime time;

    private int duration;

    private String description;

    @Column(length = 10)
    private String threadId;

    @Column(length = 30)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVER_ID", nullable = false)
    private Server server;

    @Column
    private boolean analysisRequired;

    @OneToMany(mappedBy = "event")
    private List<Comment> comments;

    @ManyToMany
    private List<Follower> followers;

}
