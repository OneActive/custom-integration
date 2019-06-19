
package com.activeai.integration.banking.apiservice.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "productName",
    "displayAccountNumber",
    "accountId",
    "currencyCode",
    "accountClassification",
    "accountStatus",
    "currentBalance",
    "availableBalance"
})
public class CheckingAccountsSummary {

    @JsonProperty("productName")
    private String productName;
    @JsonProperty("displayAccountNumber")
    private String displayAccountNumber;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("currencyCode")
    private String currencyCode;
    @JsonProperty("accountClassification")
    private String accountClassification;
    @JsonProperty("accountStatus")
    private String accountStatus;
    @JsonProperty("currentBalance")
    private Integer currentBalance;
    @JsonProperty("availableBalance")
    private Integer availableBalance;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("productName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("displayAccountNumber")
    public String getDisplayAccountNumber() {
        return displayAccountNumber;
    }

    @JsonProperty("displayAccountNumber")
    public void setDisplayAccountNumber(String displayAccountNumber) {
        this.displayAccountNumber = displayAccountNumber;
    }

    @JsonProperty("accountId")
    public String getAccountId() {
        return accountId;
    }

    @JsonProperty("accountId")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("currencyCode")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonProperty("currencyCode")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("accountClassification")
    public String getAccountClassification() {
        return accountClassification;
    }

    @JsonProperty("accountClassification")
    public void setAccountClassification(String accountClassification) {
        this.accountClassification = accountClassification;
    }

    @JsonProperty("accountStatus")
    public String getAccountStatus() {
        return accountStatus;
    }

    @JsonProperty("accountStatus")
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @JsonProperty("currentBalance")
    public Integer getCurrentBalance() {
        return currentBalance;
    }

    @JsonProperty("currentBalance")
    public void setCurrentBalance(Integer currentBalance) {
        this.currentBalance = currentBalance;
    }

    @JsonProperty("availableBalance")
    public Integer getAvailableBalance() {
        return availableBalance;
    }

    @JsonProperty("availableBalance")
    public void setAvailableBalance(Integer availableBalance) {
        this.availableBalance = availableBalance;
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
