package com.activeai.integration.banking.utils;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.*;
import com.activeai.integration.banking.model.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component("propertyUtil")
public class PropertyUtil {

  public static final String API_DOUBLE_PROPERTY_PATTERN = "{0}_{1}";
  @Autowired
  private Environment env;

  /**
   * Remove the return statement and form your URL accordingly with Parameter to Hit in Services
   * @param propertyValue
   * @param customerId This string may be use for further computation in overriding classes
   * @param accountId
   * @return
   */
  public String getAPIUrl(String propertyValue, String customerId, String accountId) {
     return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(API_DOUBLE_PROPERTY_PATTERN, accountId, propertyValue))) ?
        env.getProperty(MessageFormat.format(API_DOUBLE_PROPERTY_PATTERN, accountId, propertyValue)) :
        env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param customerId
   * @param accountId This string may be use for further computation in overriding classes
   * @return
   */
  public String getAccountAPIUrl(String propertyValue, String customerId, String accountId) {
    // to do load testing
    if (customerId.contains("testuser")) {
      return env.getProperty(PropertyConstants.CASA_TWO_ACCOUNT_API_END_POINT);
    }
    return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(API_DOUBLE_PROPERTY_PATTERN, customerId, propertyValue))) ?
        env.getProperty(MessageFormat.format(API_DOUBLE_PROPERTY_PATTERN, customerId, propertyValue)) :
        env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param payeesRequest This Object may be use for further computation in overriding classes
   * @return
   */
  public String getAPIUrlForPayees(String propertyValue, PayeesRequest payeesRequest) {
    return env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param fundTransferRequest This Object may be use for further computation in overriding classes
   * @return
   */
  public String getAPIUrlForFundTransfer(String propertyValue, FundTransferRequest fundTransferRequest) {
    return env.getProperty(propertyValue);
  }

  public Result frameErrorResponse(String errorDescription, String errorCode, Integer statusCode){
    Result result = new Result();
    result.setStatus(statusCode);
    result.setMessage(errorDescription);
    result.setMessageCode(errorCode);
    return result;
  }

  /**
   *
   * @param propertyValue
   * @param blockCardRequest This Object may be use for further computation in overriding classes
   * @return
   */
  public String getAPIUrlForBlockCard(String propertyValue, BlockCardRequest blockCardRequest) {
    return env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param activationCardRequest This Object may be use for further computation in overriding classes
   * @return
   */
  public String getAPIUrlForActivateCard(String propertyValue, ActivationCardRequest activationCardRequest) {
    return env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param resetPinConfirmRequest This Object may be use for further computation in overriding classes
   * @return
   */
  public String getAPIUrlForResetPin(String propertyValue, ResetPinConfirmRequest resetPinConfirmRequest) {
    return env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param replaceCardConfirmRequest This Object may be use for further computation in overriding classes
   * @return
   */
  public String getAPIUrlForReplaceCard(String propertyValue, ReplaceCardConfirmRequest replaceCardConfirmRequest) {
    return env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param customerId
   * @param accountId This String may be use for further computation in overriding classes
   * @return
   */
  public String getLoginAPIUrl(String propertyValue, String customerId, String accountId) {
    return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(API_DOUBLE_PROPERTY_PATTERN, customerId, propertyValue))) ?
        env.getProperty(MessageFormat.format(API_DOUBLE_PROPERTY_PATTERN, customerId, propertyValue)) :
        null;
  }

  /**
   *
   * @param propertyValue
   * @param customerId This String may be use for further computation in overriding classes
   * @param cardNumber
   * @return
   */
  public String getAPIUrlForConvertEMI(String propertyValue, String customerId, String cardNumber) {
    return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(API_DOUBLE_PROPERTY_PATTERN, cardNumber, propertyValue))) ?
        env.getProperty(MessageFormat.format(API_DOUBLE_PROPERTY_PATTERN, cardNumber, propertyValue)) :
        env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param convertEMIConfirmRequest This Object may be use for further computation in overriding classes
   * @return
   */
  public String getAPIUrlForConvertEMIConfirm(String propertyValue, ConvertEMIConfirmRequest convertEMIConfirmRequest) {
    return env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param cardPaymentRequest This Object may be use for further computation in overriding classes
   * @return
   */
  public String getAPIUrlForCardPaymentConfirm(String propertyValue, CardPaymentRequest cardPaymentRequest) {
    return env.getProperty(propertyValue);
  }

  /**
   *
   * @param propertyValue
   * @param locatorRequest This Object may be use for further computation in overriding classes
   * @return
   */
  public String getAPIUrlForAtmLocator(String propertyValue, AtmLocatorRequest locatorRequest) {
    return env.getProperty(propertyValue);
  }
}
