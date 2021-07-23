package com.activeai.integration.banking.api.constants;

public final class MessageConstants {

  private MessageConstants() {
    //To hide implementation details
  }

  public static final String INTERNAL_SERVER_ERROR = "Something went wrong!. Please contact your administrator";
  public static final String WRONG_USERNAME_OR_SECURITY_KEY = "User name or password is inValid!,Please Try again";
  public static final String EXCEPTION_MESSAGE = "API failure";
  public static final String API_FAILURE_MESSAGE = "Something went wrong while calling API";
  public static final String RESPONSE_BEFORE_TRANSFORMATION = "Response Before Transformation : ";
  public static final String RESPONSE_AFTER_TRANSFORMATION = "Response After Transformation : ";
  public static final String AND_RESPONSE_STATUS_TEXT = " and response status text :";
  public static final String API_RESPONSE_STATUS = "API Response status: ";

}
