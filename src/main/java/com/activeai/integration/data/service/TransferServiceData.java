package com.activeai.integration.data.service;

import com.activeai.integration.banking.constants.TransactionTypeEnum;
import com.activeai.integration.banking.domain.request.FundTransferRequest;
import com.activeai.integration.banking.domain.response.AccountTransactionsResponse;
import com.activeai.integration.banking.model.AccountTransaction;
import com.activeai.integration.data.model.CoreBankingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.collections.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service("transferServiceData")
public class TransferServiceData {

  @Autowired
  private CoreBankingService coreBankingService;

  @Autowired
  private AccountServiceData accountServiceData;

  public void updateTransactionResponse(FundTransferRequest fundTransferRequest) {
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(fundTransferRequest.getCustomerId());
    AccountTransactionsResponse transactionsResponse =
        coreBankingModel.getAccountTransactionsResponse().get(fundTransferRequest.getSourceAccountId());
    AccountTransaction transaction = mapFundTransferAsTransaction(fundTransferRequest);
    if (Objects.nonNull(transactionsResponse) && CollectionUtils.isNotEmpty(transactionsResponse.getAccountTransactions())) {
      transactionsResponse.getAccountTransactions().add(transaction);
    } else {
      transactionsResponse = new AccountTransactionsResponse();
      List<AccountTransaction> accountTransactions = new ArrayList<>();
      accountTransactions.add(transaction);
      transactionsResponse.setAccountTransactions(accountTransactions);
    }
    coreBankingService.saveCoreBankingModel(coreBankingModel);
  }

  private AccountTransaction mapFundTransferAsTransaction(FundTransferRequest fundTransferRequest) {
    AccountTransaction transaction = new AccountTransaction();
    transaction.setCurrency("USD");
    transaction.setAccountId(fundTransferRequest.getPayeeAccountId());
    transaction.setAccountNumber(fundTransferRequest.getPayeeAccountNumber());
    transaction.setAmount(Double.valueOf(fundTransferRequest.getAmount()));
    transaction.setDescription(fundTransferRequest.getRemarks());
    transaction.setTransactionId(UUID.randomUUID().toString());
    transaction.setReferenceId(UUID.randomUUID().toString());
    transaction.setTransactionType(TransactionTypeEnum.DEBIT);
    transaction.setCategory("Fund Transfer");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime date = LocalDateTime.now();
    transaction.setTxnDate(dateFormat.format(date));
    return transaction;
  }

}
