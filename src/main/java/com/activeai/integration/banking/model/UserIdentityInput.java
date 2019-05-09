package com.activeai.integration.banking.model;


import com.activeai.integration.banking.constants.UserIdentityInputDisplayTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class UserIdentityInput.
 */
public class UserIdentityInput {

    /** The name. */
    private String name;

    /** The regex. */
    private String regex;

    /** The message. */
    private String message;

    /** The value. */
    private String value;
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
    @Override
    public String toString() {
        return "UserIdentityInput{" +
                "name='" + name + '\'' +
                ", regex='" + regex + '\'' +
                ", message='" + message + '\'' +
                ", value='" + value + '\'' +
                ", value='" + values + '\'' +
                '}';
    }
}
