package com.sda.jz75_security_template.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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

    @OneToMany(mappedBy = "poleWedding", fetch = FetchType.EAGER)
    private List<TasksToDo> tasksToDoList;
}
