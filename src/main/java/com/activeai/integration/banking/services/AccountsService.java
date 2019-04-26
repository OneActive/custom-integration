package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.mapper.AccountsResponseMapper;
import com.activeai.integration.banking.model.AccountDetailResponse;
import com.activeai.integration.banking.model.AccountsResponse;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.text.MessageFormat;
import java.util.Objects;

/**
 *This Class methods helps to get account related information using http
 * you can extend this and override methods to handle for other's like soap
 * @return
 */
@Component("accountsService")
public class AccountsService {

  @Autowired
  private AccountsResponseMapper accountsResponseMapper;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private Environment env;

  /**
   * fetches list of accounts
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<AccountsResponse> getAccountsResponseEntity(){
    ResponseEntity<AccountsResponse> responseEntity = null;
    AccountsResponse accountsResponse = null;
    try {
      HttpResponse<String> response = Unirest.get(this.env.getProperty(PropertyConstants.ACCOUNTS_API_URL))
          .header("cache-control", "no-cache")
          .asString();
      ApplicationLogger.logInfo("API Response : "+response);
      if(Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())){
        ApplicationLogger.logInfo("Account Response Body Before Transformation :"+response.getBody());
        String accountsResponseString = accountsResponseMapper.getManipulatedAccountsResponse(response.getBody());
        ApplicationLogger.logInfo("Account Response Body After Transformation :"+response.getBody());
        accountsResponse = objectMapper.readValue(accountsResponseString,AccountsResponse.class);
      }
      return new ResponseEntity<>(accountsResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : "+ExceptionUtils.getStackTrace(e));
    }catch (Exception e){
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :"+ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(accountsResponse, HttpStatus.EXPECTATION_FAILED);
  }

  /**
   * fetches account details for selected account
   * @param customerId
   * @return ResponseEntity of type AccountDetailResponse
   */
  public ResponseEntity<AccountDetailResponse> getAccountDetailsResponseEntity(String customerId){
    ResponseEntity<AccountDetailResponse> responseEntity = null;
    AccountDetailResponse accountsResponse = null;
    try {
      HttpResponse<String> response = Unirest.get(env.getProperty(MessageFormat.format("{0}_{1}",customerId,PropertyConstants.ACCOUNT_DETAILS_API)))
          .header("cache-control", "no-cache")
          .asString();
      ApplicationLogger.logInfo("API Response : "+response);
      if(Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())){
        ApplicationLogger.logInfo("Account Response Body Before Transformation :"+response.getBody());
        String accountDetailResponseString = accountsResponseMapper.getManipulatedAccountDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Account Response Body After Transformation :"+response.getBody());
        accountsResponse = objectMapper.readValue(accountDetailResponseString,AccountDetailResponse.class);
      }
      return new ResponseEntity<>(accountsResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : "+ExceptionUtils.getStackTrace(e));
    }catch (Exception e){
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :"+ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(accountsResponse, HttpStatus.EXPECTATION_FAILED);
  }

  public static void main(String[] args) {
    new AccountsService().getAccountsResponseEntity();
  }
}
