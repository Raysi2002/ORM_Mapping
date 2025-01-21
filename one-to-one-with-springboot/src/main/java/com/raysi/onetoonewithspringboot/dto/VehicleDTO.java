package com.raysi.onetoonewithspringboot.dto;

import com.raysi.onetoonewithspringboot.entity.Engine;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

public record VehicleDTO(
        String vehicleName,
        Long chessiNo,
        String vehicle,
        Engine engine
) {
}

//private Long vehicleId;
//private String vehicleName;
//private Long chessiNo;
//private String vehicleType;
//private Engine engine;