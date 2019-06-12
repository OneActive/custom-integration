package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.ActivationCardRequest;
import com.activeai.integration.banking.domain.request.BlockCardRequest;
import com.activeai.integration.banking.domain.request.ReplaceCardConfirmRequest;
import com.activeai.integration.banking.domain.request.ResetPinConfirmRequest;
import com.activeai.integration.banking.domain.response.CardDetailResponse;
import com.activeai.integration.banking.domain.response.CardTransactionsResponse;
import com.activeai.integration.banking.domain.response.CardsResponse;
import com.activeai.integration.banking.domain.response.BlockCardResponse;
import com.activeai.integration.banking.mapper.response.ActivationCardResponseMapper;
import com.activeai.integration.banking.mapper.response.BlockCardResponseMapper;
import com.activeai.integration.banking.mapper.response.CardsResponseMapper;
import com.activeai.integration.banking.domain.response.ActivationCardResponse;
import com.activeai.integration.banking.domain.response.ReplaceCardConfirmResponse;
import com.activeai.integration.banking.domain.response.ResetPinConfirmResponse;
import com.activeai.integration.banking.domain.request.InternationalCardUsageRequest;
import com.activeai.integration.banking.domain.response.InternationalUsageResponse;
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
   * Fetches list of Credit Cards
   *
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<CardsResponse> getCreditCardsResponseEntity(String customerId) {
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
   * Fetches Credit Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCreditCardDetailsResponseEntity(String customerId, String cardNumber) {
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
   * Fetches Credit Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCreditCardBalanceResponseEntity(String customerId, String cardNumber) {
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
   * Fetches Credit Card Transactions for selected Card
   *
   * @param customerId,accountId
   * @return ResponseEntity of type CardTransactionsResponse
   */
  public ResponseEntity<CardTransactionsResponse> getCreditAccountTransactionsResponseEntity(String customerId, String accountId) {
    CardTransactionsResponse cardTransactionsResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARD_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
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
   * Fetches list of DEBIT Cards
   *
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<CardsResponse> getDebitCardsResponseEntity(String customerId) {
    CardsResponse cardsResponse = new CardsResponse();
    try {
      HttpResponse<String> response =
              Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.DEBIT_CARDS_API_END_POINT, customerId, null)).header("cache-control", "no-cache")
                      .asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Debit Cards Response Body Before Transformation :" + response.getBody());
        String cardsResponseString = cardsResponseMapper.getManipulatedCardsResponse(response.getBody());
        ApplicationLogger.logInfo("Debit Cards Response Body After Transformation :" + response.getBody());
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
   * Fetches Debit Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getDebitCardDetailsResponseEntity(String customerId, String cardNumber) {
    CardDetailResponse cardDetailResponse = null;
    try {
      HttpResponse<String> response = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.DEBIT_CARD_DETAILS_API_END_POINT, customerId, cardNumber))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Debit Card Details Response Body Before Transformation :" + response.getBody());
        String cardDetailsResponseString = cardsResponseMapper.getManipulatedCardDetailsResponse(response.getBody());
        ApplicationLogger.logInfo("Debit Card Details Response Body After Transformation :" + response.getBody());
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
   * Fetches Block  Card Response
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


  /**
   * Fetches ActivationCardResponse
   *
   * @return ResponseEntity of type ActivationCardResponse
   */
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

  public ResponseEntity<ResetPinConfirmResponse> getResetPinResponseEntity(ResetPinConfirmRequest resetPinConfirmRequest) {
    ResetPinConfirmResponse resetPinConfirmResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.post(propertyUtil.getAPIUrlForResetPin(PropertyConstants.RESET_PIN_CONFIRM_API_ENDPOINT, resetPinConfirmRequest))
              .header("Content-Type", "application/json").body(objectMapper.writeValueAsString(resetPinConfirmRequest)).asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Reset Pin Confirm Response Body Before Transformation :" + response.getBody());
        String resetPinConfirmResponseString = cardsResponseMapper.getManipulatedResetPinConfirmResponse(response.getBody());
        ApplicationLogger.logInfo("Reset Pin Confirm Response Body After Transformation :" + response.getBody());
        resetPinConfirmResponse = objectMapper.readValue(resetPinConfirmResponseString, ResetPinConfirmResponse.class);
      }
      return new ResponseEntity<>(resetPinConfirmResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(resetPinConfirmResponse, HttpStatus.OK);
  }

  public ResponseEntity<ReplaceCardConfirmResponse> getReplaceCardResponseEntity(ReplaceCardConfirmRequest replaceCardConfirmRequest) {
    ReplaceCardConfirmResponse replaceCardConfirmResponse = null;
    try {
      HttpResponse<String> response =
          Unirest.post(propertyUtil.getAPIUrlForReplaceCard(PropertyConstants.REPLACE_CARD_CONFIRM_API_ENDPOINT, replaceCardConfirmRequest))
              .header("Content-Type", "application/json").body(objectMapper.writeValueAsString(replaceCardConfirmRequest)).asString();
      ApplicationLogger.logInfo("API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo("Replace Card Confirm Response Body Before Transformation :" + response.getBody());
        String replaceCardConfirmResponseString = cardsResponseMapper.getManipulatedReplaceCardConfirmResponse(response.getBody());
        ApplicationLogger.logInfo("CardNumber :" + response.getBody());
        replaceCardConfirmResponse = objectMapper.readValue(replaceCardConfirmResponseString, ReplaceCardConfirmResponse.class);
      }
      return new ResponseEntity<>(replaceCardConfirmResponse, HttpStatus.valueOf(response.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    return new ResponseEntity<>(replaceCardConfirmResponse, HttpStatus.OK);
  }
  public ResponseEntity<InternationalUsageResponse> getInternationalUsageCardResponseEntity(InternationalCardUsageRequest internationalCardUsageRequest) {
    InternationalUsageResponse internationalUsageResponse = new InternationalUsageResponse();
    try {

      HttpResponse<String> response =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.INTERNATIONAL_USAGE_ENABLED_API_END_POINT, internationalCardUsageRequest.getCustomerId(),internationalCardUsageRequest.getCardDetails().getCardNumber())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("International Usage API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo(" International Usage Response Body Before Transformation :" + response.getBody());
        String depositPlanResponseString = cardsResponseMapper.getManipulatedInternationalUsageResponse(response.getBody());
        ApplicationLogger.logInfo("International Usage Response Body After Transformation :" + response.getBody());
        internationalUsageResponse = objectMapper.readValue(depositPlanResponseString, InternationalUsageResponse.class);
      }
      return ResponseEntity.ok(internationalUsageResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    internationalUsageResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(internationalUsageResponse);
  }
  public ResponseEntity<InternationalUsageResponse> updateInternationalUsageFinalApiCall(InternationalCardUsageRequest internationalCardUsageRequest) {
    InternationalUsageResponse internationalUsageResponse = new InternationalUsageResponse();
    try {

      HttpResponse<String> response =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.INTERNATIONAL_USAGE_API_END_POINT, internationalCardUsageRequest.getCustomerId(),internationalCardUsageRequest.getCardDetails().getCardNumber())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("International Usage API Response status: " + response.getStatus() + " and response status text :" + response.getStatusText());
      if (Objects.nonNull(response) && StringUtils.isNotEmpty(response.getBody())) {
        ApplicationLogger.logInfo(" International Usage Response Body Before Transformation :" + response.getBody());
        String depositPlanResponseString = cardsResponseMapper.getManipulatedInternationalUsageResponse(response.getBody());
        ApplicationLogger.logInfo("International Usage Response Body After Transformation :" + response.getBody());
        internationalUsageResponse = objectMapper.readValue(depositPlanResponseString, InternationalUsageResponse.class);
      }
      return ResponseEntity.ok(internationalUsageResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError("API failure : " + ExceptionUtils.getStackTrace(e));
    } catch (IOException e) {
      ApplicationLogger.logError("Couldn't serialize response for content type application/json :" + ExceptionUtils.getStackTrace(e));
    } catch (Exception e) {
      ApplicationLogger.logError("Something went wrong while calling API ->" + ExceptionUtils.getStackTrace(e));
    }
    internationalUsageResponse.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(internationalUsageResponse);
  }

}
