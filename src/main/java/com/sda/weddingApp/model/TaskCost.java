package com.sda.weddingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalCost;

    private Double paymentAmount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPayment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPaymentDeadline;

    @OneToMany(mappedBy = "taskCost", fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<Payment> payments;

    @ManyToOne()
    private TaskToDo task;
}