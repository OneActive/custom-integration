package com.activeai.integration.banking.domain.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserIdentityData {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override public String toString() {
        return new ToStringBuilder(this).append("name", name).append("value", value).toString();
    }
}
