package com.activeai.integration.banking.mapper.response;

import com.activeai.integration.banking.apiservice.domain.response.CitiAccountResponse;
import com.activeai.integration.banking.apiservice.model.AccountGroupSummary;
import com.activeai.integration.banking.constants.AccountStatusEnum;
import com.activeai.integration.banking.constants.AccountTypeEnum;
import com.activeai.integration.banking.domain.response.AccountsResponse;
import com.activeai.integration.banking.model.Account;
import com.activeai.integration.banking.model.AccountBalance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("accountsResponseMapper")
public class AccountsResponseMapper {

  /**
   * In this method you can change the obtained string accordingly to the AccountsResponse
   * @param citiAccountResponse CITI BANAMEX
   * @return String of AccountsResponse
   */
  public AccountsResponse getManipulatedCasaAccountsResponse(CitiAccountResponse citiAccountResponse) {
    AccountsResponse accountsResponse = new AccountsResponse();
    List<Account> accounts = new ArrayList<>();
    if (citiAccountResponse.getAccountGroupSummary() != null) {
      for (AccountGroupSummary accountGroupSummary : citiAccountResponse.getAccountGroupSummary()) {
        if (accountGroupSummary.getAccountGroup().equalsIgnoreCase("CHECKING")) {
          accountGroupSummary.getCheckingAccountsSummary().forEach(checkingAccountsSummary -> {
            Account account = new Account();
            account.setAccountId(checkingAccountsSummary.getAccountId());
            account.setAccountNumber(checkingAccountsSummary.getDisplayAccountNumber());
            account.setAccountName(checkingAccountsSummary.getProductName());
            account.setAccountType(AccountTypeEnum.CHECKING);
            account.setStatus(AccountStatusEnum.ACTIVE);
            account.setDisplayAccountNumber(checkingAccountsSummary.getDisplayAccountNumber());
            AccountBalance accountBalance = new AccountBalance();
            accountBalance.setAmount(Double.valueOf(checkingAccountsSummary.getAvailableBalance()));
            accountBalance.setAvailableBalance(Double.valueOf(checkingAccountsSummary.getAvailableBalance()));
            accountBalance.setCurrencyCode(checkingAccountsSummary.getCurrencyCode());
            accountBalance.setCurrentBalance(Double.valueOf(checkingAccountsSummary.getCurrentBalance()));
            account.setBalance(accountBalance);
            accounts.add(account);
          });
        } else if (accountGroupSummary.getAccountGroup().equalsIgnoreCase("SAVINGS")) {
          accountGroupSummary.getSavingsAccountsSummary().forEach(savingsAccountsSummary -> {
            Account account = new Account();
            account.setAccountId(savingsAccountsSummary.getAccountId());
            account.setAccountNumber(savingsAccountsSummary.getDisplayAccountNumber());
            account.setAccountName(savingsAccountsSummary.getProductName());
            account.setStatus(AccountStatusEnum.ACTIVE);
            account.setAccountType(AccountTypeEnum.SAVINGS);
            account.setDisplayAccountNumber(savingsAccountsSummary.getDisplayAccountNumber());
            AccountBalance accountBalance = new AccountBalance();
            accountBalance.setAmount(Double.valueOf(savingsAccountsSummary.getAvailableBalance()));
            accountBalance.setAvailableBalance(Double.valueOf(savingsAccountsSummary.getAvailableBalance()));
            accountBalance.setCurrencyCode(savingsAccountsSummary.getCurrencyCode());
            accountBalance.setCurrentBalance(Double.valueOf(savingsAccountsSummary.getCurrentBalance()));
            account.setBalance(accountBalance);
            accounts.add(account);
          });
        }
      }
    }
    accountsResponse.setAccounts(accounts);
    return accountsResponse;
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountDetailsResponse
   * @param apiResponseBody
   * @return String of AccountDetailsResponse
   */
  public String getManipulatedAccountDetailsResponse(String apiResponseBody) {
    return apiResponseBody;
  }


  /**
   * In this method you can change the obtained string accordingly to the AccountsResponse
   * @param apiResponseBody
   * @return String of AccountDetailsResponse
   */
  public String getManipulatedAccountsResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountsBalanceResponse
   * @param apiResponseBody
   * @return String of AccountsBalanceResponse
   */
  public String getManipulatedAccountsBalanceResponse(String apiResponseBody) {
    return apiResponseBody;
  }

  /**
   * In this method you can change the obtained string accordingly to the AccountTransactionsResponse
   * @param apiResponseBody
   * @return String of AccountTransactionsResponse
   */
  public String getManipulatedAccountTransactionsResponse(String apiResponseBody) {
    return apiResponseBody;
  }
}
