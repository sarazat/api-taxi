package com.gdm.vehicleapi.position;

import com.gdm.vehicleapi.vehicle.VehicleEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.UUID;

@Entity
public class PositionEntity {

  @Id
  @GeneratedValue(generator = "uuidPosition")
  @GenericGenerator(name = "uuidPosition", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;
  private Double lat;
  private Double lon;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_vehicle")
  private VehicleEntity vehicle;

  private Date created;

  @PrePersist
  protected void onCreate() {
    created = new Date();
  }


  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLon() {
    return lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }

  public VehicleEntity getVehicle() {
    return vehicle;
  }

  public void setVehicle(VehicleEntity vehicle) {
    this.vehicle = vehicle;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }
}
