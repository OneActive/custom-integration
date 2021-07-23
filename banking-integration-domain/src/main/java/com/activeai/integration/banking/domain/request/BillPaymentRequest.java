package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.domain.model.Biller;
import com.activeai.integration.banking.domain.model.User;

public class BillPaymentRequest extends User {


    private Biller billerDetails;

    private String sourceAccountId;

    private String amount;

    public Biller getBillerDetails() {
        return billerDetails;
    }

    public void setBillerDetails(Biller billerDetails) {
        this.billerDetails = billerDetails;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
