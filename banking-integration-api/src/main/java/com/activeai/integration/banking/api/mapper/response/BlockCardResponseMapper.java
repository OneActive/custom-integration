package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.constants.PropertyConstants;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.response.BlockCardResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component("blockCardResponseMapper")
public class BlockCardResponseMapper {

    /**
     * In this method you can change the obtained string accordingly to the BlockCardDetailsResponse
     *
     * @param apiResponseBody
     * @return String of BlockCardDetailsResponse
     */
    public BlockCardResponse getManipulatedBlockCardResponse(String apiResponseBody) {
        BlockCardResponse blockCardResponse = BankingIntegrationUtil.readValue(apiResponseBody, BlockCardResponse.class);
        //For Random Generation of Reference Id
        blockCardResponse.setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
        return blockCardResponse;
    }

}
