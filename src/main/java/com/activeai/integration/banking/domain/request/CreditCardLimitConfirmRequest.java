package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.model.Card;
import com.activeai.integration.banking.model.User;

public class CreditCardLimitConfirmRequest extends User {

    private Card cardDetails;

    public Card getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(Card cardDetails) {
        this.cardDetails = cardDetails;
    }
}
