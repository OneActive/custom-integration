package com.activeai.integration.banking.model;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * PayeesResponse
 */
@Validated
public class PayeesResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("cardDetail")
  private List<Payee> cardDetail = null;

  public PayeesResponse result(Result result) {
    this.result = result;
    return this;
  }

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
   * Get cardDetail
   * 
   * @return cardDetail
   **/
  @Valid
  public List<Payee> getCardDetail() {
    return cardDetail;
  }

  public void setCardDetail(List<Payee> cardDetail) {
    this.cardDetail = cardDetail;
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
    return Objects.equals(this.result, payeesResponse.result) && Objects.equals(this.cardDetail, payeesResponse.cardDetail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, cardDetail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PayeesResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    cardDetail: ").append(toIndentedString(cardDetail)).append("\n");
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

