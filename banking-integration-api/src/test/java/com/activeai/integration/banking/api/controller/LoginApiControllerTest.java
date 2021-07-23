package com.activeai.integration.banking.api.controller;

import com.activeai.integration.banking.api.services.LoginService;
import com.activeai.integration.banking.domain.model.Result;
import com.activeai.integration.banking.domain.request.UserLoginRequest;
import com.activeai.integration.banking.domain.response.LoginResponse;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginApiControllerTest {

  private MockMvc mockMvc;

  @InjectMocks private LoginApiController loginApiController;

  @Mock private LoginService loginService;

  @Before public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(loginApiController).build();
  }

  @Test public void testLogin() throws Exception {

    LoginResponse loginResponse = getUserLoginResponse();

    when(loginService.getLoginResponseEntity(any(UserLoginRequest.class))).thenReturn(ResponseEntity.ok(loginResponse));
    String json = "{\n" + "    \"userID\":\"stuart\",\n" + "    \"password\":\"stuart@123\"\n" + "}";

    mockMvc.perform(MockMvcRequestBuilders.post("/login").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.result.status", Matchers.is(200)));

    verify(loginService).getLoginResponseEntity(any(UserLoginRequest.class));
  }

  public LoginResponse getUserLoginResponse() {
    LoginResponse loginResponse = new LoginResponse();
    Result result = new Result();
    result.setMessage("Login successful");
    result.setMessageCode("OK");
    result.setStatus(200);
    loginResponse.setResult(result);
    return loginResponse;
  }
}
