package com.activeai.integration.banking.api.controller;

import com.activeai.integration.banking.api.services.CardsService;
import com.activeai.integration.banking.api.services.CreditCardService;
import com.activeai.integration.banking.api.services.DebitCardService;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.domain.request.*;
import com.activeai.integration.banking.domain.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(value = "Cards Related APIs", description = "Shows API documentation regarding cards APIs like credit card, debit card")
@RestController
public class CardsApiController {

  @Autowired private CardsService cardsService;
  @Autowired private DebitCardService debitCardService;
  @Autowired private CreditCardService creditCardService;

  /*
  Return List of Credit Cards
   */
  @ApiOperation(value = "Returns list of credit cards based on customer ID")
  @GetMapping(value = "/{customerId}/cards/creditcards", produces = {"application/json"})
  public ResponseEntity<CardsResponse> getCreditCards(@PathVariable(value = "customerId", required = true) String customerId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getCreditCards", this.getClass());
    return cardsService.getCreditCardsResponseEntity(customerId, accessToken);
  }

  /*
  Return Selected Credit Card Details
   */
  @ApiOperation(value = "Returns selected credit card details")
  @GetMapping(value = "/{customerId}/cards/creditcards/{cardNumber}", produces = {"application/json"})
  public ResponseEntity<CardDetailResponse> getCreditCardDetails(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getCreditCardDetails", this.getClass());
    return cardsService.getCreditCardDetailsResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
  Return Selected Credit Card Transaction History
   */
  @ApiOperation(value = "Returns selected card transaction history")
  @GetMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/transactions", produces = {"application/json"})
  public ResponseEntity<CardTransactionsResponse> getCreditCardTransactions(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getCreditCardTransactions", this.getClass());
    return cardsService.getCreditAccountTransactionsResponseEntity(customerId, cardNumber, accessToken);
  }


  /*
  Return Selected Debit Card Limits
   */
  @ApiOperation(value = "Returns debit card limit")
  @GetMapping(value = "/{customerId}/debitcards/{cardNumber}/getLimits", produces = {"application/json"})
  public ResponseEntity<DebitCardLimitResponse> getDebitCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getDebitLimitResponse", this.getClass());
    return debitCardService.getDebitCardLimitResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
  Return Selected Credit Card Limit
   */
  @ApiOperation(value = "Returns credit card limit")
  @GetMapping(value = "/{customerId}/creditcards/{cardNumber}/getLimits", produces = {"application/json"})
  public ResponseEntity<CreditCardLimitResponse> getCreditCardLimits(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getCreditLimitResponse", this.getClass());
    return creditCardService.getCreditCardLimitResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
   * Getting DebitCardLimitConfirmation
   */
  @ApiOperation(value = "Returns confirmation of debit card limit")
  @PostMapping(value = "/{customerId}/debitcards/{cardNumber}/limit/confirm", produces = {"application/json"}, consumes = {
      "application/json"}) public ResponseEntity<DebitCardLimitConfirmResponse> confirmDebitCardLimit(
      @PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestBody final DebitCardLimitConfirmRequest debitCardLimitConfirmRequest) {
    ApplicationLogger.logInfo("Entering getDebitCardLimitConfirm API", this.getClass());
    return debitCardService.getDebitLimitConfirmResponseEntity(debitCardLimitConfirmRequest);
  }

  /*
  Return DEbit CArd Limit Confirmation
   */
  @ApiOperation(value = "Returns confirmation of credit card limit")
  @PostMapping(value = "/{customerId}/creditcards/limit/confirm", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<CreditCardLimitConfirmResponse> confirmCreditCardLimit(
      @PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final CreditCardLimitConfirmRequest creditCardLimitConfirmRequest) {
    ApplicationLogger.logInfo("Entering getCreditCardLimitConfirm API", this.getClass());
    return creditCardService.getCreditLimitConfirmResponseEntity(creditCardLimitConfirmRequest);
  }

  /*
   * Return Status of Blocking of Card
   */
  @ApiOperation("Return blocking status of selected card")
  @PostMapping(value = "/{customerId}/cards/{cardNumber}/block/confirm", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<BlockCardResponse> blockCard(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber, @RequestBody final BlockCardRequest blockCardRequest) {
    ApplicationLogger.logInfo("Entering getBlockStatus API", this.getClass());
    return cardsService.getBlockCardResponseEntity(blockCardRequest);
  }

  /*
   * Return Status of Activation of Card
   */
  @ApiOperation("Return activation status of selected card")
  @PostMapping(value = "/{customerId}/cards/{cardNumber}/activation/confirm", produces = {"application/json"}, consumes = {
      "application/json"}) public ResponseEntity<ActivationCardResponse> activationCard(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final ActivationCardRequest activationCardRequest) {
    ApplicationLogger.logInfo("Entering getActivationStatus API", this.getClass());
    return cardsService.getActivationCardResponseEntity(activationCardRequest);
  }

  /*
   * Return Status of Resetting of Pin
   */
  @ApiOperation("Return reset pin status of selected card")
  @PostMapping(value = "/{customerId}/cards/{cardNumber}/resetPin/confirm", produces = {"application/json"}, consumes = {
      "application/json"}) public ResponseEntity<ResetPinConfirmResponse> resetPin(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final ResetPinConfirmRequest resetPinConfirmRequest) {
    ApplicationLogger.logInfo("Entering getResetPin Confirm API", this.getClass());
    return cardsService.getResetPinResponseEntity(resetPinConfirmRequest);
  }

  /*
  Return Status of Replacement of Card
   */
  @ApiOperation("Return status of replacement of card")
  @PostMapping(value = "/{customerId}/cards/{cardNumber}/replaceCard/confirm", produces = {"application/json"}, consumes = {
      "application/json"}) public ResponseEntity<ReplaceCardConfirmResponse> replaceCard(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final ReplaceCardConfirmRequest replaceCardConfirmRequest) {
    ApplicationLogger.logInfo("Entering getReplaceCard Confirm API", this.getClass());
    return cardsService.getReplaceCardResponseEntity(replaceCardConfirmRequest);
  }

  /**
   * @param customerId
   * @param cardNumber
   * @param internationalCardUsageRequest
   * @return
   * @deprecated Return Intenational Usage Enabled of Card
   */
  @Deprecated @ApiOperation("Return international usage of card is enabled or disabled")
  @PostMapping(value = "/{customerId}/cards/{cardNumber}/internationalUsage/enabled", produces = {"application/json"}, consumes = {
      "application/json"}) public ResponseEntity<InternationalUsageResponse> updateInternationalUsage(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final InternationalCardUsageRequest internationalCardUsageRequest) {
    ApplicationLogger.logInfo("Entering updateInternationalUsage  API", this.getClass());
    return cardsService.getInternationalUsageCardResponseEntity(internationalCardUsageRequest);
  }

  /*
  Return Status of Internation Usage Enabled
   */
  @ApiOperation("Return international usage reference id and status ")
  @PostMapping(value = "/{customerId}/cards/{cardNumber}/internationalUsage/api", produces = {"application/json"}, consumes = {
      "application/json"}) public ResponseEntity<InternationalUsageResponse> updateInternationalUsageFinalApiCall(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final InternationalCardUsageRequest internationalCardUsageRequest) {
    ApplicationLogger.logInfo("Entering updateInternationalUsage  API", this.getClass());
    return cardsService.updateInternationalUsageFinalApiCall(internationalCardUsageRequest);
  }

  /*
  Return List of Debit Cards
   */
  @ApiOperation(value = "Returns list of debit cards based on customer ID")
  @GetMapping(value = "/{customerId}/cards/debitcards", produces = {"application/json"}) public ResponseEntity<CardsResponse> getDebitCards(
      @PathVariable(value = "customerId", required = true) String customerId,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getDebitCards", this.getClass());
    return cardsService.getDebitCardsResponseEntity(customerId, accessToken);
  }

  /*
  Return Selected Debit Card Details
   */
  @ApiOperation(value = "Returns selected debit card details")
  @GetMapping(value = "/{customerId}/cards/debitcards/{cardNumber}", produces = {"application/json"})
  public ResponseEntity<CardDetailResponse> getDebitCardDetails(@PathVariable(value = "customerId", required = true) String customerId,
      @PathVariable(value = "cardNumber", required = true) String cardNumber,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getDebitCardDetails", this.getClass());
    return cardsService.getDebitCardDetailsResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
   * Return the Conversion of Transaction to EMI for Credit Cards
   */
  @ApiOperation("Return Conversion to EMI from Transaction of Credit card")
  @GetMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/convertemi", produces = {"application/json"})
  public ResponseEntity<ConvertEMIResponse> convertEMI(@PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestParam(name = "accessToken", required = false) String accessToken) {
    ApplicationLogger.logInfo("Entering getEMIConversion API", this.getClass());
    return cardsService.getConvertEMIResponseEntity(customerId, cardNumber, accessToken);
  }

  /*
   * Return the Status of Conversion of Transaction to EMI for Credit Cards
   */
  @ApiOperation("Return status of Conversion to EMI from Transaction of Credit card")
  @PostMapping(value = "/{customerId}/cards/creditcards/{cardNumber}/convertemi/confirm", produces = {"application/json"}, consumes = {
      "application/json"}) public ResponseEntity<ConvertEMIConfirmResponse> convertEMIConfirm(
      @PathVariable(name = "customerId", required = true) String customerId,
      @PathVariable(name = "cardNumber", required = true) String cardNumber,
      @RequestBody final ConvertEMIConfirmRequest convertEMIConfirmRequest) {
    ApplicationLogger.logInfo("Entering getEMIConversion Confirm API", this.getClass());
    return cardsService.getConvertEMIConfirmResponseEntity(convertEMIConfirmRequest);
  }

  @ApiOperation(value = "Returns confirmation of Credit Card payment")
  @PostMapping(value = "/{customerId}/cards/creditcards/payment/confirm", produces = {"application/json"}, consumes = {"application/json"})
  public ResponseEntity<CardPaymentResponse> confirmCardPayment(@PathVariable(value = "customerId", required = true) String customerId,
      @RequestBody final CardPaymentRequest cardPaymentRequest) {
    ApplicationLogger.logInfo("Entering getCardPaymentConfirm API", this.getClass());
    return cardsService.getCardPaymentResponseEntity(cardPaymentRequest);
  }
}
