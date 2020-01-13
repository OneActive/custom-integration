package com.activeai.integration.data.service;

import com.activeai.integration.data.model.CoreBankingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("genericService")
public class GenericService {

  @Autowired
  private CoreBankingService coreBankingService;

  public String resetCacheByCustomerId(String customerId) {
    coreBankingService.deleteCoreBankingModelOfCustomer(customerId);
    return "Resetting " + customerId + " data on Redis to Default values!";
  }

  public String resetCreditCards(String customerId) {
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    coreBankingModel.setCreditCardsResponse(null);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
    return "Resetting " + coreBankingModel.getId() + " Credit Cards On Redis to Default values!";
  }

  public String resetCardsTransactions(String customerId) {
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    coreBankingModel.setCardTransactionsResponse(null);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
    return "Resetting " + coreBankingModel.getId() + " Cards Transactions On Redis to Default values!";
  }

  public String resetAccountsTransactions(String customerId) {
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    coreBankingModel.setAccountTransactionsResponse(null);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
    return "Resetting " + coreBankingModel.getId() + " Accounts Transactions On Redis to Default values!";
  }

  public String resetAll() {
    coreBankingService.resetAll();
    return "Done with all Reset!";
  }
}
