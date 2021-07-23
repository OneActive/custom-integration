package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.constants.PropertyConstants;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.CreditCardLimitConfirmResponse;
import com.activeai.integration.banking.domain.response.CreditCardLimitResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component("creditCardResponseMapper")
public class CreditCardResponseMapper {

    /**
     * In this method you can change the obtained string accordingly to the CreditCardLimitResponse
     *
     * @param apiResponseBody
     * @return CreditCardLimitResponse
     */
    public CreditCardLimitResponse getManipulatedCreditCardLimitResponse(String apiResponseBody) {
        return BankingIntegrationUtil.readValue(apiResponseBody, CreditCardLimitResponse.class);
    }

    /**
     * In this method you can change the obtained string accordingly to the CreditCardLimitConfirmResponse
     *
     * @param apiResponseBody
     * @return CreditCardLimitConfirmResponse
     */
    public CreditCardLimitConfirmResponse getManipulatedCreditCardLimitConfirmResponse(String apiResponseBody) {
        CreditCardLimitConfirmResponse creditCardLimitConfirmResponse =
            BankingIntegrationUtil.readValue(apiResponseBody, CreditCardLimitConfirmResponse.class);
        //For Random Generation of Reference Id
        creditCardLimitConfirmResponse.getCardDetail()
            .setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
        return creditCardLimitConfirmResponse;
    }
}
