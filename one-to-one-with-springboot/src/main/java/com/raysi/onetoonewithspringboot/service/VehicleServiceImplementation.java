package com.raysi.onetoonewithspringboot.service;

import com.raysi.onetoonewithspringboot.dto.VehicleDTO;
import com.raysi.onetoonewithspringboot.dto.VehicleUpdateDTO;
import com.raysi.onetoonewithspringboot.entity.Vehicle;
import com.raysi.onetoonewithspringboot.error.ResourceNotFoundException;
import com.raysi.onetoonewithspringboot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImplementation implements VehicleService {

    private final VehicleRepository vehicleRepository;
    @Autowired
    public VehicleServiceImplementation(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        try{
            vehicleRepository.save(vehicle);
        }catch (RuntimeException e){
            throw new RuntimeException("Something went wrong in the service layer");
        }
    }

    @Override
    public void saveVehicles(List<Vehicle> vehicles) {
        if(vehicles.isEmpty()){
            throw new ResourceNotFoundException("701", "Can't save the empty list of vehicles");
        }
        try{
            vehicleRepository.saveAll(vehicles);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in the service layer");
        }
    }

    @Override
    public List<VehicleDTO> fetchVehicles() {
        List<VehicleDTO> vehicleDTOS = vehicleRepository.findAll()
                .stream()
                .map(vehicle -> new VehicleDTO(
                        vehicle.getVehicleName(),
                        vehicle.getChessiNo(),
                        vehicle.getVehicleType(),
                        vehicle.getEngine()
                )).toList();
        if (vehicleDTOS.isEmpty()){
            throw new ResourceNotFoundException("801", "No data available");
        }
        try {
            return vehicleDTOS;
        }catch (Exception e){
            throw new ResourceNotFoundException("802", "Something went wrong in service layer");
        }
    }

    @Override
    public Optional<VehicleDTO> fetchVehicleById(Long id) {
        Optional<VehicleDTO> vehicleDTO = vehicleRepository.findById(id)
                .map(vehicle1 -> new VehicleDTO(
                        vehicle1.getVehicleName(),
                        vehicle1.getChessiNo(),
                        vehicle1.getVehicleType(),
                        vehicle1.getEngine()
                ));
        if (vehicleDTO.isEmpty())
            throw new ResourceNotFoundException("901", "No vehicle found with this ID: " + id);
        try {
            return vehicleDTO;
        }catch (Exception e){
            throw new ResourceNotFoundException("902", "Something went wrong in the service layer");
        }
    }

    @Override
    public void deleteVehicle(Long id) {
        Optional<VehicleDTO> vehicleDTO = vehicleRepository.findById(id)
                .map(vehicle1 -> new VehicleDTO(
                        vehicle1.getVehicleName(),
                        vehicle1.getChessiNo(),
                        vehicle1.getVehicleType(),
                        vehicle1.getEngine()
                ));
        if (vehicleDTO.isEmpty())
            throw new ResourceNotFoundException("901", "No vehicle found with this ID: " + id);
        try {
            vehicleRepository.deleteById(id);
        }catch (Exception e){
            throw new ResourceNotFoundException("1002", "Something went wrong in service layer");
        }
    }

    @Override
    public void updateVehicle(Long id, VehicleUpdateDTO vehicleUpdateDTO) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("1101", "Data is empty"));
        try{
            if (vehicleUpdateDTO.getVehicleName() != null){
                existingVehicle.setVehicleName(vehicleUpdateDTO.getVehicleName());
            }
            if (vehicleUpdateDTO.getVehicleType() != null){
                existingVehicle.setVehicleType(vehicleUpdateDTO.getVehicleType());
            }
            if (vehicleUpdateDTO.getChessiNo() != null)
                existingVehicle.setChessiNo(vehicleUpdateDTO.getChessiNo());
            if (vehicleUpdateDTO.getEngine() != null)
                existingVehicle.setEngine(vehicleUpdateDTO.getEngine());

            vehicleRepository.save(existingVehicle);
        }catch (RuntimeException e){
            throw new ResourceNotFoundException("1102", "Something went wrong in service layer");
        }
    }
}
