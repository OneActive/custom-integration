package com.activeai.integration.banking.domain.model;


import com.activeai.integration.banking.domain.constants.UserIdentityInputDisplayTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class UserIdentityInput.
 */
public class UserIdentityInput {

    /** The name. */
    @JsonProperty("name")
    private String name;

    /** The regex. */
    @JsonProperty("regex")
    private String regex;

    /** The message. */
    @JsonProperty("message")
    private String message;

    /** The value. */
    @JsonProperty("value")
    private String value;

    /** Error message if regex validation failed */
    @JsonProperty("errorMessage")
    private String errorMessage;

    private UserIdentityInputDisplayTypeEnum userIdentityInputDisplayTypeEnum = UserIdentityInputDisplayTypeEnum.TEXT;
    private List<UserIdentityData> values = new ArrayList<>();

    public UserIdentityInputDisplayTypeEnum getUserIdentityInputDisplayTypeEnum() {
        return userIdentityInputDisplayTypeEnum;
    }

    public void setUserIdentityInputDisplayTypeEnum(UserIdentityInputDisplayTypeEnum userIdentityInputDisplayTypeEnum) {
        this.userIdentityInputDisplayTypeEnum = userIdentityInputDisplayTypeEnum;
    }

    public List<UserIdentityData> getValues() {
        return values;
    }

    public void setValues(List<UserIdentityData> values) {
        this.values = values;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the regex.
     *
     * @return the regex
     */
    public String getRegex() {
        return regex;
    }

    /**
     * Sets the regex.
     *
     * @param regex the new regex
     */
    public void setRegex(String regex) {
        this.regex = regex;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */

    /**
     *
     * @return errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("UserIdentityInput{");
        sb.append("name='").append(name).append('\'');
        sb.append(", regex='").append(regex).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", errorMessage='").append(errorMessage).append('\'');
        sb.append(", userIdentityInputDisplayTypeEnum=").append(userIdentityInputDisplayTypeEnum);
        sb.append(", values=").append(values);
        sb.append('}');
        return sb.toString();
    }
}
