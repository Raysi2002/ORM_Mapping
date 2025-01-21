package com.raysi.onetoonewithspringboot.controller;

import com.raysi.onetoonewithspringboot.dto.VehicleDTO;
import com.raysi.onetoonewithspringboot.dto.VehicleUpdateDTO;
import com.raysi.onetoonewithspringboot.entity.Vehicle;
import com.raysi.onetoonewithspringboot.error.ResourceNotFoundException;
import com.raysi.onetoonewithspringboot.repository.VehicleRepository;
import com.raysi.onetoonewithspringboot.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {

    private final VehicleService vehicleService;
    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/api/vehicle")
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle){
        try{
            vehicleService.saveVehicle(vehicle);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Accept-Datetime")
                    .body(vehicle);
        }catch (RuntimeException e){
            throw  e;
        }catch(Exception e){
            throw new RuntimeException("Something went wrong in the controller layer");
        }
    }

    @PostMapping("/api/vehicles")
    public ResponseEntity<List<Vehicle>> saveVehicles(@RequestBody List<Vehicle> vehicles){
        try{
            vehicleService.saveVehicles(vehicles);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .header("Accept-Datetime")
                    .body(vehicles);
        }catch (RuntimeException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in controller layer");
        }
    }

    @GetMapping("/api/vehicles")
    public ResponseEntity<List<VehicleDTO>> fetchVehicles(){
        try{
            List<VehicleDTO> vehicleDTOS = vehicleService.fetchVehicles();
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .header("Accept-Datetime")
                    .body(vehicleDTOS);
        }catch (RuntimeException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in controller layer");
        }
    }
    @GetMapping("api/vehicle/{id}")
    public ResponseEntity<Optional<VehicleDTO>> fetchVehicleById(@PathVariable Long id){
        try{
            Optional<VehicleDTO> vehicleDTO = vehicleService.fetchVehicleById(id);
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .header("Accept-Datetime")
                    .body(vehicleDTO);
        }catch (RuntimeException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in controller layer");
        }
    }

    @DeleteMapping("api/vehicle/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id){
        try{
            vehicleService.deleteVehicle(id);
            return  ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Accept-Datetime")
                    .body("Vehicle Deleted Successfully!");
        }catch (RuntimeException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Something went wrong in the controller layer");
        }
    }

    @PatchMapping("api/vehicle/{id}")
    public ResponseEntity<Optional<VehicleDTO>> updateVehicle(@PathVariable Long id , @RequestBody VehicleUpdateDTO vehicleUpdateDTO){
        try{
            vehicleService.updateVehicle(id, vehicleUpdateDTO);
            Optional<VehicleDTO> newVehicleStatus = vehicleService.fetchVehicleById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Accept-Datetime")
                    .body(newVehicleStatus);
        } catch (RuntimeException e) {
            throw e;
        }catch (Exception e){
            throw new ResourceNotFoundException("1103", "Something went wrong in Controller layer");
        }
    }
}
