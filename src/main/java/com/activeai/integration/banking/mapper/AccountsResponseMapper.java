package com.activeai.integration.banking.mapper;

import com.activeai.integration.banking.model.AccountsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("accountsResponseMapper")
public class AccountsResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the AccountsResponse
   * @param apiResponseBody
   * @return
   */
  public String getManipulatedAccountsResponse(String apiResponseBody){
    return apiResponseBody;
  }

  public String getManipulatedAccountDetailsResponse(String apiResponseBody){
    return apiResponseBody;
  }

}
