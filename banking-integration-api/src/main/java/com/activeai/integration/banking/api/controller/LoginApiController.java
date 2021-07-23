package com.activeai.integration.banking.api.controller;

import com.activeai.integration.banking.api.services.LoginService;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.domain.request.UserLoginRequest;
import com.activeai.integration.banking.domain.response.LoginResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Login and Customer Profile Related APIs", description = "Shows API documentation regarding login and customer profile APIs")
@RestController
public class LoginApiController {

  @Autowired private LoginService loginService;

  @ApiOperation("Return authorisation of user")
  @PostMapping(value = "/login", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<LoginResponse> login(@RequestBody final UserLoginRequest userLoginRequest) {
    ApplicationLogger.logInfo("Entered to Login API", this.getClass());
    return loginService.getLoginResponseEntity(userLoginRequest);
  }
}
