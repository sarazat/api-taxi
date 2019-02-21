package com.gdm.vehicleapi;

import com.gbm.vehicle.pojos.Position;
import com.gbm.vehicle.pojos.Vehicle;
import com.gdm.vehicleapi.position.PositionEntity;
import com.gdm.vehicleapi.vehicle.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

@Configuration
public class Config {


  ModelMapper modelMapper;

  public Config() {

    this.modelMapper = new ModelMapper();

    this.modelMapper.createTypeMap(Date.class, OffsetDateTime.class)
      .setConverter(context -> OffsetDateTime.ofInstant(context.getSource().toInstant(), ZoneId.systemDefault()));

    this.modelMapper.createTypeMap(OffsetDateTime.class, Date.class)
      .setConverter(context -> Date.from(context.getSource().atZoneSameInstant(ZoneId.systemDefault()).toInstant()));

  }


  @Bean
  Function<VehicleEntity, Vehicle> mapVehicleEntityToPojo() {
    return entity -> modelMapper.map(entity, Vehicle.class);
  }


  @Bean
  Function<Vehicle, VehicleEntity> mapVehiclePojoToEntity() {
    return pojo -> modelMapper.map(pojo, VehicleEntity.class);
  }

  @Bean
  Function<PositionEntity, Position> mapPositionEntityToPojo() {
    return entity -> modelMapper.map(entity, Position.class);
  }

  @Bean
  Function<Position, PositionEntity> mapPositionPojoToEntity() {
    return pojo -> modelMapper.map(pojo, PositionEntity.class);
  }

}
