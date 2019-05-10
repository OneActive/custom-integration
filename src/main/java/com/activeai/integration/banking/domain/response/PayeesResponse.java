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

  @JsonProperty("payees")
  private List<Payee> payees = null;

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
   * Get payees
   * 
   * @return payees
   **/
  @Valid
  public List<Payee> getPayees() {
    return payees;
  }

  public void setPayees(List<Payee> payees) {
    this.payees = payees;
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
    return Objects.equals(this.result, payeesResponse.result) && Objects.equals(this.payees, payeesResponse.payees);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, payees);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayeesResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    payees: ").append(toIndentedString(payees)).append("\n");
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

