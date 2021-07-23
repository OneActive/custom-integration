package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.domain.model.Card;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class CreditCardLimitResponse extends Response {

    @JsonProperty("card")
    private List<Card> card = null;

    public List<Card> getCard() {
        return card;
    }

    public void setCard(List<Card> card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("card", card).toString();
    }
}
