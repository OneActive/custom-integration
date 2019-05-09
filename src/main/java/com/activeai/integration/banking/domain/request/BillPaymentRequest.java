package com.activeai.integration.banking.domain.request;

import com.activeai.integration.banking.model.Biller;
import com.activeai.integration.banking.model.User;

public class BillPaymentRequest extends User {

    /**
     * source account to debit from
     */
    private String sourceAccountId;

    /** amount to transfer. */
    private String amount;

    /** transaction remarks if any. */
    private String remarks;

    private Biller billerDetails;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Biller getBillerDetails() {
        return billerDetails;
    }

    public void setBillerDetails(Biller billerDetails) {
        this.billerDetails = billerDetails;
    }
}
