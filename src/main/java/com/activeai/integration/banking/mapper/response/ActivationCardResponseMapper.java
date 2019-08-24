package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.ActivationCardResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("activationCardResponseMapper")
public class ActivationCardResponseMapper {

    @Autowired private ObjectMapper objectMapper;

    /**
     * In this method you can change the obtained string accordingly to the ActivationCardResponse
     * @param apiResponseBody
     * @return String of ActivationCardResponse
     */
    public ActivationCardResponse getManipulatedActivationCardResponse(String apiResponseBody) throws IOException {
        ActivationCardResponse activationCardResponse = objectMapper.readValue(apiResponseBody, ActivationCardResponse.class);
        //For Random Generation of Reference Id
        activationCardResponse.setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
        return activationCardResponse;
    }
}
