package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.UserLoginRequest;
import com.activeai.integration.banking.domain.response.LoginResponse;
import com.activeai.integration.banking.mapper.request.LoginRequestMapper;
import com.activeai.integration.banking.mapper.response.LoginResponseMapper;
import com.activeai.integration.banking.model.Result;
import com.activeai.integration.banking.model.User;
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
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service("loginService")
public class LoginService {

  @Autowired private LoginResponseMapper loginResponseMapper;
  @Autowired private LoginRequestMapper loginRequestMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private PropertyUtil propertyUtil;
  public static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  /**
   * Customise this based on your Use case
   * In first phase if you have customer profile separate API call inside this and map response accordingly here
   * @param userLoginRequest
   * @return
   */
  public ResponseEntity<LoginResponse> getLoginResponseEntity(UserLoginRequest userLoginRequest) {
    try {
      LoginResponse loginResponse = new LoginResponse();
      ApplicationLogger.logInfo("Login For User ID:-> " + userLoginRequest.getUserID());
      if (isAuthorisedUser(userLoginRequest)) {
        try {
          String url = propertyUtil.getLoginAPIUrl(PropertyConstants.CUSTOMER_LOGIN_API_END_POINT,userLoginRequest.getUserID(),null);
          if (StringUtils.isEmpty(url)) {
            loginResponse = customLoginResponseMapping(userLoginRequest.getUserID());
            ApplicationLogger.logInfo("Login Response " + loginResponse);
            return ResponseEntity.ok(loginResponse);
          }
          HttpResponse<String> apiResponse =
              Unirest.post(url)
                  .header("Content-Type", "application/json")
                  .body(loginRequestMapper.getLoginRequestBody(userLoginRequest)).asString();
          ApplicationLogger
              .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
          if (Objects.nonNull(apiResponse) && StringUtils.isNotEmpty(apiResponse.getBody())) {
            ApplicationLogger.logInfo("Login Response Body Before Transformation :" + apiResponse.getBody());
            loginResponse = loginResponseMapper.getManipulatedLoginResponse(apiResponse.getBody());

            //Here you can call customer profile API and merge the response to login

            ApplicationLogger.logInfo("Login Response Body After Transformation :" + apiResponse.getBody());
          }
          return ResponseEntity.ok(loginResponse);
        } catch (UnirestException e) {
          ApplicationLogger.logError(MessageFormat
              .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
        } catch (IOException e) {
          ApplicationLogger.logError(MessageFormat
              .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
                  ExceptionUtils.getStackTrace(e)));
        } catch (Exception e) {
          ApplicationLogger.logError(MessageFormat
              .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
        }
        return new ResponseEntity<>(objectMapper.readValue(
            "{  \"result\" : {    \"messageCode\" : \"EXPECTATION_FAILED\",    \"message\" : \"Application is Down!, Please Contact administrator\",    \"status\" : 417  }}",
            LoginResponse.class), HttpStatus.OK);
      } else {
        loginResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.WRONG_USERNAME_OR_PASSWORD,"UNAUTHORISED",401));
        return ResponseEntity.ok(loginResponse);
      }
    } catch (IOException e) {
      ApplicationLogger.logInfo(MessageFormat.format(
          ERROR_MESSAGE_FORMAT,MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE,this.getClass().getName(),ExceptionUtils.getStackTrace(e)));
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * Verify User is Authorised or not
   *
   * @param request
   * @return
   */
  private boolean isAuthorisedUser(UserLoginRequest request) {
    Map<String, String> auth = new HashMap<>();
    getAuthenticatedUserDetails(auth);
    return request.getPassword().equals(auth.get(request.getUserID()));
  }

  /**
   * Populate authenticated user details stubs
   *
   * @param auth
   */
  private void getAuthenticatedUserDetails(Map<String, String> auth) {
    auth.put("testuser1", "password");
    auth.put("testuser2", "password");
    auth.put("testuser3", "password");
    /**
     * creating map of auth for testuser4 to testuser54
     *
     * @n is number of users need to create
     */
    int n = 20;
    for (int i = 4; i <= n+4; i++) {
      String userId = "testuser" + i;
      auth.put(userId, "password");
    }
    auth.put("stuart", "stuart@123");
    auth.put("james", "james@123");
    auth.put("thanos", "thanos@123");
    auth.put("henry", "henry@123");
    auth.put("lucas", "lucas@123");
    auth.put("jackson", "jackson@123");
    auth.put("ethan", "ethan@123");
  }

  /**
   * Creating login response for more users  testuser4 to testuser54
   * Kindly remove this method on real time integration
   *
   * @param userId
   * @return LoginResponse
   */
  private LoginResponse customLoginResponseMapping(String userId) {
    ApplicationLogger.logInfo("building login response for testing : " + userId);
    LoginResponse loginResponse = new LoginResponse();
    Result result = new Result();
    result.setMessage("Login successful");
    result.setMessageCode("OK");
    result.setStatus(200);
    loginResponse.setResult(result);
    User user = new User();
    user.setAddress(userId);
    user.setCustomerId(userId);
    user.setCustomerName(userId+"name");
    user.setMobileNumber("+65 9832469" + userId.substring(8));
    user.setEmailId(userId + "@gmail.com");
    loginResponse.setUser(user);
    return loginResponse;
  }
}
