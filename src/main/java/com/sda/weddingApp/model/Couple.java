package com.sda.weddingApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Couple {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    1dowielu
    @ManyToOne(fetch = FetchType.EAGER)
    private Person groom;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person bride;

    @OneToOne(mappedBy = "couple")
    private Wedding wedding;
}
