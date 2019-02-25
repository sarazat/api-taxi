package com.gdm.vehicleapi.hook;

import com.gdm.vehicleapi.vehicle.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HookRepository extends JpaRepository<HookEntity, UUID> {

  Page<HookEntity> findAllByVehicleOrderByCreatedDesc(VehicleEntity vehicle, Pageable pageable);
  List<HookEntity> findAllByVehicleOrderByCreatedDesc(VehicleEntity vehicle);
}
