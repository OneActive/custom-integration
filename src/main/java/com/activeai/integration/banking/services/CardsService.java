package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.response.CardDetailResponse;
import com.activeai.integration.banking.domain.response.CardTransactionsResponse;
import com.activeai.integration.banking.mapper.response.CardsResponseMapper;
import com.activeai.integration.banking.model.CardsResponse;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.propertyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service("cardsService")
public class CardsService {

  @Autowired private CardsResponseMapper cardsResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private propertyUtil propertyUtil;

  /**
   * Fetches list of Cards
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<CardsResponse> getCardsResponseEntity(String customerId) {
    CardsResponse cardsResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CARDS_API_END_POINT, customerId,null)).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Cards Response Body Before Transformation :" + response.getBody());
        String cardsResponseString = cardsResponseMapper.getManipulatedCardsResponse(response.getBody());
        ApplicationLogger.logInfo("Cards Response Body After Transformation :" + response.getBody());
        cardsResponse = objectMapper.readValue(cardsResponseString, CardsResponse.class);
      }
      return new ResponseEntity<>(cardsResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(cardsResponse, HttpStatus.EXPECTATION_FAILED);
  }

  /**
   * Fetches Card Details for selected card
   * @param customerId,cardId
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCardDetailsResponseEntity(String customerId,String cardId) {
    CardDetailResponse cardDetailResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CARD_DETAILS_API_END_POINT, customerId, cardId)).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Card Details Response Body Before Transformation :" + response.getBody());
        String cardDetailsResponseString = cardsResponseMapper.getManipulatedCardDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Card Details Response Body After Transformation :" + response.getBody());
        cardDetailResponse = objectMapper.readValue(cardDetailsResponseString, CardDetailResponse.class);
      }
      return new ResponseEntity<>(cardDetailResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(cardDetailResponse, HttpStatus.EXPECTATION_FAILED);
  }

  /**
   * Fetches Card Details for selected card
   * @param customerId,cardId
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCardBalanceResponseEntity(String customerId,String cardId) {
    CardDetailResponse cardDetailResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CARD_DETAILS_API_END_POINT, customerId, cardId)).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Card Details Response Body Before Transformation :" + response.getBody());
        String cardDetailsResponseString = cardsResponseMapper.getManipulatedCardDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Card Details Response Body After Transformation :" + response.getBody());
        cardDetailResponse = objectMapper.readValue(cardDetailsResponseString, CardDetailResponse.class);
      }
      return new ResponseEntity<>(cardDetailResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(cardDetailResponse, HttpStatus.EXPECTATION_FAILED);
  }

  /**
   * Fetches Card Transactions for selected Card
   *
   * @param customerId,accountId
   * @return ResponseEntity of type CardTransactionsResponse
   */
  public ResponseEntity<CardTransactionsResponse> getAccountTransactionsResponseEntity(String customerId, String accountId) {
    CardTransactionsResponse cardTransactionsResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CARDS_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Card Transactions Response Body Before Transformation :" + response.getBody());
        String accountTransactionsResponseString = cardsResponseMapper.getManipulatedCardTransactionsResponse(response.getBody());
        ApplicationLogger.logInfo("Card Transactions Response Body After Transformation :" + response.getBody());
        cardTransactionsResponse = objectMapper.readValue(accountTransactionsResponseString, CardTransactionsResponse.class);
      }
      return new ResponseEntity<>(cardTransactionsResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(cardTransactionsResponse, HttpStatus.EXPECTATION_FAILED);
  }

  /**
   * Fetches Account Transactions for selected Account
   *
   * @param customerId,accountId
   * @return ResponseEntity of type AccountTransactionsResponse
   */
  public ResponseEntity<CardDetailResponse> getBlockCardDetailsResponseEntity(String customerId, String accountId) {
    CardDetailResponse cardDetailResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.BLOCK_CARD_DETAILS_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Card Transactions Response Body Before Transformation :" + response.getBody());
        String accountTransactionsResponseString = cardsResponseMapper.getManipulatedBlockCardDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Card Transactions Response Body After Transformation :" + response.getBody());
        cardDetailResponse = objectMapper.readValue(accountTransactionsResponseString, CardDetailResponse.class);
      }
      return new ResponseEntity<>(cardDetailResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(cardDetailResponse, HttpStatus.EXPECTATION_FAILED);
  }

}
