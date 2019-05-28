package com.activeai.integration.banking.utils;

import com.activeai.integration.banking.domain.request.ActivationCardRequest;
import com.activeai.integration.banking.domain.request.BlockCardRequest;
import com.activeai.integration.banking.domain.request.FundTransferRequest;
import com.activeai.integration.banking.domain.request.PayeesRequest;
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

  public String getAPIUrlForPayees(String propertyValue, PayeesRequest payeesRequest) {
    return env.getProperty(propertyValue);
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
}
