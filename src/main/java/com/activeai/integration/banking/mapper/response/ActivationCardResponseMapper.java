package com.activeai.integration.banking.mapper.response;

import org.springframework.stereotype.Component;

@Component("activationCardResponseMapper")
public class ActivationCardResponseMapper {


    /**
     * In this method you can change the obtained string accordingly to the ActivationCardResponse
     * @param apiResponseBody
     * @return String of ActivationCardResponse
     */
    public String getManipulatedActivationCardResponse(String apiResponseBody) {
        return apiResponseBody;
    }
}
