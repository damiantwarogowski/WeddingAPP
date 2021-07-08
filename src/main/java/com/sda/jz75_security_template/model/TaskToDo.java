package com.sda.jz75_security_template.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TaskToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reminderDay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadlineDay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate weddingDate;

    @ManyToOne()
    @ToString.Exclude
    @JsonBackReference
    private Wedding wedding;

    @ManyToOne()
    @ToString.Exclude
    @JsonBackReference
    private TypeOfTask typeOfTask;

    @OneToMany(mappedBy = "task")
    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<TaskCost> costs;
}
