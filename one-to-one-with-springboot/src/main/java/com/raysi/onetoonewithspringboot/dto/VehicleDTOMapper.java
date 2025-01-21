package com.raysi.onetoonewithspringboot.dto;

import com.raysi.onetoonewithspringboot.entity.Vehicle;

import java.util.function.Function;

public class VehicleDTOMapper implements Function<Vehicle, VehicleDTO> {
    @Override
    public VehicleDTO apply(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getVehicleName(),
                vehicle.getChessiNo(),
                vehicle.getVehicleType(),
                vehicle.getEngine()
        );
    }
}
