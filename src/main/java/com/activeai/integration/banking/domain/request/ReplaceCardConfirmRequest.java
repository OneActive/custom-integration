package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.constants.ReplaceTypeEnum;
import com.activeai.integration.banking.model.Card;
import com.activeai.integration.banking.model.User;

public class ReplaceCardConfirmRequest extends User {

  private Card cardDetails;

  private ReplaceTypeEnum replaceType;

  public Card getCardDetails() {
    return cardDetails;
  }

  public void setCardDetails(Card cardDetails) {
    this.cardDetails = cardDetails;
  }

  public ReplaceTypeEnum getReplaceType() {
    return replaceType;
  }

  public void setReplaceType(ReplaceTypeEnum replaceType) {
    this.replaceType = replaceType;
  }
}
