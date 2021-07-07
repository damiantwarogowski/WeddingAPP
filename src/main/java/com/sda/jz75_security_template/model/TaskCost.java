package com.sda.jz75_security_template.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

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
}
