package com.activeai.integration.banking.model;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CardsResponse
 */
@Validated
public class CardsResponse {

  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("cards")
  private Cards cards = null;

  public CardsResponse result(Result result) {
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

  public CardsResponse cards(Cards cards) {
    this.cards = cards;
    return this;
  }

  /**
   * Get cards
   * 
   * @return cards
   **/
  @Valid
  public Cards getCards() {
    return cards;
  }

  public void setCards(Cards cards) {
    this.cards = cards;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardsResponse cardsResponse = (CardsResponse) o;
    return Objects.equals(this.result, cardsResponse.result) && Objects.equals(this.cards, cardsResponse.cards);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, cards);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardsResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    cards: ").append(toIndentedString(cards)).append("\n");
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

