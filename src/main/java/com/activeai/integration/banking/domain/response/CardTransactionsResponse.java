package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.CardTransaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * CardTransactionsResponse
 */
@Validated
public class CardTransactionsResponse extends Response {

  @JsonProperty("cardTransactions")
  private List<CardTransaction> cardTransactions = null;

  /**
   * Get cardTransactions
   *
   * @return cardTransactions
   **/
  @Valid
  public List<CardTransaction> getCardTransactions() {
    return cardTransactions;
  }

  public void setCardTransactions(List<CardTransaction> cardTransactions) {
    this.cardTransactions = cardTransactions;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("cardTransactions", cardTransactions).toString();
  }
}

