package com.gdm.vehicleapi.hook;

import com.gbm.vehicle.pojos.Hook;
import com.gbm.vehicle.pojos.Position;
import com.gdm.vehicleapi.GenericService;
import com.gdm.vehicleapi.vehicle.VehicleEntity;
import com.gdm.vehicleapi.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
public class HookService extends GenericService<HookEntity, Hook, HookRepository> {

  @Autowired
  VehicleService vehicleService;

  public HookService(HookRepository hookRepository,
                     Function<HookEntity, Hook> mapEntityToPojo,
                     Function<Hook, HookEntity> mapPojoToEntity) {
    super(hookRepository, mapEntityToPojo, mapPojoToEntity);
  }

  public Hook save(Hook subscribe, UUID vehicleUuid) {

    Optional<VehicleEntity> vehicleEntityOptional = vehicleService.findById(vehicleUuid);

    HookEntity hookEntity = super.mapPojoToEntity.apply(subscribe);

    vehicleEntityOptional.ifPresent(hookEntity::setVehicle);
    vehicleEntityOptional.orElseThrow(IllegalArgumentException::new); //If vehicle doesn't exist explode!!
    hookEntity = save(hookEntity);

    return mapEntityToPojo.apply(hookEntity);

  }

  public Page<Hook> findAllPositionsByVehicleId(UUID vehicleUuid, Pageable pageable) {

    Optional<VehicleEntity> vehicleEntityOptional = vehicleService.findById(vehicleUuid);
    vehicleEntityOptional.orElseThrow(IllegalArgumentException::new); //If vehicle doesn't exist explode!!
    return r.findAllByVehicleOrderByCreatedDesc(vehicleEntityOptional.get(), pageable).map(mapEntityToPojo);

  }

  public List<HookEntity> findAllPositionsByVehicle(VehicleEntity vehicleEntity){
   return r.findAllByVehicleOrderByCreatedDesc(vehicleEntity);
  }

}
