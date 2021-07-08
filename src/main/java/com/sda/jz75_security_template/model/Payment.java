package com.sda.jz75_security_template.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double paymentAmount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePaymentAmount;

    @ManyToOne()
    @ToString.Exclude
    @JsonBackReference
    private TaskCost poleTaskCost;
}
