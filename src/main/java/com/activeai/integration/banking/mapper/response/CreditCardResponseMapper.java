package com.activeai.integration.banking.mapper.response;

import org.springframework.stereotype.Component;

@Component("creditCardResponseMapper")
public class CreditCardResponseMapper {

    /**
     * In this method you can change the obtained string accordingly to the CreditCardLimitResponse
     *
     * @param apiResponseBody
     * @return String of CreditCardLimitResponse
     */
    public String getManipulatedCreditCardLimitResponse(String apiResponseBody) {
        return apiResponseBody;
    }

    /**
     * In this method you can change the obtained string accordingly to the CreditCardLimitConfirmResponse
     *
     * @param apiResponseBody
     * @return String of CreditCardLimitConfirmResponse
     */
    public String getManipulatedCreditCardLimitConfirmResponse(String apiResponseBody) {
        return apiResponseBody;
    }
}
