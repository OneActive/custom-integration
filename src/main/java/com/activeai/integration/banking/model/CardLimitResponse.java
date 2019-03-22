package com.activeai.integration.banking.model;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CardLimitResponse
 */
@Validated
public class CardLimitResponse   {
  
  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("cardDetail")
  private CardLimit cardDetail = null;

  public CardLimitResponse result(Result result) {
    this.result = result;
    return this;
  }

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

  public CardLimitResponse cardDetail(CardLimit cardDetail) {
    this.cardDetail = cardDetail;
    return this;
  }

  /**
   * Get cardDetail
   * @return cardDetail
  **/
  @Valid
  public CardLimit getCardDetail() {
    return cardDetail;
  }

  public void setCardDetail(CardLimit cardDetail) {
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
    CardLimitResponse cardLimitResponse = (CardLimitResponse) o;
    return Objects.equals(this.result, cardLimitResponse.result) &&
        Objects.equals(this.cardDetail, cardLimitResponse.cardDetail);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, cardDetail);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardLimitResponse {\n");
    
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    cardDetail: ").append(toIndentedString(cardDetail)).append("\n");
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

