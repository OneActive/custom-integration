package com.activeai.integration.banking.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geocodes {

  @JsonProperty("latitude")
  private Double latitude;

  @JsonProperty("longitude")
  private Double longitude;

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Geocodes{");
    sb.append("latitude=").append(latitude);
    sb.append(", longitude=").append(longitude);
    sb.append('}');
    return sb.toString();
  }
}
