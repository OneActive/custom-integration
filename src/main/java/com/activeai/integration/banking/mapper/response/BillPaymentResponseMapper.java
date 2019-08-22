package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.BillPaymentResponse;
import com.activeai.integration.banking.domain.response.BillerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("billpaymentResponseMapper")
public class BillPaymentResponseMapper {

    @Autowired private ObjectMapper objectMapper;
    /**
     * In this method you can change the obtained string accordingly to the BillerResponse
     * @param apiResponseBody
     * @return String of BillerResponse
     */
    public BillerResponse getManipulatedRegisteredBillerResponse(String apiResponseBody) throws IOException {
        return objectMapper.readValue(apiResponseBody, BillerResponse.class);
    }

    /**
     * In this method you can change the obtained string accordingly to the BillerResponse
     * @param apiResponseBody
     * @return BillerResponse
     */
    public BillerResponse getManipulatedBillerDetailsResponse(String apiResponseBody) throws IOException{
        return objectMapper.readValue(apiResponseBody, BillerResponse.class);
    }

    /**
     * In this method you can change the obtained string accordingly to the BillPaymentResponse
     * @param apiResponseBody
     * @return BillPaymentResponse
     */
    public BillPaymentResponse getManipulatedBillPaymentResponse(String apiResponseBody) throws IOException {
        BillPaymentResponse billPaymentResponse = objectMapper.readValue(apiResponseBody, BillPaymentResponse.class);
        //For Random Generation of Reference Id
        billPaymentResponse.setTxnReferenceId(RandomStringUtils.random(PropertyConstants.RANDOM_VALUE_LENGTH, true, true));
        return billPaymentResponse;
    }
}
