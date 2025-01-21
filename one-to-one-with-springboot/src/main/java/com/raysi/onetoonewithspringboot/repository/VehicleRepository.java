package com.raysi.onetoonewithspringboot.repository;

import com.raysi.onetoonewithspringboot.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
