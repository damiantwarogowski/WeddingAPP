package com.sda.jz75_security_template.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "poleTaskCost", fetch = FetchType.EAGER)
    private List<Payment> paymentList;


}
