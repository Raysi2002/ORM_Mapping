package com.raysi.onetoonemapping.entity_unidirectional;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressCode;
    @Size(min = 4, max = 10)
    private String pinCode;
    private String city;
    private String state;
    private String country;

}
