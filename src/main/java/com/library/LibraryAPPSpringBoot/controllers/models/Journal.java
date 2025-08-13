package com.library.LibraryAPPSpringBoot.controllers.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "journal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Journal {
    @Id
    @Column(name = "journal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int journalId;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    private Member owner;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    @Column(name = "name")
    private String name;

    @Size(max = 50, message = "Thematic should be less than 50 characters")
    @Column(name = "thematic")
    private String thematic;

    @Min(value = 1, message = "Month should be greater than 1")
    @Max(value = 12, message = "Month should be less than 12")
    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime takenAt;

    @Transient
    private boolean expired;
}
