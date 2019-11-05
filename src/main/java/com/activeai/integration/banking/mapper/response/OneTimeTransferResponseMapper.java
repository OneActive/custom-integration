package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.OneTimeTransferResponse;
import com.activeai.integration.banking.domain.response.PayeesValidationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component("oneTimeTransferResponseMapper")
public class OneTimeTransferResponseMapper {

  @Autowired private ObjectMapper objectMapper;
  public OneTimeTransferResponse getManipulatedOneTimeTransferResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, OneTimeTransferResponse.class);
  }
public PayeesValidationResponse getManipulatedOneTimeTransferPayeeResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, PayeesValidationResponse.class);
}
}
