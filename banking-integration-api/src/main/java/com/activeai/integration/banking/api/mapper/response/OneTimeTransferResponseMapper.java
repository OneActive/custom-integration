package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.OneTimeTransferResponse;
import com.activeai.integration.banking.domain.response.PayeesValidationResponse;
import org.springframework.stereotype.Component;

@Component("oneTimeTransferResponseMapper")
public class OneTimeTransferResponseMapper {

  public OneTimeTransferResponse getManipulatedOneTimeTransferResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, OneTimeTransferResponse.class);
  }

  public PayeesValidationResponse getManipulatedOneTimeTransferPayeeResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, PayeesValidationResponse.class);
  }
}
