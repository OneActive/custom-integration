package com.activeai.integration.banking.services;

import com.activeai.integration.banking.constants.MessageConstants;
import com.activeai.integration.banking.constants.PropertyConstants;
import com.activeai.integration.banking.domain.request.*;
import com.activeai.integration.banking.domain.response.*;
import com.activeai.integration.banking.mapper.response.ActivationCardResponseMapper;
import com.activeai.integration.banking.mapper.response.BlockCardResponseMapper;
import com.activeai.integration.banking.mapper.response.CardsResponseMapper;
import com.activeai.integration.banking.utils.ApplicationLogger;
import com.activeai.integration.banking.utils.PropertyUtil;
import com.activeai.integration.data.model.CoreBankingModel;
import com.activeai.integration.data.service.CardServiceData;
import com.activeai.integration.data.service.CoreBankingService;
import com.activeai.integration.data.service.TransferServiceData;
import com.activeai.integration.data.util.CoreBankingUtil;
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
  @Autowired private CardServiceData cardServiceData;
  @Autowired private TransferServiceData transferServiceData;
  @Autowired private CoreBankingService coreBankingService;
  private static final String ERROR_MESSAGE_FORMAT = "{0} : {1} : {2}";

  /**
   * Fetches list of Credit Cards
   *
   * @param customerId
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<CardsResponse> getCreditCardsResponseEntity(String customerId, String accessToken) {
    // Fetch cards from cache, you can remove this later
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    CardsResponse response = coreBankingModel.getCreditCardsResponse();
    if (Objects.nonNull(response)) {
      ApplicationLogger.logInfo("Credit Cards Fetched From Cache");
      return ResponseEntity.ok(response);
    }
    // Till this
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAccountAPIUrl(PropertyConstants.CREDIT_CARDS_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Credit Cards Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardsResponse(apiResponse.getBody());
        // Caching Credit cards, Remove this later
        if (CoreBankingUtil.isCacheEnabled()) {
          cardServiceData.cacheCreditCardsResponse(coreBankingModel, customerId, response);
        }
        // Till this
        ApplicationLogger.logInfo("Credit Cards Response Body After Transformation :" + response);
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
  public ResponseEntity<CardDetailResponse> getCreditCardDetailsResponseEntity(String customerId, String cardNumber, String accessToken) {
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
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
  public ResponseEntity<CardTransactionsResponse> getCreditAccountTransactionsResponseEntity(String customerId, String accountId,String accessToken) {
    //Fetch Transactions from cache, remove this later
    CardTransactionsResponse response = cardServiceData.getAccountTransactionsResponse(customerId, accountId);
    if (Objects.nonNull(response)) {
      ApplicationLogger.logInfo("Transaction for Card is shown from cache");
      return ResponseEntity.ok(response);
    }
    // Till this
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
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
  public ResponseEntity<CardsResponse> getDebitCardsResponseEntity(String customerId, String accessToken) {
    // Fetch accounts from cache you can remove this later
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    CardsResponse response = coreBankingModel.getDebitCardsResponse();
    if (Objects.nonNull(response)) {
      ApplicationLogger.logInfo("Debit Cards Fetched From Cache");
      return ResponseEntity.ok(response);
    }
    // Till this
    try {
      HttpResponse<String> apiResponse =
          Unirest.get(propertyUtil.getAccountAPIUrl(PropertyConstants.DEBIT_CARDS_API_END_POINT, customerId, null))
              .header("cache-control", "no-cache").asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Debit Cards Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardsResponse(apiResponse.getBody());
        // Caching Debit cards, Remove this later
        ApplicationLogger.logInfo("Caching Debit cards");
        coreBankingModel.setDebitCardsResponse(response);
        coreBankingService.saveCoreBankingModel(coreBankingModel);
        // Till this
        ApplicationLogger.logInfo("Debit Cards Response Body After Transformation :" + response);
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
  public ResponseEntity<CardDetailResponse> getDebitCardDetailsResponseEntity(String customerId, String cardNumber, String accessToken) {
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
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
        //updating stub data as BLOCKED on card status, Remove this later
        cardServiceData.updateBlockCardStatus(blockCardRequest);
        // Till this
        ApplicationLogger.logInfo("Block Card Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
        /*//updating stub data as ACTIVE on card status, Remove this later
        cardServiceData.activateCardStatus(activationCardRequest.getCustomerId(), activationCardRequest.getCardDetails().getCardNumber(),
            activationCardRequest.getCardDetails().getCardType());
        // Till this*/
        ApplicationLogger.logInfo("Activation Card Response Body After Transformation :" + response);
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Deprecated
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
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
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
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches ConvertEMIResponse
   *
   * @return ResponseEntity of type ConvertEMIResponse
   */
  public ResponseEntity<ConvertEMIResponse> getConvertEMIResponseEntity(String customerId, String cardNumber, String accessToken) {
    ConvertEMIResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest
          .get(propertyUtil.getAPIUrlForConvertEMI(PropertyConstants.CONVERT_EMI_API_ENDPOINT, customerId, cardNumber))
          .header("cache-control", "no-cache").asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Convert EMI Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedConvertEMIResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Convert EMI Response Body After Transformation :" + apiResponse.getBody());
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches ConvertEMIConfirmResponse
   *
   * @return ResponseEntity of type ConvertEMIConfirmResponse
   */
  public ResponseEntity<ConvertEMIConfirmResponse> getConvertEMIConfirmResponseEntity(
      ConvertEMIConfirmRequest convertEMIConfirmRequest) {
    ConvertEMIConfirmResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrlForConvertEMIConfirm(PropertyConstants.CONVERT_EMI_CONFIRM_API_ENDPOINT, convertEMIConfirmRequest)).header("Content-Type", "application/json").asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Convert EMI Confirm Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedConvertEMIConfirmResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Convert EMI Confirm Response Body After Transformation :" + apiResponse.getBody());
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches CardPaymentResponse
   *
   * @return ResponseEntity of type CardPaymentResponse
   */
  public ResponseEntity<CardPaymentResponse> getCardPaymentResponseEntity(CardPaymentRequest cardPaymentRequest) {
    CardPaymentResponse response = null;
    try {
      HttpResponse<String> apiResponse =
          Unirest.post(propertyUtil.getAPIUrlForCardPaymentConfirm(PropertyConstants.CARD_PAYMENT_API_ENDPOINT, cardPaymentRequest))
              .header("Content-Type", "application/json").asString();
      ApplicationLogger
          .logInfo("API Response status: " + apiResponse.getStatus() + " and response status text :" + apiResponse.getStatusText());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Card Payment Confirm Response Body Before Transformation :" + apiResponse.getBody());
        response = cardsResponseMapper.getManipulatedCardPaymentResponse(apiResponse.getBody());
        transferServiceData.updateTransactionDetailsOnCache(cardPaymentRequest);
        ApplicationLogger.logInfo("Card Payment Confirm Response Body After Transformation :" + apiResponse.getBody());
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.API_FAILURE_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (IOException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.DE_SERIALIZATION_EXCEPTION_MESSAGE, this.getClass().getName(),
              ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, MessageConstants.EXCEPTION_MESSAGE, this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", 500));
    return ResponseEntity.ok(response);
  }
}

