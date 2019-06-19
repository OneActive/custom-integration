
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
    "localCurrencyCode",
    "localCurrencyBalanceAmount"
})
public class TotalCurrentBalance {

    @JsonProperty("localCurrencyCode")
    private String localCurrencyCode;
    @JsonProperty("localCurrencyBalanceAmount")
    private Integer localCurrencyBalanceAmount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("localCurrencyCode")
    public String getLocalCurrencyCode() {
        return localCurrencyCode;
    }

    @JsonProperty("localCurrencyCode")
    public void setLocalCurrencyCode(String localCurrencyCode) {
        this.localCurrencyCode = localCurrencyCode;
    }

    @JsonProperty("localCurrencyBalanceAmount")
    public Integer getLocalCurrencyBalanceAmount() {
        return localCurrencyBalanceAmount;
    }

    @JsonProperty("localCurrencyBalanceAmount")
    public void setLocalCurrencyBalanceAmount(Integer localCurrencyBalanceAmount) {
        this.localCurrencyBalanceAmount = localCurrencyBalanceAmount;
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
