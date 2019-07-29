package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.constants.LocatorTypeEnum;
import com.activeai.integration.banking.domain.request.AtmLocatorRequest;
import com.activeai.integration.banking.domain.response.AtmLocatorResponse;
import com.activeai.integration.banking.model.Address;
import com.activeai.integration.banking.model.AtmBranch;
import com.activeai.integration.banking.model.Geocodes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
  public AtmLocatorResponse getManipulatedAtmsAddressResponse(AtmLocatorResponse locatorResponses, AtmLocatorRequest atmLocatorRequest)
      throws IOException {
    AtmLocatorResponse atmLocatorResponse = new AtmLocatorResponse();
    List<AtmBranch> atmBranchList = new ArrayList<>();
    if (locatorResponses != null) {
      if (locatorResponses.getAtmBranches() != null) {
        for (AtmBranch atmBranch : locatorResponses.getAtmBranches()) {
          AtmBranch branch = new AtmBranch();
          if (atmBranch.getAddress() != null) {
            Address address = new Address();
            Geocodes geocodes = new Geocodes();
            if(atmLocatorRequest.getAddress().getLocality()!=null){
              if (atmBranch.getAddress().getCity().toLowerCase().contains(atmLocatorRequest.getAddress().getLocality().toLowerCase()) || atmBranch
                  .getAddress().getAddressLine1().toLowerCase().contains(atmLocatorRequest.getAddress().getLocality().toLowerCase()) || atmBranch
                  .getAddress().getLocality().toLowerCase().contains(atmLocatorRequest.getAddress().getLocality().toLowerCase()) || atmBranch.getAddress()
                  .getCountry().toLowerCase().contains(atmLocatorRequest.getAddress().getLocality().toLowerCase()) || atmBranch.getAddress().getState().toLowerCase()
                  .contains(atmLocatorRequest.getAddress().getLocality().toLowerCase()))
              {
                branch.setLocateResponseType(atmBranch.getLocateResponseType());
                address.setPostalCode(atmBranch.getAddress().getPostalCode());
                address.setAddressLine1(atmBranch.getAddress().getAddressLine1());
                address.setLocality(atmBranch.getAddress().getLocality());
                address.setCity(atmBranch.getAddress().getCity());
                address.setCountry(atmBranch.getAddress().getCountry());
                address.setState(atmBranch.getAddress().getState());
                branch.setAddress(address);
                branch.setName(atmBranch.getName());
                branch.setDistanceUnit(atmBranch.getDistanceUnit());
                branch.setDistanceValue(atmBranch.getDistanceValue());
                geocodes.setLatitude(atmBranch.getGeocodes().getLatitude());
                geocodes.setLongitude(atmBranch.getGeocodes().getLongitude());
                branch.setGeocodes(geocodes);
                branch.setPageIndex(atmBranch.getPageIndex());
                branch.setListCount(atmBranch.getListCount());
                branch.setOperatingHours(atmBranch.getOperatingHours());
                branch.setPhoneNumber(atmBranch.getPhoneNumber());
                branch.setFax(atmBranch.getFax());
                atmBranchList.add(branch);
              }

            }
            if(atmLocatorRequest.getDistanceValue()!=null)
            {
              if(atmBranch.getDistanceValue().equals(atmLocatorRequest.getDistanceValue()))
              {
                branch.setLocateResponseType(atmBranch.getLocateResponseType());
                address.setPostalCode(atmBranch.getAddress().getPostalCode());
                address.setAddressLine1(atmBranch.getAddress().getAddressLine1());
                address.setLocality(atmBranch.getAddress().getLocality());
                address.setCity(atmBranch.getAddress().getCity());
                address.setCountry(atmBranch.getAddress().getCountry());
                address.setState(atmBranch.getAddress().getState());
                branch.setAddress(address);
                branch.setName(atmBranch.getName());
                branch.setDistanceUnit(atmBranch.getDistanceUnit());
                branch.setDistanceValue(atmBranch.getDistanceValue());
                geocodes.setLatitude(atmBranch.getGeocodes().getLatitude());
                geocodes.setLongitude(atmBranch.getGeocodes().getLongitude());
                branch.setGeocodes(geocodes);
                branch.setPageIndex(atmBranch.getPageIndex());
                branch.setListCount(atmBranch.getListCount());
                branch.setOperatingHours(atmBranch.getOperatingHours());
                branch.setPhoneNumber(atmBranch.getPhoneNumber());
                branch.setFax(atmBranch.getFax());
                atmBranchList.add(branch);
              }
            }
            }

        }
        atmLocatorResponse.setAtmBranches(atmBranchList);
      }

    }
    return atmLocatorResponse;
  }

  public AtmLocatorResponse getManipulatedAtmsResponse(String apiResponseBody) throws IOException {
    return objectMapper.readValue(apiResponseBody, AtmLocatorResponse.class);
  }
}
