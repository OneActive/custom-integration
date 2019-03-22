package com.activeai.integration.banking.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

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

  /**
   * Address type like primary, work, office, etc.
   */
  public enum AddressTypeEnum {
    PRIMARY("PRIMARY"),

    OFFICE("OFFICE"),

    WORK("WORK");

    private String value;

    AddressTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AddressTypeEnum fromValue(String text) {
      for (AddressTypeEnum b : AddressTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Address address = (Address) o;
    return Objects.equals(this.addressLine1, address.addressLine1) && Objects.equals(this.addressLine2, address.addressLine2)
        && Objects.equals(this.addressLine3, address.addressLine3) && Objects.equals(this.addressType, address.addressType)
        && Objects.equals(this.city, address.city) && Objects.equals(this.state, address.state)
        && Objects.equals(this.province, address.province) && Objects.equals(this.country, address.country)
        && Objects.equals(this.countryCode, address.countryCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addressLine1, addressLine2, addressLine3, addressType, city, state, province, country, countryCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Address {\n");

    sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
    sb.append("    addressLine2: ").append(toIndentedString(addressLine2)).append("\n");
    sb.append("    addressLine3: ").append(toIndentedString(addressLine3)).append("\n");
    sb.append("    addressType: ").append(toIndentedString(addressType)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    province: ").append(toIndentedString(province)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

