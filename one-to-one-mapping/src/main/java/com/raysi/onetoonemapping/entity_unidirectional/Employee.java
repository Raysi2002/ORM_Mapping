package com.raysi.onetoonemapping.entity_unidirectional;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String name;
    // Bean validation for email format
    @Email
    private String email;

    // Unidirectional one-to-one relationship configuration:
    // - cascade = ALL: all operations (persist, remove, refresh, merge, detach) cascade to Address
    // - fetch = EAGER: Address is loaded immediately with Employee
    // - orphanRemoval: removes Address when it's no longer referenced by Employee
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    // Creates a foreign key column 'address_code' in Employee table
    // referencing the addressCode column in Address table
    @JoinColumn(name = "address_code")
    private Address address;
}
