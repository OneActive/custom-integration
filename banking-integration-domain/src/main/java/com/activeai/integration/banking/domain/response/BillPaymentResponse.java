package com.activeai.integration.banking.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BillPaymentResponse extends FundTransferResponse {

    @JsonProperty("billerName")
    private String billerName;

    @JsonProperty("billDeskTransactionId")
    private String billDeskTransactionId;

    @JsonProperty("pooledPayeeAccNumber")
    private String pooledPayeeAccNumber;

    public String getBillDeskTransactionId() {
        return billDeskTransactionId;
    }

    public void setBillDeskTransactionId(String billDeskTransactionId) {
        this.billDeskTransactionId = billDeskTransactionId;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getPooledPayeeAccNumber() {
        return pooledPayeeAccNumber;
    }

    public void setPooledPayeeAccNumber(String pooledPayeeAccNumber) {
        this.pooledPayeeAccNumber = pooledPayeeAccNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("billerName", billerName).append("billDeskTransactionId", billDeskTransactionId)
            .append("pooledPayeeAccNumber", pooledPayeeAccNumber).toString();
    }
}
