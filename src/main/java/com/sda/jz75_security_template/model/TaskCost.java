package com.sda.jz75_security_template.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TaskCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalCost;

    private double bailCost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate totalCostDeadline;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bailCostDeadline;

    @OneToMany(mappedBy = "taskCost", fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<Payment> payments;

    @ManyToOne()
    private TaskToDo task;
}
