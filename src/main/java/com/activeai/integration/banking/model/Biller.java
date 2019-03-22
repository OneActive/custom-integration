package com.activeai.integration.banking.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Biller
 */
@Validated
public class Biller {

  @JsonProperty("billerId")
  private String billerId = null;

  @JsonProperty("billerName")
  private String billerName = null;

  @JsonProperty("billerCategory")
  private String billerCategory = null;

  @JsonProperty("allowPartialPay")
  private Boolean allowPartialPay = null;

  public Biller billerId(String billerId) {
    this.billerId = billerId;
    return this;
  }

  /**
   * Biller ID
   * 
   * @return billerId
   **/
  public String getBillerId() {
    return billerId;
  }

  public void setBillerId(String billerId) {
    this.billerId = billerId;
  }

  public Biller billerName(String billerName) {
    this.billerName = billerName;
    return this;
  }

  /**
   * Biller Name
   * 
   * @return billerName
   **/
  public String getBillerName() {
    return billerName;
  }

  public void setBillerName(String billerName) {
    this.billerName = billerName;
  }

  public Biller billerCategory(String billerCategory) {
    this.billerCategory = billerCategory;
    return this;
  }

  /**
   * Biller category
   * 
   * @return billerCategory
   **/
  public String getBillerCategory() {
    return billerCategory;
  }

  public void setBillerCategory(String billerCategory) {
    this.billerCategory = billerCategory;
  }

  public Biller allowPartialPay(Boolean allowPartialPay) {
    this.allowPartialPay = allowPartialPay;
    return this;
  }

  /**
   * Does this biller allow partial pay
   * 
   * @return allowPartialPay
   **/
  public Boolean isAllowPartialPay() {
    return allowPartialPay;
  }

  public void setAllowPartialPay(Boolean allowPartialPay) {
    this.allowPartialPay = allowPartialPay;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Biller biller = (Biller) o;
    return Objects.equals(this.billerId, biller.billerId) && Objects.equals(this.billerName, biller.billerName)
        && Objects.equals(this.billerCategory, biller.billerCategory) && Objects.equals(this.allowPartialPay, biller.allowPartialPay);
  }

  @Override
  public int hashCode() {
    return Objects.hash(billerId, billerName, billerCategory, allowPartialPay);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Biller {\n");

    sb.append("    billerId: ").append(toIndentedString(billerId)).append("\n");
    sb.append("    billerName: ").append(toIndentedString(billerName)).append("\n");
    sb.append("    billerCategory: ").append(toIndentedString(billerCategory)).append("\n");
    sb.append("    allowPartialPay: ").append(toIndentedString(allowPartialPay)).append("\n");
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

