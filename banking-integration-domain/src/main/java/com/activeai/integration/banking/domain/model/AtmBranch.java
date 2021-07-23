package com.activeai.integration.banking.domain.model;

import com.activeai.integration.banking.domain.constants.LocatorTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

public class AtmBranch {

  @JsonProperty("locateResponseType")
  private LocatorTypeEnum locateResponseType;

  @JsonProperty("address")
  private Address address;

  @JsonProperty("name")
  private String name;

  @JsonProperty("distanceUnit")
  private String distanceUnit;

  @JsonProperty("distanceValue")
  private Double distanceValue;

  @JsonProperty("geocodes")
  private Geocodes geocodes;

  @JsonProperty("pageIndex")
  private Integer pageIndex;

  @JsonProperty("listCount")
  private Integer listCount;

  @JsonProperty("operatingHours")
  private String operatingHours;

  @JsonProperty("phoneNumber")
  private String phoneNumber;

  @JsonProperty("fax")
  private String fax;

  @JsonProperty("addtionalProperties")
  private Map<String, Object> addtionalProperties;

  public LocatorTypeEnum getLocateResponseType() {
    return locateResponseType;
  }

  public void setLocateResponseType(LocatorTypeEnum locateResponseType) {
    this.locateResponseType = locateResponseType;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDistanceUnit() {
    return distanceUnit;
  }

  public void setDistanceUnit(String distanceUnit) {
    this.distanceUnit = distanceUnit;
  }

  public Double getDistanceValue() {
    return distanceValue;
  }

  public void setDistanceValue(Double distanceValue) {
    this.distanceValue = distanceValue;
  }

  public Geocodes getGeocodes() {
    return geocodes;
  }

  public void setGeocodes(Geocodes geocodes) {
    this.geocodes = geocodes;
  }

  public Integer getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }

  public Integer getListCount() {
    return listCount;
  }

  public void setListCount(Integer listCount) {
    this.listCount = listCount;
  }

  public String getOperatingHours() {
    return operatingHours;
  }

  public void setOperatingHours(String operatingHours) {
    this.operatingHours = operatingHours;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public Map<String, Object> getAddtionalProperties() {
    return addtionalProperties;
  }

  public void setAddtionalProperties(Map<String, Object> addtionalProperties) {
    this.addtionalProperties = addtionalProperties;
  }

  @Override public String toString() {
    return new ToStringBuilder(this).append("locateResponseType", locateResponseType).append("address", address).append("name", name)
        .append("distanceUnit", distanceUnit).append("distanceValue", distanceValue).append("geocodes", geocodes)
        .append("pageIndex", pageIndex).append("listCount", listCount).append("operatingHours", operatingHours)
        .append("phoneNumber", phoneNumber).append("fax", fax).append("addtionalProperties", addtionalProperties).toString();
  }
}
