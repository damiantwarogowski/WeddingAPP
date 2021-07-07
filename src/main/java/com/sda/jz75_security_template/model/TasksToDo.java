package com.sda.jz75_security_template.model;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
