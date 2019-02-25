package com.gdm.vehicleapi;

import com.gbm.vehicle.pojos.Hook;
import com.gbm.vehicle.pojos.Position;
import com.gbm.vehicle.pojos.Vehicle;
import com.gdm.vehicleapi.hook.HookEntity;
import com.gdm.vehicleapi.position.PositionEntity;
import com.gdm.vehicleapi.vehicle.VehicleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

@Configuration
public class Config {


  @Bean
  ModelMapper getModelMapper() {

    ModelMapper modelMapper = new ModelMapper();

    modelMapper.createTypeMap(Date.class, OffsetDateTime.class)
      .setConverter(context -> OffsetDateTime.ofInstant(context.getSource().toInstant(), ZoneId.systemDefault()));

    modelMapper.createTypeMap(OffsetDateTime.class, Date.class)
      .setConverter(context -> Date.from(context.getSource().atZoneSameInstant(ZoneId.systemDefault()).toInstant()));


    modelMapper.createTypeMap(PositionEntity.class, Position.class)
      .addMapping(PositionEntity::getCreated, Position::setTimestamp);




    return modelMapper;
  }



  @Bean
  Function<VehicleEntity, Vehicle> mapVehicleEntityToPojo() {
    return entity -> getModelMapper().map(entity, Vehicle.class);
  }


  @Bean
  Function<Vehicle, VehicleEntity> mapVehiclePojoToEntity() {
    return pojo -> getModelMapper().map(pojo, VehicleEntity.class);
  }

  @Bean
  Function<PositionEntity, Position> mapPositionEntityToPojo() {
    return entity -> getModelMapper().map(entity, Position.class);
  }

  @Bean
  Function<Position, PositionEntity> mapPositionPojoToEntity() {
    return pojo -> getModelMapper().map(pojo, PositionEntity.class);
  }


  @Bean
  Function<HookEntity, Hook> mapSubscribeEntityToPojo() {
    return entity -> getModelMapper().map(entity, Hook.class);
  }

  @Bean
  Function<Hook, HookEntity> mapSubscribePojoToEntity() {
    return pojo -> getModelMapper().map(pojo, HookEntity.class);
  }
}
