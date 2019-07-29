package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.domain.request.AtmLocatorRequest;
import com.activeai.integration.banking.domain.response.AtmLocatorResponse;
import com.activeai.integration.banking.model.Address;
import com.activeai.integration.banking.model.AtmBranch;
import com.activeai.integration.banking.model.Geocodes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component("atmLocatorResponseMapper")
public class AtmLocatorResponseMapper {

  @Autowired
  private ObjectMapper objectMapper;

  /**
   * In this method you can change the obtained string accordingly to the DepositServiceResponse
   *
   * @param locatorResponses
   * @return String of AtmLocatorResponse
   */
  public AtmLocatorResponse getManipulatedAtmsAddressResponse(AtmLocatorResponse locatorResponses, AtmLocatorRequest atmLocatorRequest) {
    AtmLocatorResponse atmLocatorResponse = new AtmLocatorResponse();
    List<AtmBranch> atmBranchList = new ArrayList<>();
    if (Objects.nonNull(locatorResponses.getAtmBranches())) {

      for (AtmBranch atmBranch : locatorResponses.getAtmBranches()) {
        if (Objects.nonNull(atmBranch.getAddress()) && StringUtils.isNotEmpty(atmLocatorRequest.getAddress().getLocality())) {
          String locality = atmLocatorRequest.getAddress().getLocality().toLowerCase();
          if (StringUtils.isNotEmpty(atmBranch.getAddress().getCity())&&atmBranch.getAddress().getCity().toLowerCase().contains(locality) || StringUtils.isNotEmpty(atmBranch.getAddress().getAddressLine1())&&
              atmBranch.getAddress().getAddressLine1().toLowerCase().contains(locality) ||StringUtils.isNotEmpty(atmBranch.getAddress().getLocality())&& atmBranch.getAddress().getLocality().toLowerCase().contains(locality) ||
              StringUtils.isNotEmpty(atmBranch.getAddress().getCountry())&&atmBranch.getAddress().getCountry().toLowerCase().contains(locality) || StringUtils.isNotEmpty(atmBranch.getAddress().getState())&&
              atmBranch.getAddress().getState().toLowerCase().contains(locality) || (
              atmLocatorRequest.getDistanceValue() != null && atmLocatorRequest.getDistanceValue()
                  .equals(atmLocatorRequest.getDistanceValue()))) {
            atmBranchList.add(atmBranch);
          }
        }
      }
    }
    atmLocatorResponse.setAtmBranches(atmBranchList);


    return atmLocatorResponse;
  }

  public AtmLocatorResponse getManipulatedAtmsResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, AtmLocatorResponse.class);
  }
}
