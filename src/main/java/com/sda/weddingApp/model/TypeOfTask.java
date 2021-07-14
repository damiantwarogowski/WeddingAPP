package com.sda.weddingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "typeOfTask", fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private Set<TaskToDo> tasks;

    // todo: dodać pole "czy pytamy o ten rodzaj zadania w ankiecie?"
}
