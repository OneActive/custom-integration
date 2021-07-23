package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.domain.model.Card;
import com.activeai.integration.banking.domain.model.User;

public class ActivationCardRequest extends User {

    private Card cardDetails;

    public Card getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(Card cardDetails) {
        this.cardDetails = cardDetails;
    }
}
