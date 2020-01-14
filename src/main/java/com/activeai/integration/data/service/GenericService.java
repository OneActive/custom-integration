package com.activeai.integration.data.service;

import com.activeai.integration.data.model.CoreBankingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("genericService")
public class GenericService {

  public static final String FAILURE_MESSAGE = "Something went wrong: -> ";
  public static final String SUCCESS_MESSAGE = "Deleted data on Redis!";
  @Autowired
  private CoreBankingService coreBankingService;

  /**
   * Delete all details of customer
   * 
   * @param customerId
   * @return String indicates status of update
   */
  public String resetCacheByCustomerId(String customerId) {
    try {
      coreBankingService.deleteCoreBankingModelOfCustomer(customerId);
      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      return FAILURE_MESSAGE + e.getMessage();
    }
  }

  /**
   * Delete Credit cards from cache
   * 
   * @param customerId
   * @return String indicates status of update
   */
  public String resetCreditCards(String customerId) {
    try {
      CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
      coreBankingModel.setCreditCardsResponse(null);
      coreBankingService.saveCoreBankingModel(coreBankingModel);
      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      return FAILURE_MESSAGE + e.getMessage();
    }
  }

  /**
   * Delete Transactions of Cards from cache
   * 
   * @param customerId
   * @return String indicates status of update
   */
  public String resetCardsTransactions(String customerId) {
    try {
      CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
      coreBankingModel.setCardTransactionsResponse(null);
      coreBankingService.saveCoreBankingModel(coreBankingModel);
      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      return FAILURE_MESSAGE + e.getMessage();
    }
  }

  /**
   * Delete Transactions of Accounts from cache
   * 
   * @param customerId
   * @return String indicates status of update
   */
  public String resetAccountsTransactions(String customerId) {
    try {
      CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
      coreBankingModel.setAccountTransactionsResponse(null);
      coreBankingService.saveCoreBankingModel(coreBankingModel);
      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      return FAILURE_MESSAGE + e.getMessage();
    }
  }

  /**
   * Delete all data in redis prefix with coreBankingModel
   * 
   * @return String indicates status of update
   */
  public String resetAll() {
    try {
      coreBankingService.resetAll();
      return SUCCESS_MESSAGE;
    } catch (Exception e) {
      return FAILURE_MESSAGE + e.getMessage();
    }
  }
}
