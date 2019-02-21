package com.gdm.vehicleapi.vehicle;

import com.gdm.vehicleapi.position.PositionEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
public class VehicleEntity {

  @Id
  @GeneratedValue(generator = "uuidVehicle")
  @GenericGenerator(name = "uuidVehicle", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;
  @Column(unique=true)
  private String plate;
  @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
  private List<PositionEntity> positions;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getPlate() {
    return plate;
  }

  public void setPlate(String plate) {
    this.plate = plate;
  }
}
