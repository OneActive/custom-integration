package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.response.BlockCardResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        return objectMapper.readValue(apiResponseBody, BlockCardResponse.class);
    }

}
