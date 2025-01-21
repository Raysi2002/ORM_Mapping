package com.raysi.onetoonewithspringboot.service;

import com.raysi.onetoonewithspringboot.dto.VehicleDTO;
import com.raysi.onetoonewithspringboot.dto.VehicleUpdateDTO;
import com.raysi.onetoonewithspringboot.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VehicleService {
    void saveVehicle(Vehicle vehicle);
    void saveVehicles(List<Vehicle> vehicles);
    List<VehicleDTO> fetchVehicles();
    Optional<VehicleDTO> fetchVehicleById(Long id);
    void deleteVehicle(Long id);
    void updateVehicle(Long id, VehicleUpdateDTO vehicleUpdateDTO);
}
