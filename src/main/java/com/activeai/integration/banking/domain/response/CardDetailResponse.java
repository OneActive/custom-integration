package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.Card;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * CardDetailResponse
 */
@Validated
public class CardDetailResponse extends Response {

  @JsonProperty("cardDetails")
  private Card cardDetails = null;

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
  public String toString() {
    return new ToStringBuilder(this).append("cardDetails", cardDetails).toString();
  }
}

