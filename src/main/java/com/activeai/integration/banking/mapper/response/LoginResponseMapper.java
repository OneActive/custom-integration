package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.apiservice.domain.response.CitiAccountResponse;
import com.activeai.integration.banking.domain.response.LoginResponse;
import com.activeai.integration.banking.model.Result;
import com.activeai.integration.banking.model.User;
import com.activeai.integration.banking.utils.ApplicationLogger;
import org.springframework.stereotype.Component;

@Component("loginResponseMapper")
public class LoginResponseMapper {
  /**
   * In this method you can change the obtained string accordingly to the LoginResponse
   *
   * @param citiAccountResponse CITI BANAMEX
   * @return String of LoginResponse
   */
  public LoginResponse getManipulatedLoginResponse(CitiAccountResponse citiAccountResponse) {
    LoginResponse loginResponse = new LoginResponse();
    User user = new User();
    Result result = new Result();
    if (citiAccountResponse.getAccountGroupSummary() != null) {
      String accountGroup = citiAccountResponse.getAccountGroupSummary().get(0).getAccountGroup();
      result.setStatus(200);
      switch (accountGroup) {
        case "CREDITCARD":
          user.setCustomerId(citiAccountResponse.getAccountGroupSummary().get(0).getCreditCardAccountsSummary() != null ?
              citiAccountResponse.getAccountGroupSummary().get(0).getCreditCardAccountsSummary().get(0).getAccountId() :
              null);
          break;
        case "CHECKING":
          user.setCustomerId(citiAccountResponse.getAccountGroupSummary().get(0).getCheckingAccountsSummary() != null ?
              citiAccountResponse.getAccountGroupSummary().get(0).getCheckingAccountsSummary().get(0).getAccountId() :
              null);
          break;
        case "BROKERAGE":
          user.setCustomerId(citiAccountResponse.getAccountGroupSummary().get(0).getBrokerageAccountsSummary() != null ?
              citiAccountResponse.getAccountGroupSummary().get(0).getBrokerageAccountsSummary().get(0).getAccountId() :
              null);
          break;
        case "SAVINGS":
          user.setCustomerId(citiAccountResponse.getAccountGroupSummary().get(0).getSavingsAccountsSummary() != null ?
              citiAccountResponse.getAccountGroupSummary().get(0).getSavingsAccountsSummary().get(0).getAccountId() :
              null);
          break;
        default:
          ApplicationLogger.logInfo("unknown account category received from response");
          result.setStatus(500);
          break;

      }
    }
    loginResponse.setResult(result);
    loginResponse.setUser(user);
    ApplicationLogger.logInfo("customerId set in loginResponse is --" + loginResponse.getUser().getCustomerId());

    return loginResponse;
  }

}
