package com.activeai.integration.banking.api.services;

import com.activeai.integration.banking.api.constants.APIConstants;
import com.activeai.integration.banking.api.constants.MessageConstants;
import com.activeai.integration.banking.api.constants.PropertyConstants;
import com.activeai.integration.banking.api.mapper.response.ActivationCardResponseMapper;
import com.activeai.integration.banking.api.mapper.response.BlockCardResponseMapper;
import com.activeai.integration.banking.api.mapper.response.CardsResponseMapper;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.common.util.PropertyUtil;
import com.activeai.integration.banking.domain.request.*;
import com.activeai.integration.banking.domain.response.*;
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
   * @param accessToken for future enhancement
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<CardsResponse> getCreditCardsResponseEntity(String customerId, String accessToken) {
    // Fetch cards from cache, you can remove this later
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    CardsResponse response = coreBankingModel.getCreditCardsResponse();
    if (Objects.nonNull(response)) {
      ApplicationLogger.logInfo("Credit Cards Fetched From Cache", this.getClass());
      return ResponseEntity.ok(response);
    }
    // Till this
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAccountAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.CREDIT_CARDS_API_END_POINT, customerId, null))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Credit Cards Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = cardsResponseMapper.getManipulatedCardsResponse(apiResponse.getBody());
        // Caching Credit cards, Remove this later
        if (CoreBankingUtil.isCacheEnabled()) {
          cardServiceData.cacheCreditCardsResponse(coreBankingModel, customerId, response);
        }
        // Till this
        ApplicationLogger.logInfo("Credit Cards Response Body After Transformation :" + response, this.getClass());
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new CardsResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Credit Card Details for selected card
   *
   * @param customerId,cardNumber
   * @param accessToken           for future enhancement
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCreditCardDetailsResponseEntity(String customerId, String cardNumber, String accessToken) {
    CardDetailResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.CREDIT_CARD_DETAILS_API_END_POINT, customerId,
              cardNumber)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processCardDetailResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new CardDetailResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Credit Card Details for selected card
   *
   * @param customerId,cardNumber
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getCreditCardBalanceResponseEntity(String customerId, String cardNumber) {
    CardDetailResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.CREDIT_CARD_BALANCE_API_END_POINT, customerId,
              cardNumber)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processCardDetailResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new CardDetailResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Credit Card Transactions for selected Card
   *
   * @param customerId,accountId
   * @param accessToken          for future enhancement
   * @return ResponseEntity of type CardTransactionsResponse
   */
  public ResponseEntity<CardTransactionsResponse> getCreditAccountTransactionsResponseEntity(String customerId, String accountId,
      String accessToken) {
    //Fetch Transactions from cache, remove this later
    CardTransactionsResponse response = cardServiceData.getAccountTransactionsResponse(customerId, accountId);
    if (Objects.nonNull(response)) {
      ApplicationLogger.logInfo("Transaction for Card is shown from cache", this.getClass());
      return ResponseEntity.ok(response);
    }
    // Till this
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.CREDIT_CARD_TRANSACTIONS_HISTORY_API_END_POINT,
              customerId, accountId)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processCardTransactionsResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new CardTransactionsResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches list of DEBIT Cards
   *
   * @param customerId
   * @param accessToken for future enhancement
   * @return ResponseEntity of type AccountsResponse
   */
  public ResponseEntity<CardsResponse> getDebitCardsResponseEntity(String customerId, String accessToken) {
    // Fetch accounts from cache you can remove this later
    CoreBankingModel coreBankingModel = coreBankingService.getCoreBankingModel(customerId);
    CardsResponse response = coreBankingModel.getDebitCardsResponse();
    if (Objects.nonNull(response)) {
      ApplicationLogger.logInfo("Debit Cards Fetched From Cache", this.getClass());
      return ResponseEntity.ok(response);
    }
    // Till this
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAccountAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.DEBIT_CARDS_API_END_POINT, customerId, null))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Debit Cards Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = cardsResponseMapper.getManipulatedCardsResponse(apiResponse.getBody());
        // Caching Debit cards, Remove this later
        ApplicationLogger.logInfo("Caching Debit cards", this.getClass());
        response.getCards().forEach(c -> cardServiceData.updateDatesInCard(c));
        coreBankingModel.setDebitCardsResponse(response);
        coreBankingService.saveCoreBankingModel(coreBankingModel);
        // Till this
        ApplicationLogger.logInfo("Debit Cards Response Body After Transformation :" + response, this.getClass());
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new CardsResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Debit Card Details for selected card
   *
   * @param customerId,cardNumber
   * @param accessToken           for future enhancement
   * @return ResponseEntity of type CardDetailResponse
   */
  public ResponseEntity<CardDetailResponse> getDebitCardDetailsResponseEntity(String customerId, String cardNumber, String accessToken) {
    CardDetailResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.DEBIT_CARD_DETAILS_API_END_POINT, customerId,
              cardNumber)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processCardDetailResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new CardDetailResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches Block  Card Response
   *
   * @return ResponseEntity of type Block Card Response
   */
  public ResponseEntity<BlockCardResponse> getBlockCardResponseEntity(BlockCardRequest blockCardRequest) {
    BlockCardResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrlForBlockCard(com.activeai.integration.banking.api.constants.PropertyConstants.BLOCK_CARD_DETAILS_API_END_POINT,
              blockCardRequest)).header(APIConstants.CONTENT_TYPE, APIConstants.APPLICATION_JSON).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Block Card Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = blockCardResponseMapper.getManipulatedBlockCardResponse(apiResponse.getBody());
        //updating stub data as BLOCKED on card status, Remove this later
        cardServiceData.updateBlockCardStatus(blockCardRequest);
        // Till this
        ApplicationLogger.logInfo("Block Card Response Body After Transformation :" + response, this.getClass());
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new BlockCardResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
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
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrlForActivateCard(com.activeai.integration.banking.api.constants.PropertyConstants.ACTIVATE_CARD_DETAILS_API_END_POINT,
              activationCardRequest)).header(APIConstants.CONTENT_TYPE, APIConstants.APPLICATION_JSON).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Activation Card Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = activationCardResponseMapper.getManipulatedActivationCardResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Activation Card Response Body After Transformation :" + response, this.getClass());
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new ActivationCardResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public ResponseEntity<ResetPinConfirmResponse> getResetPinResponseEntity(ResetPinConfirmRequest resetPinConfirmRequest) {
    ResetPinConfirmResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrlForResetPin(com.activeai.integration.banking.api.constants.PropertyConstants.RESET_PIN_CONFIRM_API_ENDPOINT,
              resetPinConfirmRequest)).header(APIConstants.CONTENT_TYPE, APIConstants.APPLICATION_JSON).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Reset Pin Confirm Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = cardsResponseMapper.getManipulatedResetPinConfirmResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Reset Pin Confirm Response Body After Transformation :" + response, this.getClass());
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new ResetPinConfirmResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public ResponseEntity<ReplaceCardConfirmResponse> getReplaceCardResponseEntity(ReplaceCardConfirmRequest replaceCardConfirmRequest) {
    ReplaceCardConfirmResponse response = new ReplaceCardConfirmResponse();
    try {
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrlForReplaceCard(com.activeai.integration.banking.api.constants.PropertyConstants.REPLACE_CARD_CONFIRM_API_ENDPOINT,
              replaceCardConfirmRequest)).header(APIConstants.CONTENT_TYPE, APIConstants.APPLICATION_JSON).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Replace Card Confirm Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = cardsResponseMapper.getManipulatedReplaceCardConfirmResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Replace Card Confirm Response Body Before Transformation :" + response, this.getClass());
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new ReplaceCardConfirmResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   *
   * @param internationalCardUsageRequest
   * @return
   */
  public ResponseEntity<InternationalUsageResponse> getInternationalUsageCardResponseEntity(
      InternationalCardUsageRequest internationalCardUsageRequest) {
    InternationalUsageResponse response = null;
    try {

      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.INTERNATIONAL_USAGE_ENABLED_API_END_POINT,
              internationalCardUsageRequest.getCustomerId(), internationalCardUsageRequest.getCardDetails().getCardNumber()))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processInternationalUsageResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new InternationalUsageResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  public ResponseEntity<InternationalUsageResponse> updateInternationalUsageFinalApiCall(
      InternationalCardUsageRequest internationalCardUsageRequest) {
    InternationalUsageResponse response = null;
    try {

      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrl(com.activeai.integration.banking.api.constants.PropertyConstants.INTERNATIONAL_USAGE_API_END_POINT,
              internationalCardUsageRequest.getCustomerId(), internationalCardUsageRequest.getCardDetails().getCardNumber()))
          .header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      return processInternationalUsageResponse(apiResponse);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new InternationalUsageResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches ConvertEMIResponse
   *
   * @param accessToken for future enhancement
   * @return ResponseEntity of type ConvertEMIResponse
   */
  public ResponseEntity<ConvertEMIResponse> getConvertEMIResponseEntity(String customerId, String cardNumber, String accessToken) {
    ConvertEMIResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest.get(propertyUtil
          .getAPIUrlForConvertEMI(com.activeai.integration.banking.api.constants.PropertyConstants.CONVERT_EMI_API_ENDPOINT, customerId,
              cardNumber)).header(APIConstants.CACHE_CONTROL, APIConstants.NO_CACHE).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Convert EMI Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = cardsResponseMapper.getManipulatedConvertEMIResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Convert EMI Response Body After Transformation :" + apiResponse.getBody(), this.getClass());
      }
      return ResponseEntity.ok(response);
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new ConvertEMIResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  /**
   * Fetches ConvertEMIConfirmResponse
   *
   * @return ResponseEntity of type ConvertEMIConfirmResponse
   */
  public ResponseEntity<ConvertEMIConfirmResponse> getConvertEMIConfirmResponseEntity(ConvertEMIConfirmRequest convertEMIConfirmRequest) {
    ConvertEMIConfirmResponse response = null;
    try {
      HttpResponse<String> apiResponse = Unirest.post(propertyUtil
          .getAPIUrlForConvertEMIConfirm(com.activeai.integration.banking.api.constants.PropertyConstants.CONVERT_EMI_CONFIRM_API_ENDPOINT,
              convertEMIConfirmRequest)).header(APIConstants.CONTENT_TYPE, APIConstants.APPLICATION_JSON).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Convert EMI Confirm Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = cardsResponseMapper.getManipulatedConvertEMIConfirmResponse(apiResponse.getBody());
        ApplicationLogger.logInfo("Convert EMI Confirm Response Body After Transformation :" + apiResponse.getBody(), this.getClass());
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new ConvertEMIConfirmResponse();
    response.setResult(propertyUtil
        .frameErrorResponse(com.activeai.integration.banking.api.constants.MessageConstants.INTERNAL_SERVER_ERROR,
            APIConstants.INTERNAL_SERVER_ERROR, 500));
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
              .header(APIConstants.CONTENT_TYPE, APIConstants.APPLICATION_JSON).asString();
      ApplicationLogger.logInfo(
          MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
          this.getClass());
      if (StringUtils.isNotEmpty(apiResponse.getBody())) {
        ApplicationLogger.logInfo("Card Payment Confirm Response Body Before Transformation :" + apiResponse.getBody(), this.getClass());
        response = cardsResponseMapper.getManipulatedCardPaymentResponse(apiResponse.getBody());
        transferServiceData.updateTransactionDetailsOnCache(cardPaymentRequest);
        ApplicationLogger.logInfo("Card Payment Confirm Response Body After Transformation :" + apiResponse.getBody(), this.getClass());
      }
      return new ResponseEntity<>(response, HttpStatus.valueOf(apiResponse.getStatus()));
    } catch (UnirestException e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.API_FAILURE_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    } catch (Exception e) {
      ApplicationLogger.logError(MessageFormat
          .format(ERROR_MESSAGE_FORMAT, com.activeai.integration.banking.api.constants.MessageConstants.EXCEPTION_MESSAGE,
              this.getClass().getName(), ExceptionUtils.getStackTrace(e)));
    }
    response = new CardPaymentResponse();
    response.setResult(propertyUtil.frameErrorResponse(MessageConstants.INTERNAL_SERVER_ERROR, APIConstants.INTERNAL_SERVER_ERROR, 500));
    return ResponseEntity.ok(response);
  }

  private ResponseEntity<CardDetailResponse> processCardDetailResponse(HttpResponse<String> apiResponse) {
    CardDetailResponse response = null;
    ApplicationLogger.logInfo(
        MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
        this.getClass());
    if (StringUtils.isNotEmpty(apiResponse.getBody())) {
      ApplicationLogger.logInfo(MessageConstants.RESPONSE_BEFORE_TRANSFORMATION + apiResponse.getBody(), this.getClass());
      response = cardsResponseMapper.getManipulatedCardDetailsResponse(apiResponse.getBody());
      ApplicationLogger.logInfo(MessageConstants.RESPONSE_AFTER_TRANSFORMATION + response, this.getClass());
    }
    return ResponseEntity.ok(response);
  }

  private ResponseEntity<CardTransactionsResponse> processCardTransactionsResponse(HttpResponse<String> apiResponse) {
    CardTransactionsResponse response = null;
        ApplicationLogger.logInfo(
            MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
        this.getClass());
    if (StringUtils.isNotEmpty(apiResponse.getBody())) {
      ApplicationLogger.logInfo(MessageConstants.RESPONSE_BEFORE_TRANSFORMATION + apiResponse.getBody(), this.getClass());
      response = cardsResponseMapper.getManipulatedCardTransactionsResponse(apiResponse.getBody());
      ApplicationLogger.logInfo(MessageConstants.RESPONSE_AFTER_TRANSFORMATION + response, this.getClass());
    }
    return ResponseEntity.ok(response);
  }

  private ResponseEntity<InternationalUsageResponse> processInternationalUsageResponse(HttpResponse<String> apiResponse) {
    InternationalUsageResponse response = null;
    ApplicationLogger.logInfo(
        MessageConstants.API_RESPONSE_STATUS + apiResponse.getStatus() + MessageConstants.AND_RESPONSE_STATUS_TEXT + apiResponse.getStatusText(),
        this.getClass());
    if (StringUtils.isNotEmpty(apiResponse.getBody())) {
      ApplicationLogger.logInfo(MessageConstants.RESPONSE_BEFORE_TRANSFORMATION + apiResponse.getBody(), this.getClass());
      response = cardsResponseMapper.getManipulatedInternationalUsageResponse(apiResponse.getBody());
      ApplicationLogger.logInfo(MessageConstants.RESPONSE_AFTER_TRANSFORMATION + response, this.getClass());
    }
    return ResponseEntity.ok(response);
  }
}

