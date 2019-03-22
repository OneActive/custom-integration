package com.activeai.integration.banking.model;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CardTransactionsResponse
 */
@Validated
public class CardTransactionsResponse {
  @JsonProperty("result")
  private Result result = null;

  @JsonProperty("cardTransactions")
  private CardTransactions cardTransactions = null;

  public CardTransactionsResponse result(Result result) {
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

  public CardTransactionsResponse cardTransactions(CardTransactions cardTransactions) {
    this.cardTransactions = cardTransactions;
    return this;
  }

  /**
   * Get cardTransactions
   * 
   * @return cardTransactions
   **/
  @Valid
  public CardTransactions getCardTransactions() {
    return cardTransactions;
  }

  public void setCardTransactions(CardTransactions cardTransactions) {
    this.cardTransactions = cardTransactions;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardTransactionsResponse cardTransactionsResponse = (CardTransactionsResponse) o;
    return Objects.equals(this.result, cardTransactionsResponse.result)
        && Objects.equals(this.cardTransactions, cardTransactionsResponse.cardTransactions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(result, cardTransactions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardTransactionsResponse {\n");

    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    cardTransactions: ").append(toIndentedString(cardTransactions)).append("\n");
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

