package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.model.request.UserLoginRequest;
import com.activeai.integration.banking.model.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(value = "Login Related APIs", description = "Shows API Documentation Regards Logins APIs")
@RestController
public class LoginApiController {

  private static final Logger logger = LoggerFactory.getLogger(TransfersApiController.class);
  @Autowired
  private ObjectMapper objectMapper;

  @ApiOperation("Return Authorisation of user")
  @RequestMapping(value = "/login", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<LoginResponse> login(@RequestBody final UserLoginRequest userLoginRequest) {
    ResponseEntity<LoginResponse> response = null;
    try {
      Map<String,String> auth = new HashMap<>();
      auth.put("stuart","stuart@123");
      auth.put("michel","mic@123");
      if(userLoginRequest.getPassword().equalsIgnoreCase(auth.get(userLoginRequest.getUserID()))){
        response = new ResponseEntity<>(objectMapper
            .readValue("{  \"result\" : {    \"messageCode\" : \"200\",    \"message\" : \"Login Successful\",    \"status\" : 0  }}",
                LoginResponse.class), HttpStatus.OK);
      }else{
        response = new ResponseEntity<>(objectMapper
            .readValue("{  \"result\" : {    \"messageCode\" : \"401\",    \"message\" : \"UserId or Password is wrong\",    \"status\" : 1  }}",
                LoginResponse.class), HttpStatus.OK);
      }
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }


}
