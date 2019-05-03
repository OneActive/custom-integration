package com.activeai.integration.banking.domain.response;

import java.util.Objects;

import javax.validation.Valid;

import com.activeai.integration.banking.model.Card;
import com.activeai.integration.banking.model.Result;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CardDetailResponse
 */
@Validated
public class CardDetailResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("cardDetails")
  private Card cardDetails = null;

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
   * Get cardDetails
   * 
   * @return cardDetails
   **/
  @Valid
  public Card getCardDetails() {
    return cardDetails;
  }

  public void setCardDetails(Card cardDetails) {
    this.cardDetails = cardDetails;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardDetailResponse cardDetailResponse = (CardDetailResponse) o;
    return Objects.equals(this.result, cardDetailResponse.result) && Objects.equals(this.cardDetails, cardDetailResponse.cardDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, cardDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardDetailResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    cardDetails: ").append(toIndentedString(cardDetails)).append("\n");
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

