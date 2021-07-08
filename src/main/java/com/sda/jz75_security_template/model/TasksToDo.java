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
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TasksToDo {

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
    private Wedding poleWedding;

    @ManyToOne()
    @ToString.Exclude
    @JsonBackReference
    private TypeOfTask poleTypeOfTask;

    @OneToMany(mappedBy = "poleTasksToDo", fetch = FetchType.EAGER)
    private List<TasksToDo> ReminderList;
}
