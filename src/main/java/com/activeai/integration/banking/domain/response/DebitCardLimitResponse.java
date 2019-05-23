package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.Card;
import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DebitCardLimitResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("cards")
  private List<Card> cards = null;

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public List<Card> getCards() {
    return cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("DebitCardLimitResponse{");
    sb.append("result=").append(result);
    sb.append(", cards=").append(cards);
    sb.append('}');
    return sb.toString();
  }
}
