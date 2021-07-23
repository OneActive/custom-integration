package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.request.LoginRequestMapper;
import com.activeai.integration.banking.api.mapper.response.LoginResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.model.Result;
import com.activeai.integration.banking.domain.model.User;
import com.activeai.integration.banking.domain.request.UserLoginRequest;
import com.activeai.integration.banking.domain.response.LoginResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@Service("loginService")
public class LoginService {

  @Autowired private LoginResponseMapper loginResponseMapper;
  @Autowired private LoginRequestMapper loginRequestMapper;
  @Autowired private PropertyUtil propertyUtil;
  public static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";
  public static final String SECURE_KEY="password";

  /**
   * Customise this based on your Use case
   * In first phase if you have customer profile separate API call inside this and map response accordingly here
   * @param userLoginRequest
   * @return
   */
  public ResponseEntity<LoginResponse> getLoginResponseEntity(UserLoginRequest userLoginRequest) {
    LoginResponse loginResponse = new LoginResponse();
    ApplicationLogger.logInfo("Login For User ID:-> " + userLoginRequest.getUserID(), this.getClass());
    boolean isValidUser = false;
    if (StringUtils.isNotEmpty(userLoginRequest.getUserID()) && StringUtils.isEmpty(userLoginRequest.getPassword())) {
      String userId = userLoginRequest.getUserID();
      if (StringUtils.isNumeric(userId)) {
        userId = getUserIdBasedOnCustID(userLoginRequest.getUserID());
      } else if (!userId.toLowerCase().contains("testuser")) {
        userId = null;
      }
      if (StringUtils.isNotEmpty(userId)) {
        isValidUser = true;
        userLoginRequest.setUserID(userId);
      }
    } else if (userLoginRequest.getUserID().equals(userLoginRequest.getPassword())) {
      loginResponse = customLoginResponseMapping(userLoginRequest.getUserID());
      ApplicationLogger.logInfo("Login Response " + loginResponse, this.getClass());
      return ResponseEntity.ok(loginResponse);
    }
    if (isValidUser || isAuthorisedUser(userLoginRequest)) {
      try {
        String url = propertyUtil.getLoginAPIUrl(PropertyConstants.CUSTOMER_LOGIN_API_END_POINT, userLoginRequest.getUserID(), null);
        if (StringUtils.isEmpty(url)) {
          loginResponse = customLoginResponseMapping(userLoginRequest.getUserID());
          ApplicationLogger.logInfo("Login Response " + loginResponse, this.getClass());
          return ResponseEntity.ok(loginResponse);
        }
        HttpResponse<String> apiResponse =
            Unirest.post(url).header("Content-Type", "application/json").body(loginRequestMapper.getLoginRequestBody(userLoginRequest))
                .asString();
        ApplicationLogger
            .logInfo(MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
                this.getClass());
        if (StringUtils.isNotEmpty(apiResponse.getBody())) {
          ApplicationLogger.logInfo("Login Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
          loginResponse = loginResponseMapper.getManipulatedLoginResponse(apiResponse.getBody());

          //Here you can call customer profile API and merge the response to login

          ApplicationLogger.logInfo("Login Response Body After Transformation :" + apiResponse.getBody(), this.getClass());
        }
        return ResponseEntity.ok(loginResponse);
      } catch (UnirestException e) {
        ApplicationLogger.logError(MessageFormat
            .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
                this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
      } catch (Exception e) {
        ApplicationLogger.logError(MessageFormat
            .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
                this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
      }
      return new ResponseEntity<>(BankingIntegrationUtil.readValue(
          "{  \"result\" : {    \"messageCode\" : \"EXPECTATION_FAILED\",    \"message\" : \"Application is Down!, Please Contact administrator\",    \"status\" : 417  }}",
          LoginResponse.class), HttpStatus.OK);
    } else {
      loginResponse.setResult(propertyUtil
          .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.WRONG_USERNAME_OR_SECURITY_KEY, "UNAUTHORISED",
              401));
      return ResponseEntity.ok(loginResponse);
    }
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
    auth.put("testuser1", SECURE_KEY);
    auth.put("testuser2", SECURE_KEY);
    auth.put("testuser3", SECURE_KEY);
    /**
     * creating map of auth for testuser4 to testuser24
     *
     * @n is number of users need to create
     */
    int n = 20;
    for (int i = 4; i <= n+4; i++) {
      String userId = "testuser" + i;
      auth.put(userId, SECURE_KEY);
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
   * Fetch userId based on custId for stub users
   *
   * @param custId
   */
  private String getUserIdBasedOnCustID(String custId) {
    Map<String, String> auth = new HashMap<>();
    auth.put("80975415", "testuser1");
    auth.put("80975416", "testuser2");
    auth.put("80975417", "testuser3");
    auth.put("80975412", "stuart");
    auth.put("80975413", "james");
    auth.put("80975414", "thanos");
    auth.put("80975418", "henry");
    auth.put("80975518", "lucas");
    auth.put("80975420", "jackson");
    auth.put("80975421", "ethan");

    return auth.get(custId);
  }

  /**
   * Creating login response for more users  testuser4 to testuser54
   * Kindly remove this method on real time integration
   *
   * @param userId
   * @return LoginResponse
   */
  private LoginResponse customLoginResponseMapping(String userId) {
    ApplicationLogger.logInfo("building login response for testing : " + userId, this.getClass());
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
    user.setMobileNumber("+65 9832469");
    user.setEmailId(userId + "@gmail.com");
    loginResponse.setUser(user);
    return loginResponse;
  }
}
