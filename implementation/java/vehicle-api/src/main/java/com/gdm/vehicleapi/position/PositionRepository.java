package com.gdm.vehicleapi.position;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PositionRepository extends JpaRepository<PositionEntity, UUID> {
}
