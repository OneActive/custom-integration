package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.AtmLocatorRequest;
import com.activeai.integration.banking.domain.response.AtmLocatorResponse;
import com.activeai.integration.banking.services.LocatorService;
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

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class LocatorApiControllerTest {

  private MockMvc mockMvc;

  @Mock
  private LocatorService locatorService;

  @InjectMocks
  private LocatorApiController locatorApiController;

  @Before public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(locatorApiController).build();
  }

  @Test public void testGetNearestAtms() throws Exception {
    when(locatorService.getNearestAtmsAddressResponseEntity(any(AtmLocatorRequest.class)))
        .thenReturn(ResponseEntity.ok(getATMLocatorResponse()));

    String json = "{\n" + "  \"address\": {\n" + "    \"addressLine1\": \"string\",\n" + "    \"addressLine2\": \"string\",\n"
        + "    \"addressLine3\": \"string\",\n" + "    \"addressType\": \"PRIMARY\",\n" + "    \"city\": \"string\",\n"
        + "    \"country\": \"string\",\n" + "    \"countryCode\": \"string\",\n" + "    \"locality\": \"string\",\n"
        + "    \"postalCode\": \"string\",\n" + "    \"province\": \"string\",\n" + "    \"state\": \"string\"\n" + "  },\n"
        + "  \"distanceUnit\": \"string\",\n" + "  \"distanceValue\": 0,\n" + "  \"fetchType\": \"string\",\n" + "  \"geocodes\": {\n"
        + "    \"latitude\": 0,\n" + "    \"longitude\": 0\n" + "  },\n" + "  \"listCount\": 0,\n" + "  \"locateRequestType\": \"atm\",\n"
        + "  \"pageIndex\": 0,\n" + "  \"requestInfo\": \"string\"\n" + "}";

    mockMvc.perform(MockMvcRequestBuilders.post("/{fetchType}/atms", "address").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(3)));

    verify(locatorService).getNearestAtmsAddressResponseEntity(any(AtmLocatorRequest.class));

  }

  private AtmLocatorResponse getATMLocatorResponse() {
      AtmLocatorResponse atmLocatorResponse = new AtmLocatorResponse();
      return atmLocatorResponse;
  }

}