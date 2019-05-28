package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.constants.BlockTypeEnum;
import com.activeai.integration.banking.model.Card;
import com.activeai.integration.banking.model.User;

public class BlockCardRequest extends User {

    private BlockTypeEnum blockType;

    private Card cardDetails;

    public BlockTypeEnum getBlockType() {
        return blockType;
    }

    public void setBlockType(BlockTypeEnum blockType) {
        this.blockType = blockType;
    }

    public Card getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(Card cardDetails) {
        this.cardDetails = cardDetails;
    }
}
