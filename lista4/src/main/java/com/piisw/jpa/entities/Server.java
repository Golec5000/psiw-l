package com.piisw.jpa.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_active = true")
public class Server {

    @Id
    @SequenceGenerator(name = "SERVER_ID_GENERATOR", sequenceName = "SERVER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVER_ID_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ip;

    @Version
    private Long version;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime lastUpdateDate;

    public Server(String name, String ip) {
        super();
        this.name = name;
        this.ip = ip;
    }

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        lastUpdateDate = createdDate;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateDate = LocalDateTime.now();
    }

}
