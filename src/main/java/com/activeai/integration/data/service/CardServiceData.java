package com.activeai.integration.data.service;

import com.activeai.integration.banking.constants.CardStatusEnum;
import com.activeai.integration.banking.constants.CardTypeEnum;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.BlockCardRequest;
import com.activeai.integration.banking.domain.response.CardTransactionsResponse;
import com.activeai.integration.banking.domain.response.CardsResponse;
import com.activeai.integration.banking.mapper.response.CardsResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
import com.activeai.integration.data.model.CoreBankingModel;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service("cardServiceData")
public class CardServiceData {

  @Autowired
  private CoreBankingService coreBankingService;

  @Autowired
  private PropertyUtil propertyUtil;

  @Autowired
  private CardsResponseMapper cardsResponseMapper;

  public CardsResponse getCreditCardsResponse(String customerId){
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    return coreBankingModel.getCreditCardsResponse();
  }

  public CardsResponse getDebitCardsResponse(String customerId){
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    return coreBankingModel.getDebitCardsResponse();
  }

  /**
   * cache cardsResponse and fetch transactions for each credit cards and cache transactions too
   *
   * @param customerId
   * @param cardsResponse contains only credit cards
   */
  public void cacheCreditCardsResponse(String customerId, CardsResponse cardsResponse) {
    ApplicationLogger.logInfo("Caching Credit cards");
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    coreBankingModel.setCreditCardsResponse(cardsResponse);
    Map<String, CardTransactionsResponse> cardTransactionsResponseMap = new HashMap<>();
    ApplicationLogger.logInfo("Caching Credit cards Transactions");
    cardsResponse.getCards().stream().forEach(c -> {
      CardTransactionsResponse cardTransactionsResponse = getCardTransactions(customerId,c.getAccountId());
      cardTransactionsResponseMap.put(c.getAccountId(), cardTransactionsResponse);
    });
    coreBankingModel.setCardTransactionsResponse(cardTransactionsResponseMap);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
  }

  public void cacheDebitCardsResponse(String customerId, CardsResponse cardsResponse){
    ApplicationLogger.logInfo("Caching Debit cards");
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    coreBankingModel.setDebitCardsResponse(cardsResponse);
    coreBankingService.saveCoreBankingModel(coreBankingModel);
  }

  public void updateBlockCardStatus(BlockCardRequest blockCardRequest) {
    ApplicationLogger.logInfo("Updating Block card status in cache");
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

  public void activateCardStatus(String customerId, String cardNumber,CardTypeEnum cardTypeEnum) {
    ApplicationLogger.logInfo("Updating card Status as ACTIVE for " + cardNumber);
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

  public CardTransactionsResponse getAccountTransactionsResponse(String customerId, String accountId) {
    return coreBankingService.getCoreBankingModel(customerId).getCardTransactionsResponse().get(accountId);
  }

  public CardTransactionsResponse getCardTransactions(String customerId, String accountId){
    CardTransactionsResponse response = new CardTransactionsResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARD_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Account Transactions Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardTransactionsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Account Transactions Response Body After Transformation :" + response);
      }
    } catch (Exception e) {
      ApplicationLogger.logError("Some thing went wrong on trxn api!");
    }
    return response;
  }
}
