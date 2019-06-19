
package com.activeai.integration.banking.apiservice.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "accountId",
    "accountRegistrationType",
    "accountTradingCapableFlag",
    "asOfDateTime",
    "brokerageAccountTransactionTypes",
    "cashAccountDetails",
    "displayAccountNumber",
    "marketValueChangeAmount",
    "marketValueChangePercentage",
    "totalAccountValueAmount"
})
public class BrokerageAccountsSummary {

    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("accountRegistrationType")
    private String accountRegistrationType;
    @JsonProperty("accountTradingCapableFlag")
    private Boolean accountTradingCapableFlag;
    @JsonProperty("asOfDateTime")
    private String asOfDateTime;
    @JsonProperty("brokerageAccountTransactionTypes")
    private List<String> brokerageAccountTransactionTypes = null;
    @JsonProperty("cashAccountDetails")
    private CashAccountDetails cashAccountDetails;
    @JsonProperty("displayAccountNumber")
    private String displayAccountNumber;
    @JsonProperty("marketValueChangeAmount")
    private Double marketValueChangeAmount;
    @JsonProperty("marketValueChangePercentage")
    private Double marketValueChangePercentage;
    @JsonProperty("totalAccountValueAmount")
    private Double totalAccountValueAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("accountId")
    public String getAccountId() {
        return accountId;
    }

    @JsonProperty("accountId")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("accountRegistrationType")
    public String getAccountRegistrationType() {
        return accountRegistrationType;
    }

    @JsonProperty("accountRegistrationType")
    public void setAccountRegistrationType(String accountRegistrationType) {
        this.accountRegistrationType = accountRegistrationType;
    }

    @JsonProperty("accountTradingCapableFlag")
    public Boolean getAccountTradingCapableFlag() {
        return accountTradingCapableFlag;
    }

    @JsonProperty("accountTradingCapableFlag")
    public void setAccountTradingCapableFlag(Boolean accountTradingCapableFlag) {
        this.accountTradingCapableFlag = accountTradingCapableFlag;
    }

    @JsonProperty("asOfDateTime")
    public String getAsOfDateTime() {
        return asOfDateTime;
    }

    @JsonProperty("asOfDateTime")
    public void setAsOfDateTime(String asOfDateTime) {
        this.asOfDateTime = asOfDateTime;
    }

    @JsonProperty("brokerageAccountTransactionTypes")
    public List<String> getBrokerageAccountTransactionTypes() {
        return brokerageAccountTransactionTypes;
    }

    @JsonProperty("brokerageAccountTransactionTypes")
    public void setBrokerageAccountTransactionTypes(List<String> brokerageAccountTransactionTypes) {
        this.brokerageAccountTransactionTypes = brokerageAccountTransactionTypes;
    }

    @JsonProperty("cashAccountDetails")
    public CashAccountDetails getCashAccountDetails() {
        return cashAccountDetails;
    }

    @JsonProperty("cashAccountDetails")
    public void setCashAccountDetails(CashAccountDetails cashAccountDetails) {
        this.cashAccountDetails = cashAccountDetails;
    }

    @JsonProperty("displayAccountNumber")
    public String getDisplayAccountNumber() {
        return displayAccountNumber;
    }

    @JsonProperty("displayAccountNumber")
    public void setDisplayAccountNumber(String displayAccountNumber) {
        this.displayAccountNumber = displayAccountNumber;
    }

    @JsonProperty("marketValueChangeAmount")
    public Double getMarketValueChangeAmount() {
        return marketValueChangeAmount;
    }

    @JsonProperty("marketValueChangeAmount")
    public void setMarketValueChangeAmount(Double marketValueChangeAmount) {
        this.marketValueChangeAmount = marketValueChangeAmount;
    }

    @JsonProperty("marketValueChangePercentage")
    public Double getMarketValueChangePercentage() {
        return marketValueChangePercentage;
    }

    @JsonProperty("marketValueChangePercentage")
    public void setMarketValueChangePercentage(Double marketValueChangePercentage) {
        this.marketValueChangePercentage = marketValueChangePercentage;
    }

    @JsonProperty("totalAccountValueAmount")
    public Double getTotalAccountValueAmount() {
        return totalAccountValueAmount;
    }

    @JsonProperty("totalAccountValueAmount")
    public void setTotalAccountValueAmount(Double totalAccountValueAmount) {
        this.totalAccountValueAmount = totalAccountValueAmount;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
