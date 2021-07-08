package com.sda.jz75_security_template.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Person {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
