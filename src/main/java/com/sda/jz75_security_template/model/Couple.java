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

    //    1dowielu
    @ManyToOne(fetch = FetchType.EAGER)
    private Person groom;

    @ManyToOne(fetch = FetchType.EAGER)
    private Person bride;

    @OneToOne(mappedBy = "couple")
    private Wedding wedding;
}
