package com.activeai.integration.banking.utils;

import com.activeai.integration.banking.domain.request.*;
import com.activeai.integration.banking.model.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component("propertyUtil")
public class PropertyUtil {

  public static final String APIDoublePropertyPattern = "{0}_{1}";
  @Autowired
  private Environment env;

  /**
   * Remove the return statement and form your URL accordingly with Parameter to Hit in Services
   * @param propertyValue
   * @param customerId
   * @param accountId
   * @return
   */
  public String getAPIUrl(String propertyValue, String customerId, String accountId) {
     return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(APIDoublePropertyPattern, accountId, propertyValue))) ?
        env.getProperty(MessageFormat.format(APIDoublePropertyPattern, accountId, propertyValue)) :
        env.getProperty(propertyValue);
  }

  public String getAccountAPIUrl(String propertyValue, String customerId, String accountId) {
    return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(APIDoublePropertyPattern, customerId, propertyValue))) ?
        env.getProperty(MessageFormat.format(APIDoublePropertyPattern, customerId, propertyValue)) :
        env.getProperty(propertyValue);
  }

  public String getAPIUrlForPayees(String propertyValue, PayeesRequest payeesRequest) {
    return StringUtils
        .isNotEmpty(env.getProperty(MessageFormat.format(APIDoublePropertyPattern, payeesRequest.getCustomerId(), propertyValue))) ?
        env.getProperty(MessageFormat.format(APIDoublePropertyPattern, payeesRequest.getCustomerId(), propertyValue)) :
        env.getProperty(propertyValue);
  }

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

  public String getAPIUrlForBlockCard(String propertyValue, BlockCardRequest blockCardRequest) {
    return env.getProperty(propertyValue);
  }

  public String getAPIUrlForActivateCard(String propertyValue, ActivationCardRequest activationCardRequest) {
    return env.getProperty(propertyValue);
  }

  public String getAPIUrlForResetPin(String propertyValue, ResetPinConfirmRequest resetPinConfirmRequest) {
    return env.getProperty(propertyValue);
  }

  public String getAPIUrlForReplaceCard(String propertyValue, ReplaceCardConfirmRequest replaceCardConfirmRequest) {
    return env.getProperty(propertyValue);
  }

  public String getLoginAPIUrl(String propertyValue, String customerId, String accountId) {
    return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(APIDoublePropertyPattern, customerId, propertyValue))) ?
        env.getProperty(MessageFormat.format(APIDoublePropertyPattern, customerId, propertyValue)) :
        env.getProperty(propertyValue);
  }

  public String getAPIUrlForConvertEMI(String propertyValue, String customerId, String cardNumber) {
    return StringUtils.isNotEmpty(env.getProperty(MessageFormat.format(APIDoublePropertyPattern, cardNumber, propertyValue))) ?
        env.getProperty(MessageFormat.format(APIDoublePropertyPattern, cardNumber, propertyValue)) :
        env.getProperty(propertyValue);
  }

  public String getAPIUrlForConvertEMIConfirm(String propertyValue, ConvertEMIConfirmRequest convertEMIConfirmRequest) {
    return env.getProperty(propertyValue);
  }

  public String getAPIUrlForCardPaymentConfirm(String propertyValue, CardPaymentRequest cardPaymentRequest) {
    return env.getProperty(propertyValue);
  }

  public String getAPIUrlForAtmLocator(String propertyValue, AtmLocatorRequest locatorRequest) {
    return env.getProperty(propertyValue);
  }
}
