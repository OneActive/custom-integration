package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.ActivationCardRequest;
import com.activeai.integration.banking.domain.request.BlockCardRequest;
import com.activeai.integration.banking.domain.response.BlockCardResponse;
import com.activeai.integration.banking.domain.response.CardDetailResponse;
import com.activeai.integration.banking.domain.response.CardTransactionsResponse;
import com.activeai.integration.banking.domain.response.CardsResponse;
import com.activeai.integration.banking.domain.response.ActivationCardResponse;
import com.activeai.integration.banking.mapper.response.ActivationCardResponseMapper;
import com.activeai.integration.banking.mapper.response.BlockCardResponseMapper;
import com.activeai.integration.banking.mapper.response.CardsResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
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
  @Autowired private BlockCardResponseMapper blockCardResponseMapper;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private PropertyUtil propertyUtil;
  @Autowired private ActivationCardResponseMapper activationCardResponseMapper;

  /**
   * Fetches list of Cards
   *
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<CardsResponse> getCardsResponseEntity(String customerId) {
    CardsResponse cardsResponse = new CardsResponse();
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARDS_API_END_POINT, customerId, null)).header("cache-control", "no-cache")
              .asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Credit Cards Response Body Before Transformation :" + response.getBody());
        String cardsResponseString = cardsResponseMapper.getManipulatedCardsResponse(response.getBody());
        ApplicationLogger.logInfo("Credit Cards Response Body After Transformation :" + response.getBody());
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
    cardsResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(cardsResponse);
  }

  /**
   * Fetches Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCardDetailsResponseEntity(String customerId, String cardNumber) {
    CardDetailResponse cardDetailResponse = null;
    try {
      HttpResponse<String> response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARD_DETAILS_API_END_POINT, customerId, cardNumber))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Credit Card Details Response Body Before Transformation :" + response.getBody());
        String cardDetailsResponseString = cardsResponseMapper.getManipulatedCardDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Credit Card Details Response Body After Transformation :" + response.getBody());
        cardDetailResponse = objectMapper.readValue(cardDetailsResponseString, CardDetailResponse.class);
      }
      return ResponseEntity.ok(cardDetailResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    cardDetailResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(cardDetailResponse);
  }

  /**
   * Fetches Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCardBalanceResponseEntity(String customerId, String cardNumber) {
    CardDetailResponse cardDetailResponse = null;
    try {
      HttpResponse<String> response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARD_BALANCE_API_END_POINT, customerId, cardNumber))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Card Details Response Body Before Transformation :" + response.getBody());
        String cardDetailsResponseString = cardsResponseMapper.getManipulatedCardDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Card Details Response Body After Transformation :" + response.getBody());
        cardDetailResponse = objectMapper.readValue(cardDetailsResponseString, CardDetailResponse.class);
      }
      return ResponseEntity.ok(cardDetailResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    cardDetailResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(cardDetailResponse);
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
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARDS_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Credit Card Transactions Response Body Before Transformation :" + response.getBody());
        String accountTransactionsResponseString = cardsResponseMapper.getManipulatedCardTransactionsResponse(response.getBody());
        ApplicationLogger.logInfo("Credit Card Transactions Response Body After Transformation :" + response.getBody());
        cardTransactionsResponse = objectMapper.readValue(accountTransactionsResponseString, CardTransactionsResponse.class);
      }
      return ResponseEntity.ok(cardTransactionsResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    cardTransactionsResponse
        .setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(cardTransactionsResponse);
  }

  /**
   * Fetches Block Card Response
   *
   * @return ResponseEntity of type Block Card Response
   */
  public ResponseEntity<BlockCardResponse> getBlockCardResponseEntity(BlockCardRequest blockCardRequest) {
    BlockCardResponse blockCardResponse = new BlockCardResponse();
    try {
      HttpResponse<String> response =
          Unirest.post(propertyUtil.getAPIUrlForBlockCard(PropertyConstants.BLOCK_CARD_DETAILS_API_END_POINT, blockCardRequest))
              .header("Content-Type", "application/json").body(objectMapper.writeValueAsString(blockCardRequest)).asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Block Card Response Body Before Transformation :" + response.getBody());
        String blockCardResponseString = blockCardResponseMapper.getManipulatedBlockCardResponse(response.getBody());
        ApplicationLogger.logInfo("Block Card Response Body After Transformation :" + response.getBody());
        blockCardResponse = objectMapper.readValue(blockCardResponseString, BlockCardResponse.class);
      }
      return ResponseEntity.ok(blockCardResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(blockCardResponse, HttpStatus.OK);
  }

  public ResponseEntity<ActivationCardResponse> getActivationCardResponseEntity(ActivationCardRequest activationCardRequest) {
    ActivationCardResponse activationCardResponse = new ActivationCardResponse();
    try {
      HttpResponse<String> response =
          Unirest.post(propertyUtil.getAPIUrlForActivateCard(PropertyConstants.ACTIVATE_CARD_DETAILS_API_END_POINT, activationCardRequest))
              .header("Content-Type", "application/json").body(objectMapper.writeValueAsString(activationCardRequest)).asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Activation Card Response Body Before Transformation :" + response.getBody());
        String activationCardResponseString = activationCardResponseMapper.getManipulatedActivationCardResponse(response.getBody());
        ApplicationLogger.logInfo("Activation Card Response Body After Transformation :" + response.getBody());
        activationCardResponse = objectMapper.readValue(activationCardResponseString, ActivationCardResponse.class);
      }
      return ResponseEntity.ok(activationCardResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(activationCardResponse, HttpStatus.OK);
  }
}
