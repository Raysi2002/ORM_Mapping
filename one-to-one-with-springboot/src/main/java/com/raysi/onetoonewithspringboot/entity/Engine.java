package com.raysi.onetoonewithspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Engine {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "engine_seq"
    )
    @SequenceGenerator(
            name = "engine_seq",
            sequenceName = "engine_sequence",
            allocationSize = 116
    )
    private Long engineId;
    private String companyName;
    private Integer hp;
    private Integer noOfCylinders;
    private String engineType;
}
