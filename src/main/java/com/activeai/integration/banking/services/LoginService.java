package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.UserLoginRequest;
import com.activeai.integration.banking.domain.response.LoginResponse;
import com.activeai.integration.banking.mapper.request.LoginRequestMapper;
import com.activeai.integration.banking.mapper.response.LoginResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service("loginService")
public class LoginService {

  @Autowired private LoginResponseMapper loginResponseMapper;
  @Autowired private LoginRequestMapper loginRequestMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private PropertyUtil propertyUtil;

  /**
   * Customise this based on your Use case
   * In first phase if you have customer profile separate API call inside this and map response accordingly here
   * @param userLoginRequest
   * @return
   */
  public ResponseEntity<LoginResponse> getLoginResponseEntity(UserLoginRequest userLoginRequest) {
    ResponseEntity<LoginResponse> loginResponseEntity = null;
    try {
      Map<String, String> auth = new HashMap<>();
      LoginResponse loginResponse = new LoginResponse();
      auth.put("stuart", "stuart@123");
      if (userLoginRequest.getPassword().equalsIgnoreCase(auth.get(userLoginRequest.getUserID()))) {
        try {
          HttpResponse<String> response =
              Unirest.post(propertyUtil.getAPIUrl(PropertyConstants.CUSTOMER_LOGIN_API_END_POINT,userLoginRequest.getUserID(),null))
                  .header("Content-Type", "application/json")
                  .body(loginRequestMapper.getLoginRequestBody(userLoginRequest)).asString();
          ApplicationLogger
              .logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
          if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
            ApplicationLogger.logInfo("Login Response Body Before Transformation :" + response.getBody());
            String cardsResponseString = loginResponseMapper.getManipulatedLoginResponse(response.getBody());

            //Here you can call customer profile API and merge the response to login

            ApplicationLogger.logInfo("Login Response Body After Transformation :" + response.getBody());
            loginResponse = objectMapper.readValue(cardsResponseString, LoginResponse.class);
          }
          return ResponseEntity.ok(loginResponse);
        } catch (UnirestException e) {
          ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
        } catch (IOException e) {
          ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
          ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
        }
        return new ResponseEntity<>(objectMapper.readValue(
            "{  \"result\" : {    \"messageCode\" : \"EXPECTATION_FAILED\",    \"message\" : \"Application is Down!, Please Contact administrator\",    \"status\" : 417  }}",
            LoginResponse.class), HttpStatus.OK);
      } else {
        loginResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.WRONG_USERNAME_OR_PASSWORD,"UNAUTHORISED",401));
        return ResponseEntity.ok(loginResponse);
      }
    } catch (IOException e) {
      ApplicationLogger.logInfo("Couldn't serialize response for content type application/json", e);
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
