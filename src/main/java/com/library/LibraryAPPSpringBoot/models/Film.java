package com.library.LibraryAPPSpringBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "film")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member owner;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    @Column(name = "name")
    private String name;

    @Size(max = 50, message = "Director should be less than 50 characters")
    @Column(name = "director")
    private String director;

    @Column(name = "year")
    private int year;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name="taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime takenAt;

    @Transient
    private boolean expired;

}
