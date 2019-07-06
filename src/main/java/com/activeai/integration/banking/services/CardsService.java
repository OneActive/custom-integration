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
import java.text.MessageFormat;
import java.util.Objects;

@Service("cardsService")
public class CardsService {

  @Autowired private CardsResponseMapper cardsResponseMapper;
  @Autowired private BlockCardResponseMapper blockCardResponseMapper;
  @Autowired private PropertyUtil propertyUtil;
  @Autowired private ActivationCardResponseMapper activationCardResponseMapper;
  private static final String error_message_format = "{0} : {1} : {2}";

  /**
   * Fetches list of Credit Cards
   *
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<CardsResponse> getCreditCardsResponseEntity(String customerId) {
    CardsResponse response = new CardsResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAccountAPIUrl(PropertyConstants.CREDIT_CARDS_API_END_POINT, customerId, null)).header("cache-control", "no-cache")
              .asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Credit Cards Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Credit Cards Response Body After Transformation :" + response);
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Credit Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCreditCardDetailsResponseEntity(String customerId, String cardNumber) {
    CardDetailResponse response = new CardDetailResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARD_DETAILS_API_END_POINT, customerId, cardNumber))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Credit Card Details Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardDetailsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Credit Card Details Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Credit Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCreditCardBalanceResponseEntity(String customerId, String cardNumber) {
    CardDetailResponse response = new CardDetailResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARD_BALANCE_API_END_POINT, customerId, cardNumber))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Card Details Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardDetailsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Card Details Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Credit Card Transactions for selected Card
   *
   * @param customerId,accountId
   * @return ResponseEntity of type CardTransactionsResponse
   */
  public ResponseEntity<CardTransactionsResponse> getCreditAccountTransactionsResponseEntity(String customerId, String accountId) {
    CardTransactionsResponse response = new CardTransactionsResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.CREDIT_CARD_TRANSACTIONS_HISTORY_API_END_POINT, customerId, accountId))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Credit Card Transactions Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardTransactionsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Credit Card Transactions Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response
        .setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }
  /**
   * Fetches list of DEBIT Cards
   *
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<CardsResponse> getDebitCardsResponseEntity(String customerId) {
    CardsResponse response = new CardsResponse();
    try {
      HttpResponse<String> apiResponse =
              Unirest.get(propertyUtil.getAccountAPIUrl(PropertyConstants.DEBIT_CARDS_API_END_POINT, customerId, null)).header("cache-control", "no-cache")
                      .asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Debit Cards Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Debit Cards Response Body After Transformation :" + response);
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Debit Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getDebitCardDetailsResponseEntity(String customerId, String cardNumber) {
    CardDetailResponse response = new CardDetailResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil.getAPIUrl(PropertyConstants.DEBIT_CARD_DETAILS_API_END_POINT, customerId, cardNumber))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Debit Card Details Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardDetailsResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Debit Card Details Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Block  Card Response
   *
   * @return ResponseEntity of type Block Card Response
   */
  public ResponseEntity<BlockCardResponse> getBlockCardResponseEntity(BlockCardRequest blockCardRequest) {
    BlockCardResponse response = new BlockCardResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.post(propertyUtil.getAPIUrlForBlockCard(PropertyConstants.BLOCK_CARD_DETAILS_API_END_POINT, blockCardRequest))
              .header("Content-Type", "application/json").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Block Card Response Body Before Transformation :" + apiResponse.getBody());
        response = blockCardResponseMapper.getManipulatedBlockCardResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Block Card Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  /**
   * Fetches ActivationCardResponse
   *
   * @return ResponseEntity of type ActivationCardResponse
   */
  public ResponseEntity<ActivationCardResponse> getActivationCardResponseEntity(ActivationCardRequest activationCardRequest) {
    ActivationCardResponse response = new ActivationCardResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.post(propertyUtil.getAPIUrlForActivateCard(PropertyConstants.ACTIVATE_CARD_DETAILS_API_END_POINT, activationCardRequest))
              .header("Content-Type", "application/json").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Activation Card Response Body Before Transformation :" + apiResponse.getBody());
        response = activationCardResponseMapper.getManipulatedActivationCardResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Activation Card Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public ResponseEntity<ResetPinConfirmResponse> getResetPinResponseEntity(ResetPinConfirmRequest resetPinConfirmRequest) {
    ResetPinConfirmResponse response = null;
    try {
      HttpResponse<String> apiResponse =
          Unirest.post(propertyUtil.getAPIUrlForResetPin(PropertyConstants.RESET_PIN_CONFIRM_API_ENDPOINT, resetPinConfirmRequest))
              .header("Content-Type", "application/json").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Reset Pin Confirm Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedResetPinConfirmResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Reset Pin Confirm Response Body After Transformation :" + response);
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public ResponseEntity<ReplaceCardConfirmResponse> getReplaceCardResponseEntity(ReplaceCardConfirmRequest replaceCardConfirmRequest) {
    ReplaceCardConfirmResponse response = new ReplaceCardConfirmResponse();
    try {
      HttpResponse<String> apiResponse =
          Unirest.post(propertyUtil.getAPIUrlForReplaceCard(PropertyConstants.REPLACE_CARD_CONFIRM_API_ENDPOINT, replaceCardConfirmRequest))
              .header("Content-Type", "application/json").asString();
      ApplicationLogger.logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Replace Card Confirm Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedReplaceCardConfirmResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Replace Card Confirm Response Body Before Transformation :" + response);
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  public ResponseEntity<InternationalUsageResponse> getInternationalUsageCardResponseEntity(InternationalCardUsageRequest internationalCardUsageRequest) {
    InternationalUsageResponse response = new InternationalUsageResponse();
    try {

      HttpResponse<String> apiResponse =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.INTERNATIONAL_USAGE_ENABLED_API_END_POINT, internationalCardUsageRequest.getCustomerId(),internationalCardUsageRequest.getCardDetails().getCardNumber())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("International Usage API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" International Usage Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedInternationalUsageResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("International Usage Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }
  public ResponseEntity<InternationalUsageResponse> updateInternationalUsageFinalApiCall(InternationalCardUsageRequest internationalCardUsageRequest) {
    InternationalUsageResponse response = new InternationalUsageResponse();
    try {

      HttpResponse<String> apiResponse =
          Unirest
              .post(propertyUtil.getAPIUrl(PropertyConstants.INTERNATIONAL_USAGE_API_END_POINT, internationalCardUsageRequest.getCustomerId(),internationalCardUsageRequest.getCardDetails().getCardNumber())).header("cache-control", "no-cache").asString();
      ApplicationLogger.logInfo("Update International Usage API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo(" International Usage Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedInternationalUsageResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Update International Usage Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(error_message_format, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

}
