package com.gdm.vehicleapi.hook;

import com.gdm.vehicleapi.vehicle.VehicleEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.util.Date;
import java.util.UUID;

@Entity
public class HookEntity {

  @Id
  @GeneratedValue(generator = "uuidHook")
  @GenericGenerator(name = "uuidHook", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_vehicle")
  private VehicleEntity vehicle;
  private String payLoadUrl;
  private Date created;
  private Date updated;

  private int latestStatusCode;

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

  public VehicleEntity getVehicle() {
    return vehicle;
  }

  public void setVehicle(VehicleEntity vehicle) {
    this.vehicle = vehicle;
  }

  public String getPayLoadUrl() {
    return payLoadUrl;
  }

  public void setPayLoadUrl(String payLoadUrl) {
    this.payLoadUrl = payLoadUrl;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  @PreUpdate
  public void setUpdated() {
    this.updated = new Date();
  }

  public int getLatestStatusCode() {
    return latestStatusCode;
  }

  public void setLatestStatusCode(int latestStatusCode) {
    this.latestStatusCode = latestStatusCode;
  }


}
