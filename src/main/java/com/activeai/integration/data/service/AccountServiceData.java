package com.activeai.integration.data.service;

import com.activeai.integration.banking.constants.AccountTypeEnum;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.AccountTransactionsResponse;
import com.activeai.integration.banking.domain.response.AccountsResponse;
import com.activeai.integration.banking.domain.response.CardsResponse;
import com.activeai.integration.banking.mapper.response.AccountsResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
import com.activeai.integration.data.model.CoreBankingModel;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service("accountServiceData")
public class AccountServiceData {

  @Autowired
  private CoreBankingService coreBankingService;

  @Autowired
  private PropertyUtil propertyUtil;

  @Autowired
  private AccountsResponseMapper accountsResponseMapper;

  /**
   * Debit amount from account and cache updated details
   *
   * @param coreBankingModel
   * @param debitedAmount
   * @param accountId
   * @return CoreBankingModel
   */
  public CoreBankingModel debitAmount(CoreBankingModel coreBankingModel, String debitedAmount, String accountId) {
    ApplicationLogger.logInfo("Debiting " + debitedAmount + " from account ID " + accountId);
    AccountsResponse accountsResponse = coreBankingModel.getAccountsResponse();
    if (Objects.nonNull(accountsResponse)) {
      accountsResponse.getAccounts().stream().forEach(a -> {
        if (accountId.equalsIgnoreCase(a.getAccountId())) {
          a.getBalance().setAmount(a.getBalance().getAmount() - Double.valueOf(debitedAmount));
          a.getBalance().setAvailableBalance(a.getBalance().getAvailableBalance() - Double.valueOf(debitedAmount));
        }
      });
    }
    return coreBankingModel;
  }

  /**
   * Credit amount to Account and cache updated details
   *
   * @param coreBankingModel
   * @param creditAmount
   * @param accountId
   * @return CoreBankingModel
   */
  public CoreBankingModel creditAmount(CoreBankingModel coreBankingModel, String creditAmount, String accountId) {
    ApplicationLogger.logInfo("Crediting " + creditAmount + " to account ID " + accountId);
    AccountsResponse accountsResponse = coreBankingModel.getAccountsResponse();
    if (Objects.nonNull(accountsResponse)) {
      accountsResponse.getAccounts().stream().forEach(a -> {
        if (accountId.equalsIgnoreCase(a.getAccountId())) {
          a.getBalance().setAmount(a.getBalance().getAmount() + Double.valueOf(creditAmount));
          a.getBalance().setAvailableBalance(a.getBalance().getAvailableBalance() + Double.valueOf(creditAmount));
        }
      });
    }
    return coreBankingModel;
  }

  /**
   * Credit amount to be paid for Credit Card and cache updated details
   *
   * @param coreBankingModel
   * @param amount
   * @param cardId
   * @return CoreBankingModel
   */
  public CoreBankingModel creditOnBill(CoreBankingModel coreBankingModel, String amount, String cardId) {
    ApplicationLogger.logInfo("Paying " + amount + " for credit card of ID " + cardId);
    CardsResponse cardsResponse = coreBankingModel.getCreditCardsResponse();
    if (Objects.nonNull(cardsResponse) && CollectionUtils.isNotEmpty(cardsResponse.getCards())) {
      cardsResponse.getCards().stream().forEach(c -> {
        if (cardId.equalsIgnoreCase(c.getAccountId())) {
          c.lastStatementBalance(c.getAmountDue());
          c.setAmountDue(c.getAmountDue() - Double.valueOf(amount));
          c.setMinimumPayment(0.0);
          c.setOutStandingAmount(c.getOutStandingAmount() - c.getAmountDue());
        }
      });
    }
    return coreBankingModel;
  }

  /**
   * Fetch Transaction for Account from cache
   *
   * @param customerId
   * @param accountId
   * @return AccountTransactionsResponse
   */
  public AccountTransactionsResponse getAccountTransactionsResponse(String customerId, String accountId) {
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    if (Objects.nonNull(coreBankingModel.getCardTransactionsResponse())) {
      return coreBankingModel.getAccountTransactionsResponse().get(accountId);
    }
    return null;
  }

  /**
   * Cache Accounts and fetch transactions for Savings and Checking  each accounts and cache transactions too
   *
   * @param customerId
   * @param accountsResponse
   */
  public void cacheAccountResponse(CoreBankingModel coreBankingModel, String customerId,AccountsResponse accountsResponse) {
    ApplicationLogger.logInfo("Caching Accounts");
    coreBankingModel.setAccountsResponse(accountsResponse);
    Map<String, AccountTransactionsResponse> accountTransactionsResponseMap = new HashMap<>();
    ApplicationLogger.logInfo("Caching Accounts Transactions");
    accountsResponse.getAccounts().stream().forEach(a -> {
      if (AccountTypeEnum.SAVINGS.compareTo(a.getAccountType()) == 0 || AccountTypeEnum.CHECKING.compareTo(a.getAccountType()) == 0) {
        AccountTransactionsResponse accountTransactionsResponse = getAccountTransactions(customerId, a.getAccountId());
        accountTransactionsResponseMap.put(a.getAccountId(), accountTransactionsResponse);
      }
    });
    coreBankingModel.setAccountTransactionsResponse(accountTransactionsResponseMap);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
  }

  /**
   * Fetch Transactions For Account
   *
   * @param customerId
   * @param accountId
   * @return AccountTransactionsResponse
   */
  public AccountTransactionsResponse getAccountTransactions(String customerId, String accountId){
    AccountTransactionsResponse response = new AccountTransactionsResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CASA_ACCOUNT_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Account Transactions Response Body Before Transformation :" + apiResponse.getBody());
        response = accountsResponseMapper.getManipulatedAccountTransactionsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Account Transactions Response Body After Transformation :" + response);
      }
    } catch (Exception e) {
      ApplicationLogger.logError("Some thing went wrong on trxn api!");
    }
    return response;
  }
}
