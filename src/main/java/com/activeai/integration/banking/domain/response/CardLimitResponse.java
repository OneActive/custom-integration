package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.CardLimit;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * CardLimitResponse
 */
@Validated
public class CardLimitResponse extends Response {

  @JsonProperty("cardDetail")
  private CardLimit cardDetail = null;

  public CardLimitResponse cardDetail(CardLimit cardDetail) {
    this.cardDetail = cardDetail;
    return this;
  }

  /**
   * Get cardDetail
   *
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
  public String toString() {
    return new ToStringBuilder(this).append("cardDetail", cardDetail).toString();
  }
}

