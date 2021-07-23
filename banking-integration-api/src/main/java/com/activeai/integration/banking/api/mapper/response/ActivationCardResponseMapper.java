package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.constants.PropertyConstants;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.ActivationCardResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component("activationCardResponseMapper")
public class ActivationCardResponseMapper {

    /**
     * In this method you can change the obtained string accordingly to the ActivationCardResponse
     *
     * @param apiResponseBody
     * @return String of ActivationCardResponse
     */
    public ActivationCardResponse getManipulatedActivationCardResponse(String apiResponseBody) {
        ActivationCardResponse activationCardResponse = BankingIntegrationUtil.readValue(apiResponseBody, ActivationCardResponse.class);
        //For Random Generation of Reference Id
        activationCardResponse.setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
        return activationCardResponse;
    }
}
