package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.constants.LocatorTypeEnum;
import com.activeai.integration.banking.model.Address;
import com.activeai.integration.banking.model.Geocodes;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AtmLocatorRequest {

  @JsonProperty("requestInfo")
  private String requestInfo;

  @JsonProperty("fetchType")
  private String fetchType;

  @JsonProperty("locateRequestType")
  private LocatorTypeEnum locateRequestType;

  @JsonProperty("distanceUnit")
  private String distanceUnit;

  @JsonProperty("distanceValue")
  private Double distanceValue;

  @JsonProperty("geocodes")
  private Geocodes geocodes;

  @JsonProperty("address")
  private Address address;

  @JsonProperty("pageIndex")
  private Integer pageIndex;

  @JsonProperty("listCount")
  private Integer listCount;

  public String getRequestInfo() {
    return requestInfo;
  }

  public void setRequestInfo(String requestInfo) {
    this.requestInfo = requestInfo;
  }

  public LocatorTypeEnum getLocateRequestType() {
    return locateRequestType;
  }

  public void setLocateRequestType(LocatorTypeEnum locateRequestType) {
    this.locateRequestType = locateRequestType;
  }

  public String getFetchType() {
    return fetchType;
  }

  public void setFetchType(String fetchType) {
    this.fetchType = fetchType;
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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
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

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("AtmLocatorRequest{");
    sb.append("requestInfo='").append(requestInfo).append('\'');
    sb.append(", fetchType='").append(fetchType).append('\'');
    sb.append(", locateRequestType=").append(locateRequestType);
    sb.append(", distanceUnit='").append(distanceUnit).append('\'');
    sb.append(", distanceValue=").append(distanceValue);
    sb.append(", geocodes=").append(geocodes);
    sb.append(", address=").append(address);
    sb.append(", pageIndex=").append(pageIndex);
    sb.append(", listCount=").append(listCount);
    sb.append('}');
    return sb.toString();
  }
}
