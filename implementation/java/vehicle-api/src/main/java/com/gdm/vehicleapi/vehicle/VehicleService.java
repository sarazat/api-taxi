package com.gdm.vehicleapi.vehicle;

import com.gbm.vehicle.pojos.Vehicle;
import com.gdm.vehicleapi.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class VehicleService extends GenericService<VehicleEntity,
  Vehicle, VehicleRepository> {

@Autowired
  public VehicleService(VehicleRepository vehicleRepository,
                        Function<VehicleEntity, Vehicle> mapEntityToPojo,
                        Function<Vehicle, VehicleEntity> mapPojoToEntity) {
    super(vehicleRepository, mapEntityToPojo, mapPojoToEntity);
  }
}
