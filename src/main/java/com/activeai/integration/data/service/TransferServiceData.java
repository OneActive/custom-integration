package com.activeai.integration.data.service;

import com.activeai.integration.banking.constants.PayeeTypeEnum;
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

@Service("transferServiceData") public class TransferServiceData {

  @Autowired private CoreBankingService coreBankingService;

  @Autowired private AccountServiceData accountServiceData;

  public void updateTransactionDetailsOnCache(FundTransferRequest fundTransferRequest) {
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(fundTransferRequest.getCustomerId());
    accountServiceData.debitAmount(coreBankingModel, fundTransferRequest.getCustomerId(), fundTransferRequest.getAmount(),
        fundTransferRequest.getSourceAccountId());
    if (fundTransferRequest.getPayeeType().equals(PayeeTypeEnum.SELF)) {
      accountServiceData.creditAmount(coreBankingModel, fundTransferRequest.getCustomerId(), fundTransferRequest.getAmount(),
          fundTransferRequest.getPayeeAccountNumber());
      AccountTransaction transactions = mapFundTransferAsTransactionForCreditor(fundTransferRequest);
      populateTransactionsOnCoreBankingModel(coreBankingModel, transactions, fundTransferRequest.getPayeeAccountId());
    }
    AccountTransaction transactions = mapFundTransferAsTransactionForDebtor(fundTransferRequest);
    populateTransactionsOnCoreBankingModel(coreBankingModel, transactions, fundTransferRequest.getSourceAccountId());
    coreBankingService.saveCoreBankingModel(coreBankingModel);
  }

  private CoreBankingModel populateTransactionsOnCoreBankingModel(CoreBankingModel coreBankingModel, AccountTransaction transaction,
      String accountId) {
    AccountTransactionsResponse transactionsResponse = coreBankingModel.getAccountTransactionsResponse().get(accountId);
    if (Objects.nonNull(transactionsResponse) && CollectionUtils.isNotEmpty(transactionsResponse.getAccountTransactions())) {
      transactionsResponse.getAccountTransactions().add(transaction);
    } else {
      transactionsResponse = new AccountTransactionsResponse();
      List<AccountTransaction> accountTransactions = new ArrayList<>();
      accountTransactions.add(transaction);
      transactionsResponse.setAccountTransactions(accountTransactions);
    }
    return coreBankingModel;
  }

  private AccountTransaction mapFundTransferAsTransactionForDebtor(FundTransferRequest fundTransferRequest) {
    AccountTransaction transaction = mapGenericTransactionDetails(fundTransferRequest);
    transaction.setAccountId(fundTransferRequest.getPayeeAccountId());
    transaction.setAccountNumber(fundTransferRequest.getPayeeAccountNumber());
    transaction.setDescription(fundTransferRequest.getPayeeName());
    transaction.setTransactionType(TransactionTypeEnum.DEBIT);
    return transaction;
  }

  private AccountTransaction mapFundTransferAsTransactionForCreditor(FundTransferRequest fundTransferRequest) {
    AccountTransaction transaction = mapGenericTransactionDetails(fundTransferRequest);
    transaction.setAccountId(fundTransferRequest.getSourceAccountId());
    transaction.setAccountNumber(fundTransferRequest.getPayeeAccountNumber());
    transaction.setTransactionType(TransactionTypeEnum.CREDIT);
    return transaction;
  }

  private AccountTransaction mapGenericTransactionDetails(FundTransferRequest fundTransferRequest) {
    AccountTransaction transaction = new AccountTransaction();
    transaction.setDescription(fundTransferRequest.getRemarks());
    transaction.setCurrency("USD");
    transaction.setAmount(Double.valueOf(fundTransferRequest.getAmount()));
    transaction.setTransactionId(UUID.randomUUID().toString());
    transaction.setReferenceId(UUID.randomUUID().toString());
    transaction.setCategory("Fund Transfer");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime date = LocalDateTime.now();
    transaction.setTxnDate(dateFormat.format(date));
    return transaction;
  }
}
