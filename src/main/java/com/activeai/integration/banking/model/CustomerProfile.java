package com.activeai.integration.banking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * CustomerProfile
 */
@Validated
public class CustomerProfile {

  @JsonProperty("custId")
  private Integer custId = null;

  @JsonProperty("custSegment")
  private String custSegment = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("middleName")
  private String middleName = null;

  /**
   * Tile
   */
  public enum TitleEnum {
    MR_("Mr."),

    MRS_("Mrs."),

    MS_("Ms."),

    MISS_("Miss.");

    private String value;

    TitleEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TitleEnum fromValue(String text) {
      for (TitleEnum b : TitleEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("title")
  private TitleEnum title = null;

  @JsonProperty("address")
  private Address address = null;

  @JsonProperty("phone")
  @Valid
  private List<Phone> phone = null;

  public CustomerProfile custId(Integer custId) {
    this.custId = custId;
    return this;
  }

  /**
   * Customer ID example = "80975412"
   * 
   * @return custId
   **/
  public Integer getCustId() {
    return custId;
  }

  public void setCustId(Integer custId) {
    this.custId = custId;
  }

  public CustomerProfile custSegment(String custSegment) {
    this.custSegment = custSegment;
    return this;
  }

  /**
   * segment id / code of customer
   * 
   * @return custSegment
   **/
  public String getCustSegment() {
    return custSegment;
  }

  public void setCustSegment(String custSegment) {
    this.custSegment = custSegment;
  }

  public CustomerProfile firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * First name of customer
   * 
   * @return firstName
   **/
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public CustomerProfile lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Last name of customer
   * 
   * @return lastName
   **/
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public CustomerProfile middleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  /**
   * Middle name of customer
   * 
   * @return middleName
   **/
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public CustomerProfile title(TitleEnum title) {
    this.title = title;
    return this;
  }

  /**
   * Tile
   * 
   * @return title
   **/
  public TitleEnum getTitle() {
    return title;
  }

  public void setTitle(TitleEnum title) {
    this.title = title;
  }

  public CustomerProfile address(Address address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * 
   * @return address
   **/
  @Valid
  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public CustomerProfile phone(List<Phone> phone) {
    this.phone = phone;
    return this;
  }

  public CustomerProfile addPhoneItem(Phone phoneItem) {
    if (this.phone == null) {
      this.phone = new ArrayList<Phone>();
    }
    this.phone.add(phoneItem);
    return this;
  }

  /**
   * Get phone
   * 
   * @return phone
   **/
  @Valid
  public List<Phone> getPhone() {
    return phone;
  }

  public void setPhone(List<Phone> phone) {
    this.phone = phone;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerProfile customerProfile = (CustomerProfile) o;
    return Objects.equals(this.custId, customerProfile.custId) && Objects.equals(this.custSegment, customerProfile.custSegment)
        && Objects.equals(this.firstName, customerProfile.firstName) && Objects.equals(this.lastName, customerProfile.lastName)
        && Objects.equals(this.middleName, customerProfile.middleName) && Objects.equals(this.title, customerProfile.title)
        && Objects.equals(this.address, customerProfile.address) && Objects.equals(this.phone, customerProfile.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(custId, custSegment, firstName, lastName, middleName, title, address, phone);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerProfile {\n");

    sb.append("    custId: ").append(toIndentedString(custId)).append("\n");
    sb.append("    custSegment: ").append(toIndentedString(custSegment)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    middleName: ").append(toIndentedString(middleName)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
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

