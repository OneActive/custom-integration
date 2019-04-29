package com.activeai.integration.banking.domain.response;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.activeai.integration.banking.model.CardTransaction;
import com.activeai.integration.banking.model.Result;
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
  private List<CardTransaction> cardTransactions = null;

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

