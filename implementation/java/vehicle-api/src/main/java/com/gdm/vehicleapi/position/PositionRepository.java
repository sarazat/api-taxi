package com.gdm.vehicleapi.position;

import com.gbm.vehicle.pojos.Vehicle;
import com.gdm.vehicleapi.vehicle.VehicleEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<PositionEntity, UUID> {

  Page<PositionEntity> findAllByVehicleOrderByCreatedDesc(VehicleEntity vehicle, Pageable pageable);
}
