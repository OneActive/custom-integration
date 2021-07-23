package com.activeai.integration.banking.domain.model;

import com.activeai.integration.banking.domain.constants.AddressTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Address
 */
@Validated
public class Address {

  @JsonProperty("addressLine1")
  private String addressLine1 = null;

  @JsonProperty("addressLine2")
  private String addressLine2 = null;

  @JsonProperty("addressLine3")
  private String addressLine3 = null;

  @JsonProperty("addressType")
  private AddressTypeEnum addressType = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("state")
  private String state = null;

  @JsonProperty("province")
  private String province = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("countryCode")
  private String countryCode = null;

  @JsonProperty("locality")
  private String locality;

  @JsonProperty("postalCode")
  private String postalCode;

  public Address addressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
    return this;
  }

  /**
   * Address Line 1
   * 
   * @return addressLine1
   **/
  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public Address addressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
    return this;
  }

  /**
   * Address Line 2
   * 
   * @return addressLine2
   **/
  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public Address addressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
    return this;
  }

  /**
   * Address Line 3
   * 
   * @return addressLine3
   **/
  public String getAddressLine3() {
    return addressLine3;
  }

  public void setAddressLine3(String addressLine3) {
    this.addressLine3 = addressLine3;
  }

  public Address addressType(AddressTypeEnum addressType) {
    this.addressType = addressType;
    return this;
  }

  /**
   * Address type like primary, work, office, etc.
   * 
   * @return addressType
   **/
  public AddressTypeEnum getAddressType() {
    return addressType;
  }

  public void setAddressType(AddressTypeEnum addressType) {
    this.addressType = addressType;
  }

  public Address city(String city) {
    this.city = city;
    return this;
  }

  /**
   * City
   * 
   * @return city
   **/
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Address state(String state) {
    this.state = state;
    return this;
  }

  /**
   * State
   * 
   * @return state
   **/
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public Address province(String province) {
    this.province = province;
    return this;
  }

  /**
   * Province
   * 
   * @return province
   **/
  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public Address country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Country
   * 
   * @return country
   **/
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Address countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Country Code
   * 
   * @return countryCode
   **/
  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  @Override public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Address address = (Address) o;
    return Objects.equals(addressLine1, address.addressLine1) && Objects.equals(addressLine2, address.addressLine2) && Objects
        .equals(addressLine3, address.addressLine3) && addressType == address.addressType && Objects.equals(city, address.city) && Objects
        .equals(state, address.state) && Objects.equals(province, address.province) && Objects.equals(country, address.country) && Objects
        .equals(countryCode, address.countryCode) && Objects.equals(locality, address.locality) && Objects
        .equals(postalCode, address.postalCode);
  }

  @Override public int hashCode() {
    return Objects
        .hash(addressLine1, addressLine2, addressLine3, addressType, city, state, province, country, countryCode, locality, postalCode);
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Address{");
    sb.append("addressLine1='").append(addressLine1).append('\'');
    sb.append(", addressLine2='").append(addressLine2).append('\'');
    sb.append(", addressLine3='").append(addressLine3).append('\'');
    sb.append(", addressType=").append(addressType);
    sb.append(", city='").append(city).append('\'');
    sb.append(", state='").append(state).append('\'');
    sb.append(", province='").append(province).append('\'');
    sb.append(", country='").append(country).append('\'');
    sb.append(", countryCode='").append(countryCode).append('\'');
    sb.append(", locality='").append(locality).append('\'');
    sb.append(", postalCode='").append(postalCode).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

