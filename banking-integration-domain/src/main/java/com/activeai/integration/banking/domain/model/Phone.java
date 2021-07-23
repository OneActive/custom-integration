package com.activeai.integration.banking.domain.model;

import com.activeai.integration.banking.domain.constants.PhoneTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Phone
 */
@Validated
public class Phone {

  @JsonProperty("phoneNo")
  private Long phoneNo = null;

  @JsonProperty("phoneCountryCode")
  private String phoneCountryCode = null;


  @JsonProperty("phoneType")
  private PhoneTypeEnum phoneType = null;

  /**
   * Phone number without country code
   * 
   * @return phoneNo
   **/
  public Long getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(Long phoneNo) {
    this.phoneNo = phoneNo;
  }

  /**
   * country code prefixed with +
   * 
   * @return phoneCountryCode
   **/
  public String getPhoneCountryCode() {
    return phoneCountryCode;
  }

  public void setPhoneCountryCode(String phoneCountryCode) {
    this.phoneCountryCode = phoneCountryCode;
  }

  /**
   * Type of Phone number i.e. work/home/office
   * 
   * @return phoneType
   **/
  public PhoneTypeEnum getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(PhoneTypeEnum phoneType) {
    this.phoneType = phoneType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Phone phone = (Phone) o;
    return Objects.equals(this.phoneNo, phone.phoneNo) && Objects.equals(this.phoneCountryCode, phone.phoneCountryCode)
        && Objects.equals(this.phoneType, phone.phoneType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(phoneNo, phoneCountryCode, phoneType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Phone {\n");

    sb.append("    phoneNo: ").append(toIndentedString(phoneNo)).append("\n");
    sb.append("    phoneCountryCode: ").append(toIndentedString(phoneCountryCode)).append("\n");
    sb.append("    phoneType: ").append(toIndentedString(phoneType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

