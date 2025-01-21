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
public class Vehicle {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehicle_seq"
    )
    @SequenceGenerator(
            name = "vehicle_seq",
            sequenceName = "vehicle_sequence",
            allocationSize = 116
    )
    private Long vehicleId;
    private String vehicleName;
    private Long chessiNo;
    private String vehicleType; // Fueled or Electric
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Engine engine;


}
