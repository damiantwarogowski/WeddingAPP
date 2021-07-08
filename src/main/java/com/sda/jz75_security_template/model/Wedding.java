package com.sda.jz75_security_template.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@Entity
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
    @ToString.Exclude
    @JsonBackReference
    private Couple couple;

    @ManyToOne
    private Account owner;

    @OneToMany(mappedBy = "wedding", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<TaskToDo> tasks;
}
