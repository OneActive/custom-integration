package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.domain.model.Card;
import com.activeai.integration.banking.domain.model.User;

public class ResetPinConfirmRequest extends User {
  private Card cardDetails;

  private String inputPin;

  public String getInputPin() {
    return inputPin;
  }

  public void setInputPin(String inputPin) {
    this.inputPin = inputPin;
  }

  public Card getCardDetails() {
    return cardDetails;
  }

  public void setCardDetails(Card cardDetails) {
    this.cardDetails = cardDetails;
  }
}
