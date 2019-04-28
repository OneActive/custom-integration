package com.activeai.integration.banking.services;

import com.activeai.integration.banking.domain.request.UserLoginRequest;
import com.activeai.integration.banking.mapper.AccountsResponseMapper;
import com.activeai.integration.banking.model.LoginResponse;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.FetchAPIUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service("loginService")
public class LoginService {

  @Autowired private AccountsResponseMapper accountsResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private FetchAPIUrl fetchAPIUrl;

  /**
   * Customise this based on your Use case
   * @param userLoginRequest
   * @return
   */
  public ResponseEntity<LoginResponse> getLoginResponseEntity(UserLoginRequest userLoginRequest) {
    ResponseEntity<LoginResponse> loginResponseEntity = null;
    try {
      Map<String,String> auth = new HashMap<>();
      auth.put("stuart","stuart@123");
      auth.put("michel","mic@123");
      if(userLoginRequest.getPassword().equalsIgnoreCase(auth.get(userLoginRequest.getUserID()))){
        return new ResponseEntity<>(objectMapper
            .readValue("{  \"result\" : {    \"messageCode\" : \"200\",    \"message\" : \"Login Successful\",    \"status\" : 0  }}",
                LoginResponse.class), HttpStatus.OK);
      }else{
        return new ResponseEntity<>(objectMapper
            .readValue("{  \"result\" : {    \"messageCode\" : \"401\",    \"message\" : \"UserId or password is wrong!\",    \"status\" : 1  }}",
                LoginResponse.class), HttpStatus.UNAUTHORIZED);
      }
    } catch (IOException e) {
      ApplicationLogger.logInfo("Couldn't serialize response for content type application/json", e);
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
