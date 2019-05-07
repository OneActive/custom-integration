package com.activeai.integration.banking.domain.response;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.activeai.integration.banking.model.Payee;
import com.activeai.integration.banking.model.Result;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PayeesResponse
 */
@Validated
public class PayeesResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("payeeDetails")
  private List<Payee> payeeDetails = null;

  /**
   * Get result
   * 
   * @return result
   **/
  @Valid
  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  /**
   * Get payeeDetails
   * 
   * @return payeeDetails
   **/
  @Valid
  public List<Payee> getPayeeDetails() {
    return payeeDetails;
  }

  public void setPayeeDetails(List<Payee> payeeDetails) {
    this.payeeDetails = payeeDetails;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PayeesResponse payeesResponse = (PayeesResponse) o;
    return Objects.equals(this.result, payeesResponse.result) && Objects.equals(this.payeeDetails, payeesResponse.payeeDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, payeeDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayeesResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    payeeDetails: ").append(toIndentedString(payeeDetails)).append("\n");
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

