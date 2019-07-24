package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.model.Card;
import com.activeai.integration.banking.model.EMIPlan;
import com.activeai.integration.banking.model.User;

public class ConvertEMIConfirmRequest extends User {

  private Card cardDetails;

  private EMIPlan selectedEMIPlan;

  public Card getCardDetails() {
    return cardDetails;
  }

  public void setCardDetails(Card cardDetails) {
    this.cardDetails = cardDetails;
  }

  public EMIPlan getSelectedEMIPlan() {
    return selectedEMIPlan;
  }

  public void setSelectedEMIPlan(EMIPlan selectedEMIPlan) {
    this.selectedEMIPlan = selectedEMIPlan;
  }
}
