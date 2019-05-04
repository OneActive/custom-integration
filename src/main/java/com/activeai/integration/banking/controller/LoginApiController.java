package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.UserLoginRequest;
import com.activeai.integration.banking.mapper.response.LoginResponse;
import com.activeai.integration.banking.services.LoginService;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Login Related APIs", description = "Shows API Documentation Regards Logins APIs")
@RestController
public class LoginApiController {

  private static final Logger logger = LoggerFactory.getLogger(TransfersApiController.class);
  @Autowired private ObjectMapper objectMapper;
  @Autowired private LoginService loginService;

  @ApiOperation("Return Authorisation of user")
  @RequestMapping(value = "/login", produces = {"application/json"}, consumes = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<LoginResponse> login(@RequestBody final UserLoginRequest userLoginRequest) {
    ApplicationLogger.logInfo("Entered to Login API");
    return loginService.getLoginResponseEntity(userLoginRequest);
  }
}
