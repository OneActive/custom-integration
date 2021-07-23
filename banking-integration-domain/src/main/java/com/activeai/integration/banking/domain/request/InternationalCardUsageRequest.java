package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.domain.model.Card;
import com.activeai.integration.banking.domain.model.User;

public class InternationalCardUsageRequest extends User {
  private Card cardDetails;

  /**
   *Start Date of International Usage Enable
   */
  private String fromDate;

  /**
   *End Date of International Usage Enable
   */
  private String toDate;

  /**
   * Getter and Setter Methods
   */
  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }

  public Card getCardDetails() {
    return cardDetails;
  }

  public void setCardDetails(Card cardDetails) {
    this.cardDetails = cardDetails;
  }
}
