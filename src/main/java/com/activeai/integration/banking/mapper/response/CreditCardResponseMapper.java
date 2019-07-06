package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.CreditCardLimitConfirmResponse;
import com.activeai.integration.banking.domain.response.CreditCardLimitResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("creditCardResponseMapper")
public class CreditCardResponseMapper {

    @Autowired private ObjectMapper objectMapper;
    /**
     * In this method you can change the obtained string accordingly to the CreditCardLimitResponse
     *
     * @param apiResponseBody
     * @return CreditCardLimitResponse
     */
    public CreditCardLimitResponse getManipulatedCreditCardLimitResponse(String apiResponseBody) throws IOException {
        return objectMapper.readValue(apiResponseBody, CreditCardLimitResponse.class);
    }

    /**
     * In this method you can change the obtained string accordingly to the CreditCardLimitConfirmResponse
     *
     * @param apiResponseBody
     * @return CreditCardLimitConfirmResponse
     */
    public CreditCardLimitConfirmResponse getManipulatedCreditCardLimitConfirmResponse(String apiResponseBody) throws IOException{
        return objectMapper.readValue(apiResponseBody, CreditCardLimitConfirmResponse.class);
    }
}
