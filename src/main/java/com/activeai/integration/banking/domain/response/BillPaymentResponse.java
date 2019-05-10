package com.activeai.integration.banking.domain.response;

import com.activeai.integration.banking.constants.StatusEnum;
import com.activeai.integration.banking.model.Result;
import com.fasterxml.jackson.annotation.JsonProperty;

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
        final StringBuffer sb = new StringBuffer("BillPaymentResponse{");
        sb.append("billerName='").append(billerName).append('\'');
        sb.append(", billDeskTransactionId='").append(billDeskTransactionId).append('\'');
        sb.append(", pooledPayeeAccNumber='").append(pooledPayeeAccNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
