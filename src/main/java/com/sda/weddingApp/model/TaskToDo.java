package com.sda.weddingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
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
public class TaskToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reminderDay;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadlineDay;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime timeOfTask;

    private Double plannedCost;

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

    private Double allCosts;

//    public Double sumAllCosts (){
//        for (int i = 0; i <= costs.size() ; i++) {
//            allCosts += costs.
//        }
//
//    }
}


