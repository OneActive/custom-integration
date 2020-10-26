package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.Card;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

/**
 * CardsResponse
 */
@Validated
public class CardsResponse extends Response {

  @JsonProperty("cards")
  private List<Card> cards = null;

  /**
   * Get cards
   *
   * @return cards
   **/
  @Valid
  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("cards", cards).toString();
  }
}

