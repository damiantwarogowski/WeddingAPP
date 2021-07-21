package com.sda.weddingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wedding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfWedding;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeOfWedding;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeOfWeddingParty;

    @OneToOne()
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @ToString.Exclude
    private Couple couple;

    @ManyToOne
    private Account owner;

    @OneToMany(mappedBy = "wedding", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE})
    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<TaskToDo> tasks;
}
