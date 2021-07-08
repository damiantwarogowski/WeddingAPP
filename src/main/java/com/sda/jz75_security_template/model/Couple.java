package com.sda.jz75_security_template.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Couple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @OneToMany(mappedBy = "poleCouple", fetch = FetchType.EAGER)
//    private List<Account> AccountList;
    private List<Person> PersonList; // Wydaje nam się, że person, to już stworzony account

    @OneToOne()
    @ToString.Exclude
    @JsonBackReference
    private Wedding wedding;

//    1dowielu
@OneToMany(mappedBy = "poleCouple", fetch = FetchType.EAGER)
private List<Person> personList;
}
