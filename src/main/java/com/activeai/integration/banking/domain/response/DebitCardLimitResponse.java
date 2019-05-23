package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.model.Card;
import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DebitCardLimitResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("card")
  private List<Card> card = null;

  public Result getResult() {
    return result;
  }

  public void setResult(Result result) {
    this.result = result;
  }

  public List<Card> getCard() {
    return card;
  }

  public void setCard(List<Card> card) {
    this.card = card;
  }

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("DebitCardLimitResponse{");
    sb.append("result=").append(result);
    sb.append(", card=").append(card);
    sb.append('}');
    return sb.toString();
  }
}
