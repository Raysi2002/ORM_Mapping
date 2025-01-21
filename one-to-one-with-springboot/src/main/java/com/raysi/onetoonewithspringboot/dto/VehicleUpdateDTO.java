package com.raysi.onetoonewithspringboot.dto;

import com.raysi.onetoonewithspringboot.entity.Engine;
import com.raysi.onetoonewithspringboot.entity.Vehicle;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleUpdateDTO {
    private Long vehicleId;
    private String vehicleName;
    private Long chessiNo;
    private String vehicleType;
    private Engine engine;
}
