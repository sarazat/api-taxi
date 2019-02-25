package com.gdm.vehicleapi.position;

import com.gbm.vehicle.pojos.Position;
import com.gdm.vehicleapi.GenericService;
import com.gdm.vehicleapi.hook.HookCommand;
import com.gdm.vehicleapi.vehicle.VehicleEntity;
import com.gdm.vehicleapi.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
public class PositionService extends GenericService<PositionEntity, Position, PositionRepository> {

  @Autowired
  VehicleService vehicleService;

  @Autowired
  @Transient
  HookCommand hookCommand;

  @Autowired
  public PositionService(PositionRepository positionRepository,
                         Function<PositionEntity, Position> mapEntityToPojo,
                         Function<Position, PositionEntity> mapPojoToEntity) {
    super(positionRepository, mapEntityToPojo, mapPojoToEntity);
  }

  public Position save(Position position, UUID vehicleUuid) {

    Optional<VehicleEntity> vehicleEntityOptional = vehicleService.findById(vehicleUuid);

    PositionEntity positionEntity = super.mapPojoToEntity.apply(position);

    vehicleEntityOptional.ifPresent(positionEntity::setVehicle);
    vehicleEntityOptional.orElseThrow(IllegalArgumentException::new); //If vehicle doesn't exist explode!!
    positionEntity = save(positionEntity);

    return mapEntityToPojo.apply(positionEntity);

  }


  public Page<Position> findAllPositionsByVehicleId(UUID vehicleUuid, Pageable pageable) {
    Optional<VehicleEntity> vehicleEntityOptional = vehicleService.findById(vehicleUuid);
    vehicleEntityOptional.orElseThrow(IllegalArgumentException::new); //If vehicle doesn't exist explode!!
    return r.findAllByVehicleOrderByCreatedDesc(vehicleEntityOptional.get(), pageable).map(mapEntityToPojo);
  }

  @Override
  public PositionEntity save(PositionEntity positionEntity) {
    positionEntity =  super.save(positionEntity);

    try {
      hookCommand.triggerHook(positionEntity);
    } catch (IOException e) {
      System.out.println("It's dead larry");
      e.printStackTrace();
    }
    return positionEntity;
  }
}
