package com.gbm.vehicle.pojos;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Position
 */
@Validated

public class Position   {
  @JsonProperty("id")
  private UUID id = null;

  @JsonProperty("lat")
  private String lat = null;

  @JsonProperty("lon")
  private String lon = null;

  @JsonProperty("timestamp")
  private String timestamp = null;

  public Position id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")

  @Valid

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Position lat(String lat) {
    this.lat = lat;
    return this;
  }

  /**
   * Get lat
   * @return lat
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public Position lon(String lon) {
    this.lon = lon;
    return this;
  }

  /**
   * Get lon
   * @return lon
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getLon() {
    return lon;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }

  public Position timestamp(String timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  **/
  @ApiModelProperty(value = "")


  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Position position = (Position) o;
    return Objects.equals(this.id, position.id) &&
        Objects.equals(this.lat, position.lat) &&
        Objects.equals(this.lon, position.lon) &&
        Objects.equals(this.timestamp, position.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lat, lon, timestamp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Position {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

