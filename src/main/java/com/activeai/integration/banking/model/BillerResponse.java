package com.activeai.integration.banking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BillerResponse
 */
@Validated
public class BillerResponse   {
  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("billers")
  @Valid
  private List<Biller> billers = null;

  /**
   * Get result
   * @return result
  **/
  @Valid
  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public BillerResponse addBillersItem(Biller billersItem) {
    if (this.billers == null) {
      this.billers = new ArrayList<Biller>();
    }
    this.billers.add(billersItem);
    return this;
  }

  /**
   * Get billers
   * @return billers
  **/
  @Valid
  public List<Biller> getBillers() {
    return billers;
  }

  public void setBillers(List<Biller> billers) {
    this.billers = billers;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BillerResponse billerList = (BillerResponse) o;
    return Objects.equals(this.result, billerList.result) &&
        Objects.equals(this.billers, billerList.billers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, billers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BillerList {\n");
    
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    billers: ").append(toIndentedString(billers)).append("\n");
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

