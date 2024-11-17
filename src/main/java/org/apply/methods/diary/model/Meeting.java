package org.apply.methods.diary.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('meeting_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Column(name = "start", nullable = false)
    private Instant start;

    @Column(name = "finish")
    private Instant finish;

}