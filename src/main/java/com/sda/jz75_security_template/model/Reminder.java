package com.sda.jz75_security_template.model;

import jdk.jfr.Timespan;
import jdk.jfr.Timestamp;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reminderDay;

    @Basic
    private java.sql.Time reminderTime;

    private String contentTxt;


}
