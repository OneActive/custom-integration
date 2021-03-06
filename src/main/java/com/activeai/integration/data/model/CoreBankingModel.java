package com.activeai.integration.data.model;

import com.activeai.integration.banking.domain.response.*;
import com.activeai.integration.data.constants.CoreBankingConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RedisHash("coreBankingModel")
public class CoreBankingModel implements Serializable {

  /**
   * customer ID
   */
  @Id
  private String id;

  /**
   * expiry time for redis storage
   */
  @TimeToLive
  private long expiryTime;

  /**
   * CASA account response
   */
  private AccountsResponse accountsResponse;

  /**
   * Deposit accounts
   */
  private DepositAccountsResponse depositAccountsResponse;

  /**
   * CreditCards
   */
  private CardsResponse creditCardsResponse;

  /**
   * DebitCards
   */
  private CardsResponse debitCardsResponse;

  /**
   * key will be account number and value will be transactions of particular account
   */
  private Map<String,AccountTransactionsResponse> accountTransactionsResponse = new HashMap();

  /**
   * key will be card number and value will be transactions of particular card
   */
  private Map<String,CardTransactionsResponse> cardTransactionsResponse = new HashMap();

  /**
   * Payees Response
   */
  private PayeesResponse payeesResponse;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setExpiryTime(long expiryTime) {
    this.expiryTime = expiryTime;
  }

  public AccountsResponse getAccountsResponse() {
    return accountsResponse;
  }

  public void setAccountsResponse(AccountsResponse accountsResponse) {
    this.accountsResponse = accountsResponse;
  }

  public DepositAccountsResponse getDepositAccountsResponse() {
    return depositAccountsResponse;
  }

  public void setDepositAccountsResponse(DepositAccountsResponse depositAccountsResponse) {
    this.depositAccountsResponse = depositAccountsResponse;
  }

  public Map<String, AccountTransactionsResponse> getAccountTransactionsResponse() {
    return accountTransactionsResponse;
  }

  public void setAccountTransactionsResponse(Map<String, AccountTransactionsResponse> accountTransactionsResponse) {
    this.accountTransactionsResponse = accountTransactionsResponse;
  }

  public Map<String, CardTransactionsResponse> getCardTransactionsResponse() {
    return cardTransactionsResponse;
  }

  public void setCardTransactionsResponse(Map<String, CardTransactionsResponse> cardTransactionsResponse) {
    this.cardTransactionsResponse = cardTransactionsResponse;
  }

  public CardsResponse getCreditCardsResponse() {
    return creditCardsResponse;
  }

  public void setCreditCardsResponse(CardsResponse creditCardsResponse) {
    this.creditCardsResponse = creditCardsResponse;
  }

  public CardsResponse getDebitCardsResponse() {
    return debitCardsResponse;
  }

  public void setDebitCardsResponse(CardsResponse debitCardsResponse) {
    this.debitCardsResponse = debitCardsResponse;
  }

  public PayeesResponse getPayeesResponse() {
    return payeesResponse;
  }

  public void setPayeesResponse(PayeesResponse payeesResponse) {
    this.payeesResponse = payeesResponse;
  }
}
