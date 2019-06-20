
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
    "accountGroup",
    "brokerageAccountsSummary",
    "checkingAccountsSummary",
    "totalCurrentBalance",
    "totalAvailableBalance",
    "creditCardAccountsSummary",
    "totalOutstandingBalance",
    "savingsAccountsSummary"
})
public class AccountGroupSummary {

    @JsonProperty("accountGroup")
    private String accountGroup;
    @JsonProperty("brokerageAccountsSummary")
    private List<BrokerageAccountsSummary> brokerageAccountsSummary = null;
    @JsonProperty("checkingAccountsSummary")
    private List<CheckingAccountsSummary> checkingAccountsSummary = null;
    @JsonProperty("totalCurrentBalance")
    private TotalCurrentBalance totalCurrentBalance;
    @JsonProperty("totalAvailableBalance")
    private TotalAvailableBalance totalAvailableBalance;
    @JsonProperty("creditCardAccountsSummary")
    private List<CreditCardAccountsSummary> creditCardAccountsSummary = null;
    @JsonProperty("totalOutstandingBalance")
    private TotalOutstandingBalance totalOutstandingBalance;
    @JsonProperty("savingsAccountsSummary")
    private List<SavingsAccountsSummary> savingsAccountsSummary = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("accountGroup")
    public String getAccountGroup() {
        return accountGroup;
    }

    @JsonProperty("accountGroup")
    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }

    @JsonProperty("brokerageAccountsSummary")
    public List<BrokerageAccountsSummary> getBrokerageAccountsSummary() {
        return brokerageAccountsSummary;
    }

    @JsonProperty("brokerageAccountsSummary")
    public void setBrokerageAccountsSummary(List<BrokerageAccountsSummary> brokerageAccountsSummary) {
        this.brokerageAccountsSummary = brokerageAccountsSummary;
    }

    @JsonProperty("checkingAccountsSummary")
    public List<CheckingAccountsSummary> getCheckingAccountsSummary() {
        return checkingAccountsSummary;
    }

    @JsonProperty("checkingAccountsSummary")
    public void setCheckingAccountsSummary(List<CheckingAccountsSummary> checkingAccountsSummary) {
        this.checkingAccountsSummary = checkingAccountsSummary;
    }

    @JsonProperty("totalCurrentBalance")
    public TotalCurrentBalance getTotalCurrentBalance() {
        return totalCurrentBalance;
    }

    @JsonProperty("totalCurrentBalance")
    public void setTotalCurrentBalance(TotalCurrentBalance totalCurrentBalance) {
        this.totalCurrentBalance = totalCurrentBalance;
    }

    @JsonProperty("totalAvailableBalance")
    public TotalAvailableBalance getTotalAvailableBalance() {
        return totalAvailableBalance;
    }

    @JsonProperty("totalAvailableBalance")
    public void setTotalAvailableBalance(TotalAvailableBalance totalAvailableBalance) {
        this.totalAvailableBalance = totalAvailableBalance;
    }

    @JsonProperty("creditCardAccountsSummary")
    public List<CreditCardAccountsSummary> getCreditCardAccountsSummary() {
        return creditCardAccountsSummary;
    }

    @JsonProperty("creditCardAccountsSummary")
    public void setCreditCardAccountsSummary(List<CreditCardAccountsSummary> creditCardAccountsSummary) {
        this.creditCardAccountsSummary = creditCardAccountsSummary;
    }

    @JsonProperty("totalOutstandingBalance")
    public TotalOutstandingBalance getTotalOutstandingBalance() {
        return totalOutstandingBalance;
    }

    @JsonProperty("totalOutstandingBalance")
    public void setTotalOutstandingBalance(TotalOutstandingBalance totalOutstandingBalance) {
        this.totalOutstandingBalance = totalOutstandingBalance;
    }

    @JsonProperty("savingsAccountsSummary")
    public List<SavingsAccountsSummary> getSavingsAccountsSummary() {
        return savingsAccountsSummary;
    }

    @JsonProperty("savingsAccountsSummary")
    public void setSavingsAccountsSummary(List<SavingsAccountsSummary> savingsAccountsSummary) {
        this.savingsAccountsSummary = savingsAccountsSummary;
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
