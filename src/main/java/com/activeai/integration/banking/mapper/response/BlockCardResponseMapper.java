package com.activeai.integration.banking.mapper.response;

import org.springframework.stereotype.Component;

@Component("blockCardResponseMapper")
public class BlockCardResponseMapper {


    /**
     * In this method you can change the obtained string accordingly to the BlockCardDetailsResponse
     * @param apiResponseBody
     * @return String of BlockCardDetailsResponse
     */
    public String getManipulatedBlockCardResponse(String apiResponseBody) {
        return apiResponseBody;
    }

}
