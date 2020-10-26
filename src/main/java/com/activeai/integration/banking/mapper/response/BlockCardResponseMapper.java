package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.BlockCardResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("blockCardResponseMapper")
public class BlockCardResponseMapper {

    @Autowired private ObjectMapper objectMapper;
    /**
     * In this method you can change the obtained string accordingly to the BlockCardDetailsResponse
     * @param apiResponseBody
     * @return String of BlockCardDetailsResponse
     */
    public BlockCardResponse getManipulatedBlockCardResponse(String apiResponseBody) throws IOException {
        BlockCardResponse blockCardResponse = objectMapper.readValue(apiResponseBody, BlockCardResponse.class);
        //For Random Generation of Reference Id
        blockCardResponse.setReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
        return blockCardResponse;
    }

}
