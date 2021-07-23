package com.activeai.integration.data.service;

import com.activeai.integration.banking.common.constants.PropertyConstants;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.BankingIntegrationUtil;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.constants.CardStatusEnum;
import com.activeai.integration.banking.domain.constants.CardTypeEnum;
import com.activeai.integration.banking.domain.model.Card;
import com.activeai.integration.banking.domain.request.BlockCardRequest;
import com.activeai.integration.banking.domain.response.CardTransactionsResponse;
import com.activeai.integration.banking.domain.response.CardsResponse;
import com.activeai.integration.data.model.CoreBankingModel;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service("cardServiceData")
public class CardServiceData {

  @Autowired
  private CoreBankingService coreBankingService;

  @Autowired
  private PropertyUtil propertyUtil;

  /**
   * Cache Credit Cards and fetch transactions for each cards and cache transactions too
   *
   * @param customerId
   * @param cardsResponse contains only credit cards
   */
  public void cacheCreditCardsResponse(CoreBankingModel coreBankingModel, String customerId, CardsResponse cardsResponse) {
    ApplicationLogger.logInfo("Caching Credit cards", this.getClass());
    coreBankingModel.setCreditCardsResponse(cardsResponse);
    Map<String, CardTransactionsResponse> cardTransactionsResponseMap = new HashMap<>();
    ApplicationLogger.logInfo("Caching Credit cards Transactions", this.getClass());
    cardsResponse.getCards().forEach(c -> {
      updateDatesInCreditCard(c);
      CardTransactionsResponse cardTransactionsResponse = getCardTransactions(customerId, c.getAccountId());
      cardTransactionsResponseMap.put(c.getAccountId(), cardTransactionsResponse);
    });
    coreBankingModel.setCardTransactionsResponse(cardTransactionsResponseMap);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
  }

  /**
   * Update date in credit cards always latest
   *
   * @param c
   */
  private void updateDatesInCreditCard(Card c) {
    updateDatesInCard(c);
    LocalDate currentDate = LocalDate.now();
    LocalDate requiredDate = CoreBankingService.getRandomDate(currentDate, currentDate.plusMonths(1));
    c.setPaymentDueDate(CoreBankingService.getLocalDateAsString(requiredDate, null));
    requiredDate = CoreBankingService.getRandomDate(currentDate.minusMonths(1), currentDate);
    c.setLastStatementDate(CoreBankingService.getLocalDateAsString(requiredDate, null));
  }

  /**
   * Update date in cards always latest
   * @param c
   */
  public void updateDatesInCard(Card c){
    LocalDate currentDate = LocalDate.now();
    LocalDate requiredDate = CoreBankingService.getRandomDate(currentDate, currentDate.plusMonths(1));
    c.setPaymentDueDate(CoreBankingService.getLocalDateAsString(requiredDate, null));
    requiredDate = CoreBankingService.getRandomDate(currentDate.minusYears(3), currentDate.minusYears(1));
    c.setActivationDate(CoreBankingService.getLocalDateAsString(requiredDate, null));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CoreBankingService.API_DATE_PATTERN);
    LocalDate cardActivationDate = LocalDate.parse(c.getActivationDate(), formatter);
    c.setExpiryDate(CoreBankingService.getLocalDateAsString(cardActivationDate.plusYears(5), null));
  }

  /**
   * Update status of selected card as BLOCKED_TEMPORARY or BLOCKED_PERMANENT and cache cards
   *
   * @param blockCardRequest
   */
  public void updateBlockCardStatus(BlockCardRequest blockCardRequest) {
    ApplicationLogger.logInfo("Updating Block card status in cache", this.getClass());
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(blockCardRequest.getCustomerId());
    CardsResponse cardsResponse;
    if (CardTypeEnum.CREDIT_CARD.compareTo(blockCardRequest.getCardDetails().getCardType()) == 0) {
      cardsResponse = coreBankingModel.getCreditCardsResponse();
    } else {
      cardsResponse = coreBankingModel.getDebitCardsResponse();
    }
    if (Objects.nonNull(cardsResponse)) {
      cardsResponse.getCards().stream().forEach(c -> {
        if (blockCardRequest.getCardDetails().getCardNumber().equalsIgnoreCase(c.getCardNumber())) {
          c.setCardStatus(CardStatusEnum.fromValue(blockCardRequest.getBlockType().toString()));
        }
      });
    }
    coreBankingService.saveCoreBankingModel(coreBankingModel);
  }

  /**
   * Update status of selected card as ACTIVE and cache cards
   *
   * @param customerId
   * @param cardNumber
   * @param cardTypeEnum
   */
  public void activateCardStatus(String customerId, String cardNumber,CardTypeEnum cardTypeEnum) {
    ApplicationLogger.logInfo("Updating card Status as ACTIVE for " + cardNumber, this.getClass());
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    CardsResponse cardsResponse;
    if (CardTypeEnum.CREDIT_CARD.compareTo(cardTypeEnum) == 0) {
      cardsResponse = coreBankingModel.getCreditCardsResponse();
    } else {
      cardsResponse = coreBankingModel.getDebitCardsResponse();
    }
    if (Objects.nonNull(cardsResponse)) {
      cardsResponse.getCards().stream().forEach(c -> {
        if (cardNumber.equalsIgnoreCase(c.getCardNumber())) {
          c.setCardStatus(CardStatusEnum.ACTIVE);
        }
      });
    }
    coreBankingService.saveCoreBankingModel(coreBankingModel);
  }

  /**
   * Fetch Transactions for Accounts from cache
   *
   * @param customerId
   * @param accountId
   * @return CardTransactionsResponse
   */
  public CardTransactionsResponse getAccountTransactionsResponse(String customerId, String accountId) {
    return coreBankingService.getCoreBankingModel(customerId).getCardTransactionsResponse().get(accountId);
  }

  /**
   * Fetch Transactions of Cards from cache
   *
   * @param customerId
   * @param accountId
   * @return CardTransactionsResponse
   */
  public CardTransactionsResponse getCardTransactions(String customerId, String accountId){
    CardTransactionsResponse response = new CardTransactionsResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARD_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Account Transactions Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response =  getManipulatedCardTransactionsResponse(apiResponse.getBody());
        response.getCardTransactions().forEach(t -> {
          LocalDate date = LocalDate.now();
          LocalDate txnDate = CoreBankingService.getRandomDate(date.minusMonths(2), date);
          t.setTxnDate(CoreBankingService.getLocalDateAsString(txnDate, null));
        });
        ApplicationLogger.logInfo("Account Transactions Response Body After Transformation :" + response, this.getClass());
      }
    } catch (Exception e) {
      ApplicationLogger.logError("Some thing went wrong on trxn api!");
    }
    return response;
  }

  /**
   * In this method you can change the obtained string accordingly to the CardTransactionsResponse
   * @param apiResponseBody
   * @return String of CardTransactionsResponse
   */
  public CardTransactionsResponse getManipulatedCardTransactionsResponse(String apiResponseBody) {
    return BankingIntegrationUtil.readValue(apiResponseBody, CardTransactionsResponse.class);
  }
}
