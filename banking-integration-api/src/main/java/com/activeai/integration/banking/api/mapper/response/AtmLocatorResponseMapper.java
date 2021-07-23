package com.activeai.integration.banking.api.mapper.response;

import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.domain.model.AtmBranch;
import com.activeai.integration.banking.domain.request.AtmLocatorRequest;
import com.activeai.integration.banking.domain.response.AtmLocatorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component("atmLocatorResponseMapper")
public class AtmLocatorResponseMapper {

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
          mapATMBranchAdresses(atmLocatorRequest, atmBranchList, atmBranch);
        }
        if (atmLocatorRequest.getDistanceValue() != null && atmBranch.getDistanceValue().equals(atmLocatorRequest.getDistanceValue())) {
          atmBranchList.add(atmBranch);
        }
      }
    }
    atmLocatorResponse.setAtmBranches(atmBranchList);
    return atmLocatorResponse;
  }

  /**
   * mapping ATM Branch Addresses
   * 
   * @param atmLocatorRequest
   * @param atmBranchList
   * @param atmBranch
   */
  private void mapATMBranchAdresses(AtmLocatorRequest atmLocatorRequest, List<AtmBranch> atmBranchList, AtmBranch atmBranch) {
    String locality = atmLocatorRequest.getAddress().getLocality().toLowerCase();
    if (StringUtils.isNotEmpty(atmBranch.getAddress().getCity()) && atmBranch.getAddress().getCity().toLowerCase().contains(locality)
        || StringUtils.isNotEmpty(atmBranch.getAddress().getAddressLine1()) && atmBranch.getAddress().getAddressLine1().toLowerCase()
        .contains(locality) || StringUtils.isNotEmpty(atmBranch.getAddress().getLocality()) && atmBranch.getAddress().getLocality()
        .toLowerCase().contains(locality) || StringUtils.isNotEmpty(atmBranch.getAddress().getCountry()) && atmBranch.getAddress()
        .getCountry().toLowerCase().contains(locality) || StringUtils.isNotEmpty(atmBranch.getAddress().getState()) && atmBranch
        .getAddress().getState().toLowerCase().contains(locality)) {
      atmBranchList.add(atmBranch);
    }
  }

  public AtmLocatorResponse getManipulatedAtmsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, AtmLocatorResponse.class);
  }
}
