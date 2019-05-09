package com.activeai.integration.banking.model;

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

    public String toString() {
        StringBuffer sb = new StringBuffer("UserIdentityData{");
        sb.append("name='").append(this.name).append('\'');
        sb.append(", value='").append(this.value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}