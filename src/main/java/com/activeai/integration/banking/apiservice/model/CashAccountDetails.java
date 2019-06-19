
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
    "fundsAvailableToTrade",
    "fundsAvailableToWithdraw"
})
public class CashAccountDetails {

    @JsonProperty("fundsAvailableToTrade")
    private Integer fundsAvailableToTrade;
    @JsonProperty("fundsAvailableToWithdraw")
    private Integer fundsAvailableToWithdraw;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fundsAvailableToTrade")
    public Integer getFundsAvailableToTrade() {
        return fundsAvailableToTrade;
    }

    @JsonProperty("fundsAvailableToTrade")
    public void setFundsAvailableToTrade(Integer fundsAvailableToTrade) {
        this.fundsAvailableToTrade = fundsAvailableToTrade;
    }

    @JsonProperty("fundsAvailableToWithdraw")
    public Integer getFundsAvailableToWithdraw() {
        return fundsAvailableToWithdraw;
    }

    @JsonProperty("fundsAvailableToWithdraw")
    public void setFundsAvailableToWithdraw(Integer fundsAvailableToWithdraw) {
        this.fundsAvailableToWithdraw = fundsAvailableToWithdraw;
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
